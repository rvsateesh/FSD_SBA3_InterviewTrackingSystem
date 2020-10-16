package com.wellsfargo.fsd.cms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.wellsfargo.fsd.cms.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {

	boolean existsByEmail(String email);

	boolean existsByMobile(String mobile);

	@Query("SELECT u FROM User u")
	List<User> findAllUsers();

	@Query("SELECT u FROM User u WHERE u.userId = :userId")
	User getUser(int userId);

}
