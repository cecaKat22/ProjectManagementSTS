package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.dto.EmployeeProject;
import com.jrp.pma.entities.Employee;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long>{
	
	@Override
	public List<Employee> findAll();
	
	@Query(nativeQuery = true, 
			value = "SELECT e.first_name as firstName, e.last_name as lastName, COUNT(pe.employee_id) as projectCount \r\n" + 
			"FROM employee e\r\n" + 
			" LEFT JOIN project_employee  pe ON (e.employee_id=pe.employee_id)\r\n" + 
			" group by e.first_name, e.last_name\r\n" + 
			" order by 3 desc")
	public List<EmployeeProject> employeeProjects();

	public Employee findByEmail(String value);

	

}
