package com.assetmanagement.maintenance.client;

import com.assetmanagement.maintenance.dto.AssetApiResponse;
import com.assetmanagement.maintenance.dto.StatusUpdateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.UUID;

@FeignClient(name = "asset-service")
public interface AssetClient {

    @GetMapping("/assets/{id}")
    AssetApiResponse getAssetById(
            @PathVariable("id") UUID id
    );

    @PatchMapping("/assets/{id}/status")
    void updateAssetStatus(@PathVariable("id") UUID id, @RequestBody StatusUpdateDTO request);
}