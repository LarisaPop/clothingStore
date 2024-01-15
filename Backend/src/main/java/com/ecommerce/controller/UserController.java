package com.ecommerce.controller;

import java.util.List;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.model.User;
import com.ecommerce.service.UserService;

@RestController
@RequestMapping(value = "/api")
@CrossOrigin(origins = "*")
public class                                                                                                                                                                                                            UserController {

	@Autowired
	private UserService userService;
    @ApiOperation(value="Add a new user")
	@PostMapping("/addUser")
	User addUser(@RequestBody User user) {
		return userService.addUser(user);
	}
    @ApiOperation(value="Return a list of users")
	@GetMapping("/admin/findAllUsers")
	public List<User> findAllUsers() {
		return userService.findAllUsers();
	}

	@ApiOperation(value="Modify user proprietes")
	@PutMapping("/editUser/{id}")
	User editUser(@RequestBody User user,@ApiParam("Id user") @PathVariable long id) {
		return userService.editUser(user, id);
	}

	@ApiOperation(value="Search for user")
	@GetMapping("/findUserById/{id}")
	User findUserById(@PathVariable long id) {
		return userService.findUserById(id);
	}

	@ApiOperation(value="Delete user by id")
	@DeleteMapping("/deleteUser/{id}")
	void deleteUser(@PathVariable long id) {
		userService.deleteUser(id);
	}

	@ApiOperation(value="Search user by name")
	@GetMapping("/findByUsername/{username}")
	User findByUsername(@PathVariable String username) {
		return userService.findByUsername(username);
	}

	@GetMapping("/export/{id}")
	public ResponseEntity exportUsersDetails(@PathVariable("id") long id, @RequestParam("fileType") String fileType){
		return ResponseEntity.ok(userService.exportUsersDetails(id,fileType));
	}
}
