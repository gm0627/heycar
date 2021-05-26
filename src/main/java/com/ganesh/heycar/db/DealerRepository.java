package com.ganesh.heycar.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.heycar.model.DealerEntity;

@Repository
public interface DealerRepository extends JpaRepository<DealerEntity, Long> {

}
