package com.wellsfargo.fsd.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.cms.entity.Attendees;

@Repository
public interface AttendeesRepository extends JpaRepository<Attendees, Integer> {

	@Query("SELECT distinct a.AttendeeInterviewId FROM Attendees a")
	List<Integer> findInterviewList();

	@Query("SELECT a.AttendeeUserId FROM Attendees a WHERE a.AttendeeInterviewId = :AttendeeInterviewId")
	List<Integer> findInterviewAttendees(int AttendeeInterviewId);

	@Query("SELECT count(*) FROM Attendees a WHERE a.AttendeeInterviewId = :AttendeeInterviewId and a.AttendeeUserId = :AttendeeUserId")
	int InterviewConsistsUser(int AttendeeInterviewId, String AttendeeUserId);

	@Query("SELECT a.AttendeeId FROM Attendees a WHERE a.AttendeeInterviewId = :AttendeeInterviewId")
	List<Integer> findByInterviewId(int AttendeeInterviewId);

	@Query("SELECT a.AttendeeId FROM Attendees a WHERE a.AttendeeUserId = :AttendeeUserId")
	List<Integer> findByUserId(String AttendeeUserId);

	@Query("SELECT a FROM Attendees a WHERE a.AttendeeInterviewId = :AttendeeInterviewId and a.AttendeeUserId = :AttendeeUserId")
	Attendees findByUserIdAndInterviewId(int AttendeeInterviewId, String AttendeeUserId);

}
