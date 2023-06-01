package com.it.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import com.it.dao.SignupRepository;
import com.it.dao.entity.Signup;
import com.it.dto.PatchDTO;
import com.it.dto.SignupDTO;
import com.it.exception.ResourceNotFoundException;

@Service
public class SignupService {
	
	@Value("${rest.api.base.url}")
	private String restBaseUrl;

	// If one bean wanted to use other bean
	@Autowired
	private SignupRepository signupDao;

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
		Signup signup = new Signup();
		BeanUtils.copyProperties(signupDTO, signup);
		signupDao.save(signup);
	}

	public SignupDTO findByPid(int pid) {
		java.util.Optional<Signup> optional= signupDao.findById(pid);
		if(optional.isEmpty()) {
			throw new ResourceNotFoundException("Signup","pid",pid);
		}
		SignupDTO signupDTO = new SignupDTO();
		BeanUtils.copyProperties(optional.get(), signupDTO);
		return signupDTO;
	}

	public void deleteByPid(int pid) {
		signupDao.deleteById(pid);
	}

	public int save(SignupDTO signupDTO) {
		Signup signup = new Signup();
		BeanUtils.copyProperties(signupDTO, signup);
		Signup dsignup = signupDao.save(signup);
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

	public List<SignupDTO> findAll()  {
		//WRITE CODE TO FETCH DATA FROM OTHER REST API
		//http://localhost:9090/v3/signups
		//REST API - I have to access data from REST API
		//URI= 
		List<SignupDTO> dtos = new ArrayList<>();
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<List<SignupDTO>> signupResponseList = restTemplate.exchange(
					restBaseUrl+"v3/signups", HttpMethod.GET, null,
					new ParameterizedTypeReference<List<SignupDTO>>() {
					});

			List<SignupDTO> externalList = signupResponseList.getBody();
			dtos.addAll(externalList);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	
		List<Signup> signups = signupDao.findAll();
		for (Signup signup : signups) {
			SignupDTO signupDTO = new SignupDTO();
			BeanUtils.copyProperties(signup, signupDTO);
			dtos.add(signupDTO);
		}
		return dtos;
	}

}
