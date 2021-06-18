package com.contactmanager.dao;

import com.contactmanager.entities.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface userrepo extends JpaRepository<User,Integer>{
    
}