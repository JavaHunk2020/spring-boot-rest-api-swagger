package com.it.service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.client.RestTemplate;

import com.it.controller.AppResponse;
import com.it.dao.SignupRepository;
import com.it.dao.entity.Signup;
import com.it.dto.PatchDTO;
import com.it.dto.SignupDTO;
import com.it.dto.UserDTO;
import com.it.exception.ResourceNotFoundException;
import com.netflix.discovery.shared.Application;

@Service
public class SignupService {
	
	@Value("${rest.api.base.url}")
	private String restBaseUrl;
	
	@Value("${rest.login.base.url}")
	private String loginBaseUrl;

	// If one bean wanted to use other bean
	@Autowired
	private SignupRepository signupDao;
	
	@Autowired
	private RestTemplate restTemplate;
	
	
	 @Autowired
	    private DiscoveryClient discoveryClient;
	 
	 @PostConstruct
	 public void init() {
		 List<String> applications = discoveryClient.getServices();
		 System.out.println("#)#)");
	 }
	 
	 

	@Transactional
	public void patchUpdate(PatchDTO patchDTO) {
		Signup signup = signupDao.findById(patchDTO.getSid()).get();
		if (patchDTO.getAttributeName().equalsIgnoreCase("email")) {
			signup.setEmail(patchDTO.getValue());
		} else if (patchDTO.getAttributeName().equalsIgnoreCase("address")) {
			signup.setAddress(patchDTO.getValue());
		}
	}

	public void update(SignupDTO signupDTO) {
		if("technoweb".equalsIgnoreCase(signupDTO.getServiceName())) {
			Signup signup = new Signup();
			BeanUtils.copyProperties(signupDTO, signup);
			signupDao.save(signup);	
		}else {
			 //CALLING AGORA REST API
			 restTemplate.put(restBaseUrl+"/v3/signups", signupDTO);
		}
	}

	public SignupDTO findByPid(int pid,String serviceName) {
		SignupDTO signupDTO = new SignupDTO();
		if("technoweb".equalsIgnoreCase(serviceName)) {
			java.util.Optional<Signup> optional= signupDao.findById(pid);
			Signup  signup=optional.get();
			if(optional.isEmpty()) {
				throw new ResourceNotFoundException("Signup","pid",pid);
			}	
			BeanUtils.copyProperties(signup, signupDTO);
		} else {
	         //otherwise call agora api
			signupDTO = restTemplate.getForObject(restBaseUrl+"/v3/signups/"+pid, SignupDTO.class);
	    }	
		return signupDTO;
	}

	public void deleteByPid(int pid) {
		signupDao.deleteById(pid);
	}

	public int save(SignupDTO signupDTO) {
		Signup signup = new Signup();
		BeanUtils.copyProperties(signupDTO, signup);
		signup.setDoe(new Timestamp(new java.util.Date().getTime()));
		Signup dsignup = signupDao.save(signup);
		//Making TWO Rest callss
		/*
		 * @PostMapping("/signups")
		 * 
		 * @ResponseStatus(HttpStatus.CREATED) public SignupDTO
		 * createSignup(@RequestBody SignupDTO signupDTO) { int
		 * pid=signupService.save(signupDTO); signupDTO.setPid(pid); return signupDTO; }
		 */
		//Login api
		UserDTO userDTO=new UserDTO();
		userDTO.setUsername(signupDTO.getUsername());
		userDTO.setPassword(signupDTO.getPassword());
		ResponseEntity<AppResponse> result = restTemplate.postForEntity(loginBaseUrl+"/v4/cauth", userDTO, AppResponse.class);
		AppResponse  response= result.getBody();
		
		ResponseEntity<SignupDTO> resultSignup = restTemplate.postForEntity(restBaseUrl+"/v3/signups", signupDTO, SignupDTO.class);
		SignupDTO  response2= resultSignup.getBody();
		
		return dsignup.getPid();
	}

	public List<SignupDTO> searchData(String searchText) {
		searchText = "%" + searchText + "%";
		List<Signup> signups = signupDao.findByUsernameLikeOrEmailLikeOrMobileLike(searchText, searchText, searchText);
		List<SignupDTO> dtos = new ArrayList<>();
		for (Signup signup : signups) {
			SignupDTO signupDTO = new SignupDTO();
			BeanUtils.copyProperties(signup, signupDTO);
			dtos.add(signupDTO);
		}
		return dtos;
	}
	
	
	public boolean verfiyUser(UserDTO userDTO)  {
		//WRITE CODE TO FETCH DATA FROM OTHER REST API
		//http://localhost:9090/v3/signups
		//REST API - I have to access data from REST API
		//URI= 
		System.out.println("REST API IS BEING CALLED!!!!!!!!!!!!!!!!");
		List<SignupDTO> dtos = new ArrayList<>();
		try {
			
			ResponseEntity<AppResponse> result = restTemplate.postForEntity(loginBaseUrl+"/v4/auth", userDTO, AppResponse.class);
			AppResponse  response= result.getBody();
		} catch (Exception ex) {
			ex.printStackTrace();
			return false;
		}
		return true;
	}		

	public List<SignupDTO> findAll()  {
		//WRITE CODE TO FETCH DATA FROM OTHER REST API
		//http://localhost:9090/v3/signups
		//REST API - I have to access data from REST API
		//URI= 
		System.out.println("REST API IS BEING CALLED!!!!!!!!!!!!!!!!");
		List<SignupDTO> dtos = new ArrayList<>();
		try {
			ResponseEntity<List<SignupDTO>> signupResponseList = this.restTemplate.exchange(
					restBaseUrl+"/v3/signups", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<SignupDTO>>() {
					});

			List<SignupDTO> externalList = signupResponseList.getBody();
			for(SignupDTO signupDTO : externalList) {
				signupDTO.setServiceName("agora");
			}
			dtos.addAll(externalList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		List<Signup> signups = signupDao.findAll();
		for (Signup signup : signups) {
			SignupDTO signupDTO = new SignupDTO();
			BeanUtils.copyProperties(signup, signupDTO);
			signupDTO.setServiceName("technoweb");
			dtos.add(signupDTO);
		}
		
		System.out.println("REST API IS  CALLED SUCCESFULLY!!!!!!!!!!!!!!!!");
		return dtos;
	}

}
