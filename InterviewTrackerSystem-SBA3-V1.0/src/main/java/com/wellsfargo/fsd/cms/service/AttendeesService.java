package com.wellsfargo.fsd.cms.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.cms.dao.AttendeesRepository;
import com.wellsfargo.fsd.cms.dao.InterviewRepository;
import com.wellsfargo.fsd.cms.dao.UserRepository;
import com.wellsfargo.fsd.cms.entity.Attendees;
import com.wellsfargo.fsd.cms.exception.ITSException;
import com.wellsfargo.fsd.cms.service.dto.AttendeesDTO;

@Service
public class AttendeesService {

	@Autowired
	private AttendeesRepository attendeesRepo;

	@Autowired
	private InterviewRepository interviewRepo;

	@Autowired
	private UserRepository userRepo;

	@Transactional
	public List<AttendeesDTO> getAllInterviews() throws ITSException {
		List<AttendeesDTO> eachIntList = new ArrayList<>();
		List<Integer> intList = attendeesRepo.findInterviewList();
		for (Integer i : intList) {
			AttendeesDTO eachIntAttList = new AttendeesDTO();
			eachIntAttList.setAttendeeInterviewId(i);
			List<Integer> userlist = attendeesRepo.findInterviewAttendees(i);
			String result = userlist.stream().map(n -> String.valueOf(n)).collect(Collectors.joining("-", "{", "}"));

			eachIntAttList.setAttendeeUserId(result);
			eachIntList.add(eachIntAttList);

		}
		return eachIntList;
	}

	@Transactional
	public List<Integer> displayAllUsersForInterview(int interviewId) throws ITSException {

		return attendeesRepo.findInterviewAttendees(interviewId);
	}

	@Transactional
	public String addAttendeeToInterview(int interviewId, int userId) throws ITSException {
		String s = null;
		if (!(interviewId > 0)) {
			s = "operation cancelled";
			throw new ITSException("InterviewId provided does not match with the records");
		} else if (!(userId > 0)) {
			s = "operation cancelled";
			throw new ITSException("UserId provided does not match with the records");
		} else {
			if (!(interviewRepo.existsById(interviewId))) {
				throw new ITSException("InterviewId mentioned is not available");
			}
			if (!(userRepo.existsById(userId))) {
				throw new ITSException("UserId mentioned is not available, please create user");
			}
			if (attendeesRepo.InterviewConsistsUser(interviewId, Integer.toString(userId)) > 0) {
				throw new ITSException("The given UserId is already added as Attendee to the given InterviewId");
			}
			attendeesRepo.save(new Attendees(interviewId, Integer.toString(userId)));
			s = (userId + " is added to the Interview Id " + interviewId);
		}
		return s;

	}

	@Transactional
	public String deleteAttendeeFromInterview(int interviewId, int userId) throws ITSException {
		String s = null;
		if (!(interviewId > 0)) {
			s = "operation cancelled";
			throw new ITSException("InterviewId provided does not match with the records");
		} else if (!(userId > 0)) {
			s = "operation cancelled";
			throw new ITSException("UserId provided does not match with the records");
		} else {
			if (attendeesRepo.InterviewConsistsUser(interviewId, Integer.toString(userId)) == 0) {
				throw new ITSException(
						"The UserId provided was not added to Interview before, hence operation cancelled");
			}
			if (!(interviewRepo.existsById(interviewId))) {
				throw new ITSException("InterviewId mentioned is not available");
			}
			if (!(userRepo.existsById(userId))) {
				throw new ITSException("UserId mentioned is not available");
			}
			attendeesRepo.delete(attendeesRepo.findByUserIdAndInterviewId(interviewId, Integer.toString(userId)));
			s = (userId + " is removed from the Interview " + interviewId);
		}
		return s;
	}

}
