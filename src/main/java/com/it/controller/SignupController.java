package com.it.controller;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.it.dto.SignupDTO;
import com.it.dto.UserDTO;
import com.it.service.SignupService;

@Controller
public class SignupController {

	// If one bean wanted to use other bean
	@Autowired
	private SignupService signupService;
	
	@PostMapping("/auth")
	public @ResponseBody  AppResponse checkUser(@RequestBody  UserDTO userDTO) {
		boolean result=signupService.verfiyUser(userDTO);
		AppResponse appResponse=new AppResponse();
		if(result) {
			appResponse.setCode("200");
			appResponse.setMessage("success");
		}else {
			appResponse.setCode("404");
			appResponse.setMessage("Username and password are not correct");
		}
		return appResponse;
	}

	
	@GetMapping("/auth")
	public String showLogin() {
		return "login";
	}
	
	
	@PostMapping("/login")
	public String login(@ModelAttribute UserDTO userDTO,Model model) {
		boolean result=signupService.verfiyUser(userDTO);
		if(result) {
			return "home";
		}else {
			model.addAttribute("message","Username and password are not correct");
			return "login";
		}
	}
	

	@GetMapping("/signups")
	public String showSignups(Model model) {
		List<SignupDTO> dtos = signupService.findAll();
		// Adding my data inside request scope
		model.addAttribute("signups", dtos);
		return "showSignup";
	}

	@GetMapping({ "/csignup", "/" })
	public String showSignup() {
		return "signup";
	}
	
	
	@PostMapping("/csignup")
	public String createSignup(@ModelAttribute SignupDTO signupDTO,Model model) {
		signupService.save(signupDTO);
		model.addAttribute("message","Data is saved");
		return "signup";
	}
	
	@PostMapping("/esignup")
	public String update(@ModelAttribute SignupDTO signupDTO,Model model) {
		
		
		List<SignupDTO> dtos = signupService.findAll();
		 model.addAttribute("signups", dtos);
		//I have to go to next JSP
		 return "showSignup";
	}
	
	
	@GetMapping("esignup")
	public String editUpdate(@RequestParam int pid,@ModelAttribute SignupDTO signupDTO,Model model) {
		 
		SignupDTO dto =signupService.findByPid(pid);
		model.addAttribute("dto", dto);
		
		return "esignup";
	}
	

	@GetMapping("dsignup")
	public String deleteSignup(@RequestParam int pid) {
		signupService.deleteByPid(pid);
		return "redirect:/signups";
	}

	@GetMapping({ "/seachRecords" })
	public String search(@RequestParam String searchText, Model model) {
		if (searchText != null) {
			searchText = searchText.trim();
		}
		List<SignupDTO> dtos = signupService.searchData(searchText);
		model.addAttribute("signups", dtos);
		return "showSignup";

	}

	@GetMapping("sortData")
	public String sortSignup(@RequestParam String sort,@RequestParam String orderBy,Model model) {
		// FETCHING REMAINING DATA
		List<SignupDTO> dtos = signupService.findAll();
		if ("email".equalsIgnoreCase(sort) && "asc".equalsIgnoreCase(orderBy)) {
			// This is an example of anonymous class - class with out name
			Collections.sort(dtos, new Comparator<SignupDTO>() {
				@Override
				public int compare(SignupDTO o1, SignupDTO o2) {
					// Ascending order
					return o1.getEmail().compareTo(o2.getEmail());
				}
			});
		} else if ("email".equalsIgnoreCase(sort) && "desc".equalsIgnoreCase(orderBy)) {
			// This is an example of anonymous class - class with out name
			Collections.sort(dtos, new Comparator<SignupDTO>() {
				@Override
				public int compare(SignupDTO o1, SignupDTO o2) {
					// Ascending order
					return o2.getEmail().compareTo(o1.getEmail());
				}
			});
		} else if ("username".equalsIgnoreCase(sort)) {
			// This is an example of anonymous class - class with out name
			Collections.sort(dtos, new Comparator<SignupDTO>() {
				@Override
				public int compare(SignupDTO o1, SignupDTO o2) {
					// Ascending order
					return o1.getUsername().compareTo(o2.getUsername());
				}
			});
		}
		// Adding my data inside request scope
		model.addAttribute("signups", dtos);
		// I have to go to next JSP
		return "showSignup";
	}

}
