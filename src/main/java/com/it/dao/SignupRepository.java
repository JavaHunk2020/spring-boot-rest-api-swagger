package com.it.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.it.dao.entity.Signup;

public interface SignupRepository extends JpaRepository<Signup, Integer> {
	
	public List<Signup> findByUsernameLikeOrEmailLikeOrMobileLike(String username,String email,String mobile);

}
