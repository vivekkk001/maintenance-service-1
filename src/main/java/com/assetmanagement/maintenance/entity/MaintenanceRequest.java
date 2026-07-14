package com.assetmanagement.maintenance.entity;
import com.assetmanagement.maintenance.entity.Priority;
import com.assetmanagement.maintenance.entity.Status;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "maintenance_requests")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID assetId;

    private String reportedByEmail;

    @Column(nullable = false)
    private String issueDescription;

    @Enumerated(EnumType.STRING)
    private Priority priority;

    @Enumerated(EnumType.STRING)
    private Status status;

    private LocalDateTime createdAt;

    private LocalDateTime resolvedAt;
}