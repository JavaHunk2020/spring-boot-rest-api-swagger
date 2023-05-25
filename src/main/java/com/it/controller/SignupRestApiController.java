package com.it.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.it.dto.SignupDTO;
import com.it.service.SignupService;

@RestController
@RequestMapping("/v3")
public class SignupRestApiController {

	// If one bean wanted to use other bean
	@Autowired
	private SignupService signupService;
	
	
	@PostMapping("/signups")
	public SignupDTO createSignup(@RequestBody SignupDTO signupDTO) {
		int pid=signupService.save(signupDTO);
		signupDTO.setPid(pid);
		return signupDTO;
	}
 
	///v3/signups 
	@GetMapping("/signups")
	@ResponseStatus(HttpStatus.OK)
	public List<SignupDTO> showSignups() {
		//Jackson mapper framework
		List<SignupDTO> dtos = signupService.findAll();
		return dtos;
	}
	//http://localhost:8080/v3/signups/2
	@GetMapping("/signups/{sid}")
	@ResponseStatus(HttpStatus.OK)
	public SignupDTO showSignup(@PathVariable("sid") int sid) {
		//Jackson mapper framework
		SignupDTO signupDTO = signupService.findByPid(sid);
		return signupDTO;
	}
	
	@DeleteMapping("signups/{sid}")
	public AppResponse deleteSignup(@PathVariable("sid") int pid) {
		signupService.deleteByPid(pid);
		AppResponse appResponse=new AppResponse();
		appResponse.setCode("100c");
		appResponse.setMessage("Hey! resource is deleted successfully.");
		return appResponse;
	}

}
