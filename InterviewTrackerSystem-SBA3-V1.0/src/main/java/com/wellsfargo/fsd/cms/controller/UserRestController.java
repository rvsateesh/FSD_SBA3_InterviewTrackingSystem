package com.wellsfargo.fsd.cms.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wellsfargo.fsd.cms.entity.User;
import com.wellsfargo.fsd.cms.exception.ITSException;
import com.wellsfargo.fsd.cms.service.UserService;
import com.wellsfargo.fsd.cms.service.dto.UserDTO;

@RestController
@RequestMapping("/users")
public class UserRestController {

	@Autowired
	private UserService userService;

	@PostMapping()
	public ResponseEntity<User> addUser(@RequestBody UserDTO userDTO) throws ITSException {

		return userDTO.isUserValid(userDTO)
				? new ResponseEntity<User>(userService.addUser(userDTO.convertToEntity(userDTO)), HttpStatus.OK)
				: new ResponseEntity<User>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	@GetMapping
	public ResponseEntity<List<User>> findAllUsers() throws ITSException {
		return new ResponseEntity<List<User>>(userService.displayAllUsers(), HttpStatus.OK);
	}

	@DeleteMapping("/delete/{userId}")
	public ResponseEntity<Void> deleteUser(@PathVariable("userId") int userId) throws ITSException {
		userService.deleteUser(userId);
		return new ResponseEntity<Void>(HttpStatus.OK);
	}
}
