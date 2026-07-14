package com.assetmanagement.maintenance.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class AssetResponseDTO {

    private UUID id;
    private String assetName;
    private String assetType;
    private String serialNumber;
    private String status;
}