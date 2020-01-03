package com.ur.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ur.domain.Store;

@Repository
public interface PlaceRepository extends JpaRepository<Store, Long> {

}
