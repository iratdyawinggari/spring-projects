package com.enigma.finalproject.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.web.bind.annotation.RequestParam;

import com.enigma.finalproject.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {

	User findByUsernameAndPassword(String username, String password);

	User findByUsername(String username);

	@Query("SELECT u from User u where u.id=:id")
	User findUserById(@RequestParam("id") Integer id);

	Page<User> findByUsernameContains(String username, Pageable paging);

	Page<User> findByUserInfoFullnameContains(String fullname, Pageable paging);

	Page<User> findByUserInfoAddressContains(String address, Pageable paging);

	Page<User> findByUserInfoEmailContains(String email, Pageable paging);

	Page<User> findByUserInfoNoTelpContains(String noTelp, Pageable paging);

}
