package com.contactmanager.dao;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.contactmanager.entities.User;

public interface detailrepo extends JpaRepository<User,Integer>{
	public interface UserRepository {
		@Query("select u from DetailInfo u where u.id = :id")
		public detailrepo getUserByUserName(@Param("id") int id);
	}
}
