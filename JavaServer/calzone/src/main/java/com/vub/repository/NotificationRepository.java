package com.vub.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.vub.model.Notification;

/**
 * 
 * @author Nicolas
 *
 */
@Repository
public interface NotificationRepository extends JpaRepository<Notification, Integer> {

}