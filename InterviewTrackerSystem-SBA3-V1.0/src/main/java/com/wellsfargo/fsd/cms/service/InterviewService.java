package com.wellsfargo.fsd.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.cms.dao.AttendeesRepository;
import com.wellsfargo.fsd.cms.dao.InterviewRepository;
import com.wellsfargo.fsd.cms.entity.Interview;
import com.wellsfargo.fsd.cms.exception.ITSException;

import ch.qos.logback.core.rolling.helper.IntegerTokenConverter;

@Service
public class InterviewService {

	@Autowired
	private InterviewRepository interviewRepo;

	@Autowired
	private AttendeesRepository attendeesRepo;

	@Transactional
	public Interview addInterview(Interview interview) throws ITSException {

		if (interview != null) {
			if (interviewRepo.existsById(interview.getInterviewId())) {
				throw new ITSException("Interview ID already in use");
			}
			if (interviewRepo.existsByInterviewName(interview.getInterviewName())) {
				throw new ITSException("Interview Name cannot be duplicate");
			}

			interviewRepo.save(interview);
		}
		return interview;
	}

	@Transactional
	public boolean deleteInterview(int interviewId) throws ITSException {

		if (!(interviewRepo.existsById(interviewId))) {
			throw new ITSException("No such Interview ID defined");
		}

		interviewRepo.deleteById(interviewId);

		List<Integer> currentIntList = attendeesRepo.findByInterviewId(interviewId);
		if (!(currentIntList.isEmpty())) {
			for (Integer i : currentIntList) {
				attendeesRepo.deleteById(i);
			}

		}
		return true;
	}

	@Transactional
	public Interview updateInterviewStatus(int interviewId, String status) throws ITSException {
		Interview interview = new Interview();
		interview = interviewRepo.getById(interviewId);
		if (interviewRepo.existsById(interviewId)) {
			interview.setInterviewStatus(status);
		} else {
			throw new ITSException("No such interview ID defined");
		}
		return interview;
	}

	@Transactional
	public Interview getByInterviewName(String interviewName) throws ITSException {

		if (interviewRepo.existsByInterviewName(interviewName)) {
			return interviewRepo.findbyInterviewName(interviewName);
		} else {
			throw new ITSException("No such interview Name defined");
		}

	}

	@Transactional
	public List<Interview> getByInterviewerName(String interviewerName) throws ITSException {

		if (interviewRepo.existsByInterviewerName(interviewerName)) {
			return interviewRepo.findbyInterviewerName(interviewerName);
		} else {
			throw new ITSException("No such interviewer Name defined");
		}
	}

	@Transactional
	public String countOfInterviews() throws ITSException {

		return "There are " + interviewRepo.getCountOfInterviews() + " interviews currently active";
	}

	@Transactional
	public List<Interview> getAllInterviews() throws ITSException {

		return interviewRepo.findAll();
	}

	@Transactional
	public Interview getbyInterviewId(int interviewId) throws ITSException {
		if (interviewRepo.existsById(interviewId)) {
			return interviewRepo.getById(interviewId);
		} else {
			throw new ITSException("No such InterviewID available");
		}

	}

	@Transactional
	public Interview searchByNames(String interviewerName, String interviewName) throws ITSException {
		if (interviewRepo.existsByInterviewerName(interviewerName)
				&& interviewRepo.existsByInterviewName(interviewName)) {
			return interviewRepo.searchByNames(interviewerName, interviewName);
		} else {
			throw new ITSException("No such interview exists with that combination");
		}
	}
}
