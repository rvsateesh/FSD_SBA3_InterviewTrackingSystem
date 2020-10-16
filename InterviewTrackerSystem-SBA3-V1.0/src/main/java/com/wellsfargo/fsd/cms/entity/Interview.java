package com.wellsfargo.fsd.cms.entity;

import java.time.LocalDate;
import java.time.LocalTime;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

import com.wellsfargo.fsd.cms.service.dto.InterviewDTO;

@Entity
@Table(name = "interview")
public class Interview {

	@Id
	@Column(name = "interviewId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @NotNull(message = "Interview Id is requried")
	private int interviewId;

	@Column(name = "interviewerName")
	// @NotNull(message = "Interviewer Name is requried")
	// @Size(min=5, max=30, message = "Interviewer Name length should be in between
	// 5 characters to 30 characters")
	private String interviewerName;

	@Column(name = "interviewName")
	// @NotNull(message = "Interview Name is requried")
	// @Size(min=3, max=30, message = "Interview Name length should be in between 3
	// characters to 30 characters")
	private String interviewName;

	@Column(name = "userSkills")
	// @NotNull(message = "User Skills are requried")
	// @Size(min=5, max=30, message = "User Skills length should be in between 5
	// characters to 30 characters")
	private String userSkills;

	@Column(name = "time")
	// @DateTimeFormat(iso=ISO.TIME)
	// @NotNull(message = "Interview Scheduled Time is required to be mentioned")
	private LocalTime time;

	@Column(name = "date")
	// @DateTimeFormat(iso=ISO.DATE)
	// @NotNull(message = "Interview Scheduled Date is required to be mentioned")
	private LocalDate date;

	@Column(name = "interviewStatus")
	// @NotNull(message = "Interview Status is required to be mentioned")
	// @Size(min=5, max=100, message = "Interview Status length should be in between
	// 5 characters to 100 characters")
	private String interviewStatus;

	@Column(name = "remarks")
	// @NotNull(message = "Remarks are required to be mentioned")
	// @Size(min=5, max=100, message = "Remarks length should be in between 5
	// characters to 100 characters")
	private String remarks;

	public Interview() {
	}

	public Interview(int interviewId, String interviewerName, String interviewName, String userSkills, LocalTime time,
			LocalDate date, String interviewStatus, String remarks) {
		this.interviewId = interviewId;
		this.interviewerName = interviewerName;
		this.interviewName = interviewName;
		this.userSkills = userSkills;
		this.time = time;
		this.date = date;
		this.interviewStatus = interviewStatus;
		this.remarks = remarks;
	}

	public Interview(InterviewDTO interviewDTO) {
		this.interviewId = interviewDTO.interviewId;
		this.interviewerName = interviewDTO.interviewerName;
		this.interviewName = interviewDTO.interviewName;
		this.userSkills = interviewDTO.userSkills;
		this.time = interviewDTO.time;
		this.date = interviewDTO.date;
		this.interviewStatus = interviewDTO.interviewStatus;
		this.remarks = interviewDTO.remarks;
	}

	public int getInterviewId() {
		return interviewId;
	}

	public void setInterviewId(int interviewId) {
		this.interviewId = interviewId;
	}

	public String getInterviewerName() {
		return interviewerName;
	}

	public void setInterviewerName(String interviewerName) {
		this.interviewerName = interviewerName;
	}

	public String getInterviewName() {
		return interviewName;
	}

	public void setInterviewName(String interviewName) {
		this.interviewName = interviewName;
	}

	public String getUserSkills() {
		return userSkills;
	}

	public void setUserSkills(String userSkills) {
		this.userSkills = userSkills;
	}

	public LocalTime getTime() {
		return time;
	}

	public void setTime(LocalTime time) {
		this.time = time;
	}

	public LocalDate getDate() {
		return date;
	}

	public void setDate(LocalDate date) {
		this.date = date;
	}

	public String getInterviewStatus() {
		return interviewStatus;
	}

	public void setInterviewStatus(String interviewStatus) {
		this.interviewStatus = interviewStatus;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Interview [interviewId=" + interviewId + ", interviewerName=" + interviewerName + ", interviewName="
				+ interviewName + ", userSkills=" + userSkills + ", time=" + time + ", date=" + date
				+ ", interviewStatus=" + interviewStatus + ", remarks=" + remarks + "]";
	}

}
