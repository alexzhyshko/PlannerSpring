package com.zhyshko.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zhyshko.model.Card;


@Repository
public interface CardRepository extends CrudRepository<Card, UUID>{


}
