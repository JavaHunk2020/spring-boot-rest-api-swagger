package com.it.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.it.dao.SignupRepository;
import com.it.dao.entity.Signup;
import com.it.dto.SignupDTO;

@Service
public class SignupService {
	
	
	// If one bean wanted to use other bean
	@Autowired
	private SignupRepository signupDao;

		public  void update(SignupDTO signupDTO) {
			Signup signup=new Signup();
			BeanUtils.copyProperties(signupDTO, signup);
			signupDao.save(signup);
		}

		public  SignupDTO findByPid(int pid) {
		   Signup  signup=signupDao.findById(pid).get();
			SignupDTO signupDTO=new SignupDTO();
			BeanUtils.copyProperties(signup, signupDTO);
			return signupDTO;
		}

		public  void deleteByPid(int pid) {
			signupDao.deleteById(pid);
		}

		public  int save(SignupDTO signupDTO) {
			Signup signup=new Signup();
			BeanUtils.copyProperties(signupDTO, signup);
			Signup dsignup=signupDao.save(signup);
			return dsignup.getPid();
		}
		
		
		public List<SignupDTO> searchData(String searchText) {
			searchText="%"+searchText+"%";
			List<Signup> signups=signupDao.findByUsernameLikeOrEmailLikeOrMobileLike(searchText,searchText,searchText);
			List<SignupDTO> dtos=new ArrayList<>();
			for(Signup signup:signups) {
				SignupDTO signupDTO=new SignupDTO();
				BeanUtils.copyProperties(signup, signupDTO);
				dtos.add(signupDTO);
			}
			return dtos;
		}

		public List<SignupDTO> findAll() {
			List<Signup> signups=signupDao.findAll();
			List<SignupDTO> dtos=new ArrayList<>();
			for(Signup signup:signups) {
				SignupDTO signupDTO=new SignupDTO();
				BeanUtils.copyProperties(signup, signupDTO);
				dtos.add(signupDTO);
			}
			return dtos;
		}


}
