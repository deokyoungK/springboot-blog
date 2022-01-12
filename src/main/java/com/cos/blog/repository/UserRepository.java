package com.cos.blog.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import com.cos.blog.model.User;


//DAO
//자동으로 bean등록이 된다.
//@Repository생략 가능하다.
public interface UserRepository extends JpaRepository<User, Integer>{
	
	//SELECT * FROM user WHERE username = ?
	Optional<User> findByUsername(String username);
}




//JPA Naming전략
//SELECT * FROM user WHERE username=? AND password=?;
//User findByUsernameAndPassword(String username, String password);