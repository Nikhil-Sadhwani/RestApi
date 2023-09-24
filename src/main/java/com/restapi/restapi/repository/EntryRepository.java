package com.restapi.restapi.repository;

import com.restapi.restapi.entity.EntryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EntryRepository extends JpaRepository<EntryEntity, Long> {

    List<EntryEntity> findByshipperId(Long id);

}