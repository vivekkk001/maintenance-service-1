package com.assetmanagement.maintenance.client;

import com.assetmanagement.maintenance.dto.AssetApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

@FeignClient(name = "asset-service")
public interface AssetClient {

    @GetMapping("/assets/{id}")
    AssetApiResponse getAssetById(
            @PathVariable("id") UUID id
    );
}