package com.project.www.repo;

	import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.project.www.model.Employee;

	@Repository
	/*If a user with the specified login is found in the database, the Optional will contain the user object.
	If no user is found with the specified login, the Optional will be empty (contain no value)
	*/
	public interface EmployeeRepository extends JpaRepository<Employee, Long>
	{
		 Optional<Employee> findByLogin(String login);
	}
