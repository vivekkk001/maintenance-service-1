package com.assetmanagement.maintenance.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AssetApiResponse {

    private boolean success;
    private String message;
    private AssetResponseDTO data;
}