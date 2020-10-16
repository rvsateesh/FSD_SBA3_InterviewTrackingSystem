package com.wellsfargo.fsd.cms.service.dto;

public class AttendeesDTO {

	private int AttendeeInterviewId;

	private String AttendeeUserId;

	public AttendeesDTO() {
	}

	public AttendeesDTO(int attendeeInterviewId, String attendeeUserId) {
		AttendeeInterviewId = attendeeInterviewId;
		AttendeeUserId = attendeeUserId;
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
		return "AttendeesDTO [AttendeeInterviewId=" + AttendeeInterviewId + ", AttendeeUserId=" + AttendeeUserId + "]";
	}

}
