package com.ganesh.heycar.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ganesh.heycar.model.VehicleEntity;
@Repository
public interface VehicleRepository extends JpaRepository<VehicleEntity, String> {

}
