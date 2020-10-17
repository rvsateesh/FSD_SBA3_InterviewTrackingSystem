package com.wellsfargo.fsd.cms.service.dto;

import com.wellsfargo.fsd.cms.entity.User;
import com.wellsfargo.fsd.cms.exception.ITSException;

public class UserDTO {

	public int userId;
	public String firstName;
	public String lastName;
	public String email;
	public String mobile;

	public UserDTO() {
	}

	public UserDTO(int userId, String firstName, String lastName, String email, String mobile) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public boolean isUserValid(UserDTO userDTO) throws ITSException {
		boolean fname, lname, email, mobile, userValid = false;
		if (userDTO.firstName != null && userDTO.firstName.length() >= 5 && userDTO.firstName.length() <= 30) {
			fname = true;
		} else {
			fname = false;
			throw new ITSException("First Name should not be null and its length must be between 5 to 30 characters");
		}

		if (userDTO.lastName != null && userDTO.lastName.length() >= 3 && userDTO.lastName.length() <= 25) {
			lname = true;
		} else {
			lname = false;
			throw new ITSException("Last Name should not be null and its length must be between 3 to 25 characters");
		}

		if (userDTO.email != null && userDTO.email.matches("[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")) {
			email = true;
		} else {
			email = false;
			throw new ITSException("Email address should be unique, not null and must be of proper format.");
		}

		if (userDTO.mobile != null && userDTO.mobile.matches("[1-9][0-9]{9}")) {
			mobile = true;
		} else {
			mobile = false;
			throw new ITSException("Mobile number should be unique, not null and must be of 10 digits");
		}

		if (fname && lname && email && mobile) {
			userValid = true;
		} else {
			userValid = false;
		}

		return userValid;
	}

	public User convertToEntity(UserDTO userDTO) {
		return new User(userDTO);

	}

}
