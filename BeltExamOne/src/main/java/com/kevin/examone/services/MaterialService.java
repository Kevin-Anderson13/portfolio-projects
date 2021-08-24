package com.kevin.examone.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kevin.examone.models.Material;
import com.kevin.examone.models.User;
import com.kevin.examone.repositories.MaterialRepository;

@Service
public class MaterialService {
	@Autowired
	private MaterialRepository mRepository;
	
	//Show All
	public List<Material> allMaterials(){
		return this.mRepository.findAll();
	}
	
	//Create
	public Material createMaterial(Material material){
		return this.mRepository.save(material);
	}
	
	//Read
	public Material getOneMaterial(Long id) {
		return this.mRepository.findById(id).orElse(null);
	}
	
	//Update
	public Material updateMaterial(Long id, Material material) {
		return this.mRepository.save(material);
	}
	
	//Delete
	public void deleteMaterial(Long id) {
		this.mRepository.deleteById(id);
	}
	
	//Add user that likes
	public void addUserLike(Material material, User user) {
		List<User> currentLikes = material.getUsersWhoLiked();
		currentLikes.add(user);
		this.mRepository.save(material);
	}
	
	//Remove user that likes
		public void removeUserLike(Material material, User user) {
			List<User> currentLikes = material.getUsersWhoLiked();
			currentLikes.remove(user);
			this.mRepository.save(material);
		}

}
