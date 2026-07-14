package com.assetmanagement.maintenance.dto;

import com.assetmanagement.maintenance.entity.Priority;
import com.assetmanagement.maintenance.entity.Status;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceResponseDTO {

    private Long id;
    private UUID assetId;
    private String reportedByEmail;
    private String issueDescription;
    private Priority priority;
    private Status status;
    private LocalDateTime createdAt;
    private LocalDateTime resolvedAt;
}