package com.test.services.Impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.test.entities.Role;
import com.test.entities.User;
import com.test.repositories.RoleRepository;
import com.test.repositories.UserRepository;
import com.test.services.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Value("${role.normal.Id}")
	private int normalRoleId;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private RoleRepository roleRepository;

	@Override
	public User createUser(User user) {
		
		Role role = this.roleRepository.findById(normalRoleId).get();
		user.setPassword(passwordEncoder.encode(user.getPassword()));
		user.getRoles().add(role);
	
		User savedUser = this.userRepository.save(user);
		
		return savedUser;
	}

	@Override
	public User updateUser(int userId, User user) {
		// Find the user by id
		User user2 = this.userRepository.findById(userId);
		user2.setName(user.getName());
		user2.setEmail(user.getEmail());
		user2.setPassword(user.getPassword());
		user2.setAbout(user.getAbout());

		User updatedUser = this.userRepository.save(user2);

		return updatedUser;
	}

	@Override
	public void deleteUser(int userId) {
		// Find the user by id
		User user = this.userRepository.findById(userId);

		// Delete the user
		this.userRepository.delete(user);
	}

	@Override
	public List<User> getAll() {
		List<User> users = this.userRepository.findAll();
		return users;
	}

	@Override
	public User getById(int id) {
		User user = this.userRepository.findById(id);
		return user;
	}

	@Override
	public User getByEmail(String email) {
		User user = this.userRepository.findByEmail(email);
		return user;
	}

}
