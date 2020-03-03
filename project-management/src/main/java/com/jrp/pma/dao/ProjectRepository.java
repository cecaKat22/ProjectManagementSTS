package com.jrp.pma.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.jrp.pma.dto.ChartData;
import com.jrp.pma.entities.Project;

public interface ProjectRepository extends PagingAndSortingRepository<Project, Long>{
	@Override
	public List<Project> findAll();
	
	@Query(nativeQuery=true, value="select stage as label, count(*) as value\r\n" + 
			" from project\r\n" + 
			" group by stage")
	public List<ChartData> getProjectStatus();
	
	

}
