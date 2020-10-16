package com.wellsfargo.fsd.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.cms.entity.Interview;
import com.wellsfargo.fsd.cms.exception.ITSException;
import com.wellsfargo.fsd.cms.service.InterviewService;
import com.wellsfargo.fsd.cms.service.dto.InterviewDTO;

@RestController
@RequestMapping("/interviews")
public class InterviewRestController {

	@Autowired
	private InterviewService intService;

	@GetMapping
	public ResponseEntity<List<Interview>> displayAllInterviews() throws ITSException {
		return new ResponseEntity<List<Interview>>(intService.getAllInterviews(), HttpStatus.OK);
	}

	@GetMapping("/{interviewId}")
	public ResponseEntity<Interview> getByInterviewId(@PathVariable("interviewId") int interviewId)
			throws ITSException {
		return new ResponseEntity<Interview>(intService.getbyInterviewId(interviewId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<Interview> addInterview(@RequestBody InterviewDTO interviewDTO) throws ITSException {
		return interviewDTO.isInterviewValid(interviewDTO)
				? new ResponseEntity<Interview>(intService.addInterview(interviewDTO.ConvertToEntity(interviewDTO)),
						HttpStatus.OK)
				: new ResponseEntity<Interview>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@DeleteMapping("/delete/{interviewId}")
	public ResponseEntity<Void> deleteInterview(@PathVariable("interviewId") int interviewId) throws ITSException {
		intService.deleteInterview(interviewId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}

	@PutMapping("/update/{interviewId}/{status}")
	public ResponseEntity<Interview> updateInterviewStatus(@PathVariable("interviewId") int interviewId,
			@PathVariable("status") String status) throws ITSException {
		intService.updateInterviewStatus(interviewId, status);
		return new ResponseEntity<Interview>(intService.getbyInterviewId(interviewId), HttpStatus.OK);
	}

	@GetMapping("/interview/{interviewname}")
	public ResponseEntity<Interview> getByInterivewName(@PathVariable("interviewname") String interviewname)
			throws ITSException {
		return new ResponseEntity<Interview>(intService.getByInterviewName(interviewname), HttpStatus.OK);
	}

	@GetMapping("/interviewer/{interviewerName}")
	public ResponseEntity<List<Interview>> getListByInterviewerName(
			@PathVariable("interviewerName") String interviewerName) throws ITSException {
		return new ResponseEntity<List<Interview>>(intService.getByInterviewerName(interviewerName), HttpStatus.OK);
	}

	@GetMapping("/count")
	public ResponseEntity<String> getCountOfInterviews() throws ITSException {
		return new ResponseEntity<String>(intService.countOfInterviews(), HttpStatus.OK);
	}

	@GetMapping("/searchByNames/{interviewerName}/{interviewname}")
	public ResponseEntity<Interview> searchByNames(@PathVariable("interviewerName") String interviewerName,
			@PathVariable("interviewname") String interviewname) throws ITSException {
		return new ResponseEntity<Interview>(intService.searchByNames(interviewerName, interviewname), HttpStatus.OK);
	}
}
