package com.wellsfargo.fsd.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.wellsfargo.fsd.cms.exception.ITSException;
import com.wellsfargo.fsd.cms.service.AttendeesService;
import com.wellsfargo.fsd.cms.service.dto.AttendeesDTO;

@RestController
@RequestMapping("/attendees")
public class AttendeesRestController {

	@Autowired
	private AttendeesService attendeesService;

	@GetMapping
	public ResponseEntity<List<AttendeesDTO>> listAllInterviews() throws ITSException {
		return new ResponseEntity<List<AttendeesDTO>>(attendeesService.getAllInterviews(), HttpStatus.OK);
	}

	@PostMapping("/addAttendee/{interviewId}/{userId}")
	public ResponseEntity<String> addAttendeeToInterview(@PathVariable("interviewId") int interviewId,
			@PathVariable("userId") int userId) throws ITSException {
		return new ResponseEntity<String>(attendeesService.addAttendeeToInterview(interviewId, userId), HttpStatus.OK);
	}

	@GetMapping("/interview/{interviewId}")
	public ResponseEntity<List<Integer>> AttendeesOfAnInterview(@PathVariable("interviewId") int interviewId)
			throws ITSException {
		return new ResponseEntity<List<Integer>>(attendeesService.displayAllUsersForInterview(interviewId),
				HttpStatus.OK);
	}

	@DeleteMapping("/removeUser/{interviewId}/{userId}")
	public ResponseEntity<String> removeAttendeeFromInterview(@PathVariable("interviewId") int interviewId,
			@PathVariable("userId") int userId) throws ITSException {
		return new ResponseEntity<String>(attendeesService.deleteAttendeeFromInterview(interviewId, userId),
				HttpStatus.OK);
	}

}
