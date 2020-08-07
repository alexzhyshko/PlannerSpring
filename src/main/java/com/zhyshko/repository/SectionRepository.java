package com.zhyshko.repository;

import java.util.UUID;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.zhyshko.model.Section;


@Repository
public interface SectionRepository extends CrudRepository<Section, UUID>{


}
