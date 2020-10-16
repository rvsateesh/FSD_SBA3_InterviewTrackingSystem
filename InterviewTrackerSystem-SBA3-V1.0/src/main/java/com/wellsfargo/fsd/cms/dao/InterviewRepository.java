package com.wellsfargo.fsd.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.cms.entity.Interview;

@Repository
public interface InterviewRepository extends JpaRepository<Interview, Integer> {

	boolean existsByInterviewName(String interviewName);

	boolean existsByInterviewerName(String interviewerName);

	@Query("SELECT i FROM Interview i WHERE i.interviewId = :interviewId")
	Interview getById(int interviewId);

	@Query("SELECT i FROM Interview i WHERE i.interviewerName = :interviewerName")
	List<Interview> findbyInterviewerName(String interviewerName);

	@Query("SELECT i FROM Interview i WHERE i.interviewName = :interviewName")
	Interview findbyInterviewName(String interviewName);

	@Query("SELECT count(i) FROM Interview i")
	int getCountOfInterviews();

	@Query("SELECT i FROM Interview i WHERE i.interviewerName = :interviewerName and i.interviewName = :interviewName")
	Interview searchByNames(String interviewerName, String interviewName);

}
