package com.wellsfargo.fsd.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Attendees")
public class Attendees {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "AttendeeId", nullable = false)
	private int AttendeeId;

	@Column(name = "AttendeeInterviewId", nullable = false)
	private int AttendeeInterviewId;

	@Column(name = "AttendeeUserId", nullable = true)
	private String AttendeeUserId;

	public Attendees() {
	}

	public Attendees(int attendeeInterviewId, String attendeeUserId) {
		AttendeeInterviewId = attendeeInterviewId;
		AttendeeUserId = attendeeUserId;
	}

	public int getAttendeeId() {
		return AttendeeId;
	}

	public void setAttendeeId(int attendeeId) {
		AttendeeId = attendeeId;
	}

	public int getAttendeeInterviewId() {
		return AttendeeInterviewId;
	}

	public void setAttendeeInterviewId(int attendeeInterviewId) {
		AttendeeInterviewId = attendeeInterviewId;
	}

	public String getAttendeeUserId() {
		return AttendeeUserId;
	}

	public void setAttendeeUserId(String attendeeUserId) {
		AttendeeUserId = attendeeUserId;
	}

	@Override
	public String toString() {
		return "Attendees [AttendeeId=" + AttendeeId + ", AttendeeInterviewId=" + AttendeeInterviewId
				+ ", AttendeeUserId=" + AttendeeUserId + "]";
	}

}
