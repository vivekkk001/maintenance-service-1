package com.assetmanagement.maintenance.repository;

import com.assetmanagement.maintenance.entity.MaintenanceRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository
        extends JpaRepository<MaintenanceRequest, Long> {

}