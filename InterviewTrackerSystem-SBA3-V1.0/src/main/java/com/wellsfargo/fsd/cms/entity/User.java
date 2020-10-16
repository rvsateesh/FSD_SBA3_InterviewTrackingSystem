package com.wellsfargo.fsd.cms.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.wellsfargo.fsd.cms.service.dto.UserDTO;

@Entity
@Table(name = "user")
public class User {

	@Id
	@Column(name = "userId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	// @NotNull(message = "User Id is requried")
	private int userId;

	@Column(name = "firstName")
	// @NotNull(message = "First Name is requried")
	// @Size(min = 5, max = 30, message = "First Name length should be in between 5
	// characters to 30 characters")
	private String firstName;

	@Column(name = "lastName")
	// @NotNull(message = "Last Name is requried")
	// @Size(min = 3, max = 25, message = "Last Name length should be in between 3
	// characters to 25 characters")
	private String lastName;

	@Column(name = "email")
	// @NotNull(message = "Email is requried")
	// @Pattern(regexp = "[a-zA-Z][a-zA-Z0-9._]*@[a-zA-Z0-9]+([.][a-zA-Z]+)+")
	private String email;

	@Column(name = "mobile")
	// @NotNull(message = "Mobile number is requried")
	// @Pattern(regexp = "[1-9][0-9]{9}", message = "Mobile Number should be of 10
	// digits only")
	private String mobile;

	public User(int userId, String firstName, String lastName, String email, String mobile) {
		this.userId = userId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.mobile = mobile;
	}

	public User(UserDTO userDTO) {
		this.userId = userDTO.userId;
		this.firstName = userDTO.firstName;
		this.lastName = userDTO.lastName;
		this.email = userDTO.email;
		this.mobile = userDTO.mobile;
	}

	public User() {
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "User [userId=" + userId + ", firstName=" + firstName + ", lastName=" + lastName + ", email=" + email
				+ ", mobile=" + mobile + "]";
	}

}
