package com.contactmanager.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.contactmanager.entities.Subscriber;

public interface Subscriberrepository extends JpaRepository<Subscriber, Integer> {
    
}
