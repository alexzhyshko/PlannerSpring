package com.zhyshko.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zhyshko.model.Notification;


@Repository
public interface NotificationRepository extends CrudRepository<Notification, UUID>{


}
