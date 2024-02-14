package net.javaguides.ems.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import net.javaguides.ems.entity.Employee;

public interface EmployeeRepositroy extends JpaRepository<Employee, Long> {
	
	/*
	 JpaRepository extends ListCrudRepository
	 
	 ListCrudRepository extends CrudRepository
	 
	 CrudRepository extends Repository
	 
	 
	 simpleRepository implements JpaRepository interface
	 simpleRepository is a class. it has annotation of @Repository, @Transactional
	 
	 */

}
