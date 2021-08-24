package com.kevin.examone.controllers;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.kevin.examone.models.Material;
import com.kevin.examone.models.User;
import com.kevin.examone.services.MaterialService;
import com.kevin.examone.services.UserService;
import com.kevin.examone.validators.UserValidator;

@Controller
public class HomeController {
	@Autowired
	private UserService uService;
	@Autowired
	private MaterialService mService;
	@Autowired
	private UserValidator uValidator;
	
	@GetMapping("/")
	public String homePage(@ModelAttribute("user") User user) {
		return "home.jsp";
	}
	
	@PostMapping("/register")
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result, HttpSession session) {
		uValidator.validate(user, result);
		if(result.hasErrors()) {
			return "home.jsp";
		}
		User newUser = this.uService.createUser(user);
		session.setAttribute("user_sessionId", newUser.getId());
		return "redirect:/ideas";
	}
	
	@PostMapping("/signin")
	public String signIn(@RequestParam("semail") String email, @RequestParam("spassword") String password, RedirectAttributes redirectAttribute, HttpSession session) {
		if(!this.uService.authenticateUser(email, password)) {
			redirectAttribute.addFlashAttribute("signinError", "Invalid Credentials");
			return "redirect:/";
		}
		
		User user = this.uService.findByEmail(email);
		session.setAttribute("user_sessionId", user.getId());
		return "redirect:/ideas";
	}
	
	@GetMapping("/signout")
	public String signOut(HttpSession session) {
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("/ideas")
	public String materialDash(Model viewModel, HttpSession session) {
		if((Long)session.getAttribute("user_sessionId") == null) {
			return "redirect:/";
		}
		Long currentUser = (Long)session.getAttribute("user_sessionId");
		viewModel.addAttribute("user", this.uService.getOneUser(currentUser));
		viewModel.addAttribute("allMaterials", this.mService.allMaterials());
		return "dashboard.jsp";
	}
	
	@GetMapping("ideas/new")
	public String addNewIdea(@ModelAttribute("newIdea")Material material, Model viewModel, HttpSession session) {
		Long loggedInUser = (Long)session.getAttribute("user_sessionId");
		viewModel.addAttribute("userId", loggedInUser);
		return "newIdea.jsp";
	}
	
	@PostMapping("/ideas/new")
	public String newIdea(@Valid @ModelAttribute("newIdea")Material material, BindingResult result, Model viewModel, HttpSession session) {
		if(result.hasErrors()) {
			return "newIdea.jsp";
		}
			mService.createMaterial(material);
			return "redirect:/ideas";
		}
	
	@GetMapping("/ideas/{id}")
	public String singleMaterial(@PathVariable("id") Long id, Model viewModel) {
		viewModel.addAttribute("material", this.mService.getOneMaterial(id));
		viewModel.addAttribute("user", this.uService.getOneUser(id));
		return "material.jsp";
	}
	
	@GetMapping("/ideas/{id}/edit")
	public String editMaterial(@ModelAttribute("editMaterial") Material material, @PathVariable("id") Long id, Model viewModel, HttpSession session, RedirectAttributes redirectAttr) {
		User u = this.uService.getOneUser((Long)session.getAttribute("user_sessionId"));
		Material m = this.mService.getOneMaterial(id);
		viewModel.addAttribute("editMaterial", this.mService.getOneMaterial(id));
		Long loggedInUser = (Long)session.getAttribute("user_sessionId");
		viewModel.addAttribute("userId", loggedInUser);
		if(u != m.getUser()) {
			redirectAttr.addFlashAttribute("error", "Cant edit, you didn't create!");
			return "redirect:/ideas";
		}
		
		return "edit.jsp";
	}
	
	@PostMapping("/ideas/{id}/edit")
	public String updateMaterial(@Valid @ModelAttribute("editMaterial") Material material, BindingResult result, @PathVariable("id") Long id, Model viewModel) {
		if(result.hasErrors()) {
			viewModel.addAttribute("material", this.mService.getOneMaterial(id));
			return "edit.jsp";
		} 
			this.mService.updateMaterial(id, material);
			return "redirect:/ideas";
		}	
	
	@GetMapping("ideas/{id}/delete")
	public String deleteMaterial(@PathVariable("id") Long id) {
		if(mService.getOneMaterial(id) != null) {
			mService.deleteMaterial(id);
		}
		return "redirect:/ideas";
	}
	
	
	
	@GetMapping("/like/{id}")
	public String like(@PathVariable("id") Long id, HttpSession session) {
		Long UserId = (Long)session.getAttribute("user_sessionId");
		Long materialId = id;
		User userWhoLiked = this.uService.getOneUser(UserId);
		Material likedMaterial = this.mService.getOneMaterial(materialId);
		this.mService.addUserLike(likedMaterial, userWhoLiked);
		return "redirect:/ideas";
	}
	
	@GetMapping("/unlike/{id}")
	public String unLike(@PathVariable("id") Long id, HttpSession session) {
		Long UserId = (Long)session.getAttribute("user_sessionId");
		Long materialId = id;
		User userWhoLiked = this.uService.getOneUser(UserId);
		Material likedMaterial = this.mService.getOneMaterial(materialId);
		this.mService.removeUserLike(likedMaterial, userWhoLiked);
		return "redirect:/ideas";
	}
}
