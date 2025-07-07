package com.test.services;

import java.util.List;
import com.test.entities.User;

public interface UserService {
	
//	create
	User createUser(User user);
//  update
	User updateUser(int id,User user);
//	delete
	void deleteUser(int id);
//	getAll
	List<User> getAll();
//	getById
	User getById(int id);
//	getByEmail
	User getByEmail(String email);
	

	
	

	
}
