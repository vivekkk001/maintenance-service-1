package com.assetmanagement.maintenance.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor // This allows new StatusUpdateDTO("AVAILABLE")
@NoArgsConstructor  // This allows Spring/JSON to create empty objects
public class StatusUpdateDTO {
    private String status;
}