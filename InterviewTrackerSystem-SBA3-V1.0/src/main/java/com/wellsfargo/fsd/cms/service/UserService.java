package com.wellsfargo.fsd.cms.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.wellsfargo.fsd.cms.dao.AttendeesRepository;
import com.wellsfargo.fsd.cms.dao.UserRepository;
import com.wellsfargo.fsd.cms.entity.User;
import com.wellsfargo.fsd.cms.exception.ITSException;

@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;

	@Autowired
	private AttendeesRepository attendeesRepo;

	@Transactional
	public User addUser(User user) throws ITSException {

		if (user != null) {
			if (userRepo.existsById(user.getUserId())) {
				throw new ITSException("User ID already in use");
			}
			if (userRepo.existsByEmail(user.getEmail())) {
				throw new ITSException("Email address already in use");
			}

			if (userRepo.existsByMobile(user.getMobile())) {
				throw new ITSException("Mobile number already registered");
			}

			userRepo.save(user);
		}
		return user;
	}

	@Transactional
	public boolean deleteUser(int userId) throws ITSException {

		if (!(userRepo.existsById(userId))) {
			throw new ITSException("User ID not found");
		}

		userRepo.deleteById(userId);

		List<Integer> currentUserlist = attendeesRepo.findByUserId(Integer.toString(userId));
		if (!(currentUserlist.isEmpty())) {
			for (Integer i : currentUserlist) {
				attendeesRepo.deleteById(i);
			}
		}
		return true;
	}

	@Transactional
	public List<User> displayAllUsers() throws ITSException {

		return userRepo.findAllUsers();

	}

}
