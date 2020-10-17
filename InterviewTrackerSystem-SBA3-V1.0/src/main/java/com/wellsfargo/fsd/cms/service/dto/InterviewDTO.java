package com.wellsfargo.fsd.cms.service.dto;

import java.time.LocalDate;
import java.time.LocalTime;

import com.wellsfargo.fsd.cms.entity.Interview;
import com.wellsfargo.fsd.cms.exception.ITSException;

public class InterviewDTO {

	public int interviewId;
	public String interviewerName;
	public String interviewName;
	public String userSkills;
	public LocalTime time;
	public LocalDate date;
	public String interviewStatus;
	public String remarks;

	public InterviewDTO() {
	}

	public InterviewDTO(int interviewId, String interviewerName, String interviewName, String userSkills,
			LocalTime time, LocalDate date, String interviewStatus, String remarks) {
		super();
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}

	public Interview ConvertToEntity(InterviewDTO interviewDTO) {
		return new Interview(interviewDTO);
	}

	public boolean isInterviewValid(InterviewDTO interviewDTO) throws ITSException {
		boolean intrvrName, intName, skills, time, date, status, remarks, intValid = false;
		if (interviewDTO.interviewerName != null && interviewDTO.interviewerName.length() >= 5
				&& interviewDTO.interviewerName.length() <= 30) {
			intrvrName = true;
		} else {
			intrvrName = false;
			throw new ITSException(
					"Interviewer Name should not be null and its length must be between 5 to 30 characters");
		}

		if (interviewDTO.interviewName != null && interviewDTO.interviewName.length() >= 3
				&& interviewDTO.interviewName.length() <= 30) {
			intName = true;
		} else {
			intName = false;
			throw new ITSException(
					"Interview Name should not be null and its length must be between 3 to 30 characters");
		}

		if (interviewDTO.userSkills != null && interviewDTO.userSkills.length() >= 5
				&& interviewDTO.userSkills.length() <= 30) {
			skills = true;
		} else {
			skills = false;
			throw new ITSException("User skills should not be null and its length must be between 5 to 30 characters");
		}

		if (interviewDTO.time != null) {
			time = true;
		} else {
			time = false;
			throw new ITSException("Time should not be null and is of format HH:MM:SS");
		}

		if (interviewDTO.date != null && interviewDTO.date.isAfter(LocalDate.now())) {
			date = true;
		} else {
			date = false;
			throw new ITSException("Date should not be null and is of future date with format YYYY:MM:DD");
		}

		if (interviewDTO.interviewStatus != null && interviewDTO.interviewStatus.length() >= 5
				&& interviewDTO.interviewStatus.length() <= 100) {
			status = true;
		} else {
			status = false;
			throw new ITSException(
					"Interview status should not be null and its length must be between 5 to 100 characters");
		}

		if (interviewDTO.remarks != null && interviewDTO.remarks.length() >= 5
				&& interviewDTO.remarks.length() <= 100) {
			remarks = true;
		} else {
			remarks = false;
			throw new ITSException("Remarks should not be null and its length must be between 5 to 100 characters");
		}

		if (intrvrName && intName && skills && time && date && status && remarks) {
			intValid = true;
		} else {
			intValid = false;
		}

		return intValid;
	}

}
