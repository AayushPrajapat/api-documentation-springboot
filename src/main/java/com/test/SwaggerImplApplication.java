package com.test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.test.entities.Role;
import com.test.repositories.RoleRepository;

@SpringBootApplication
public class SwaggerImplApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SwaggerImplApplication.class, args);
	}

	@Value("${role.admin.Id}")
	private int adminId;

	@Value("${role.normal.Id}")
	private int normalId;

	@Autowired
	private RoleRepository roleRepository;

	@Override
	public void run(String... args) throws Exception {
/*
		Role role = new Role();
		role.setRoleId(normalId);
		role.setRoleName("ROLE_NORMAL");

		Role role2 = new Role();
		role2.setRoleId(adminId);
		role2.setRoleName("ROLE_ADMIN");

		Role savedNormalRole = this.roleRepository.save(role);
		Role savedAdminRole = this.roleRepository.save(role2);
		System.out.println(savedNormalRole + "" + savedAdminRole);
	
*/
	}

}
