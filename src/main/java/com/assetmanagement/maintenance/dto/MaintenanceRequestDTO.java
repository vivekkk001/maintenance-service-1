package com.assetmanagement.maintenance.dto;

import com.assetmanagement.maintenance.entity.Priority;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaintenanceRequestDTO {

    @NotNull(message = "Asset ID is required")
    private UUID assetId;

    @NotNull(message = "Reported By user email is required")
    private String reportedByEmail;

    @NotBlank(message = "Issue description is required")
    @Size(
            min = 5,
            max = 500,
            message = "Issue description must be between 5 and 500 characters"
    )
    private String issueDescription;

    @NotNull(message = "Priority is required")
    private Priority priority;
}