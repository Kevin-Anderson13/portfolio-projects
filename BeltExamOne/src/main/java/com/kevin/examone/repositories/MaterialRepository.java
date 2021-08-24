package com.kevin.examone.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.kevin.examone.models.Material;

@Repository
public interface MaterialRepository extends CrudRepository<Material, Long> {
	List<Material> findAll();
}
