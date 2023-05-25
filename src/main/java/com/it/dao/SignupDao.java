/*package com.it.dao;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.it.dao.entity.Signup;


@Service  //  it is creating bean of this class
public class SignupDao {
	
	//JdbcTemplate - who created this ->> spring boot
	@Autowired
	private  JdbcTemplate jdbcTemplate;
	

	public  void update(Signup signup) {
		String sql = "update empdetails set username=?,password=?,email=?,mobile=?,address=? where id = ?";
		Object[] data= {signup.getUsername(), signup.getPassword(), signup.getEmail(), Long.parseLong(signup.getMobile()),signup.getAddress(),signup.getPid()};
		jdbcTemplate.update(sql,data);
	}

	public  Signup findByPid(int pid) {
		String sql = "select id,username,password,email,mobile,address,doe from empdetails where id = ?";
		List<Signup> signupDTOs=jdbcTemplate.query(sql,new Object[] {pid}, new BeanPropertyRowMapper(Signup.class));
		return signupDTOs.get(0);
	}

	public  void deleteByPid(int pid) {
		String sql = "delete from empdetails where id = ?";
		jdbcTemplate.update(sql,new Object[] {pid});
	}

	public  void save(Signup signup) {
			String sql = "insert into empdetails(username,password,email,mobile,address,doe) values(?,?,?,?,?,?)";
			Timestamp doe = new Timestamp(new Date().getTime());
			Object[] data= {signup.getUsername(), signup.getPassword(), signup.getEmail(), Long.parseLong(signup.getMobile()),signup.getAddress(),doe};
			jdbcTemplate.update(sql,data);
	}
	
	
	public List<Signup> searchData(String searchText) {
		String sql = "select id,username,password,email,mobile,address,doe from empdetails where username like ? OR email like ? OR mobile like ?";
		String input="%" + searchText + "%";
		List<Signup> signups=jdbcTemplate.query(sql,new Object[] {input,input,input}, new BeanPropertyRowMapper(Signup.class));
		return signups;
	}

	public List<Signup> findAll() {
		String sql = "select id,username,password,email,mobile,address,doe from empdetails";
		List<Signup> signups=jdbcTemplate.query(sql,new BeanPropertyRowMapper(Signup.class));
		return signups;
	}

}*/
