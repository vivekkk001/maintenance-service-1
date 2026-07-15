package com.assetmanagement.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogRequestDTO {
    private UUID assetId;
    private String action;
    private String details;
}