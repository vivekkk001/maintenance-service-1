package com.assetmanagement.maintenance.service;

import com.assetmanagement.maintenance.client.AssetClient;
import com.assetmanagement.maintenance.dto.AssetApiResponse;
import com.assetmanagement.maintenance.dto.MaintenanceRequestDTO;
import com.assetmanagement.maintenance.dto.MaintenanceResponseDTO;
import com.assetmanagement.maintenance.dto.StatusUpdateDTO;
import com.assetmanagement.maintenance.entity.MaintenanceRequest;
import com.assetmanagement.maintenance.entity.Status;
import com.assetmanagement.maintenance.exception.MaintenanceNotFoundException;
import com.assetmanagement.maintenance.repository.MaintenanceRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MaintenanceService {

    private final MaintenanceRepository maintenanceRepository;
    private final AssetClient assetClient;


    public List<MaintenanceResponseDTO> getAllRequests() {

        return maintenanceRepository.findAll()
                .stream()
                .map(this::mapToDTO)
                .toList();
    }

    public MaintenanceResponseDTO updateStatus(
            Long id,
            StatusUpdateDTO dto
    ) {

        MaintenanceRequest request = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException(
                                "Maintenance Request not found with id: " + id
                        ));

        request.setStatus(dto.getStatus());

        if (dto.getStatus() == Status.COMPLETED) {
            request.setResolvedAt(LocalDateTime.now());
        }

        MaintenanceRequest saved =
                maintenanceRepository.save(request);

        return mapToDTO(saved);
    }

    public void deleteRequest(Long id) {

        MaintenanceRequest request = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException(
                                "Maintenance Request not found with id: " + id
                        ));

        maintenanceRepository.delete(request);
    }

    private MaintenanceResponseDTO mapToDTO(
            MaintenanceRequest request
    ) {

        return MaintenanceResponseDTO.builder()
                .id(request.getId())
                .assetId(request.getAssetId())
                .reportedByEmail(request.getReportedByEmail())
                .issueDescription(request.getIssueDescription())
                .priority(request.getPriority())
                .status(request.getStatus())
                .createdAt(request.getCreatedAt())
                .resolvedAt(request.getResolvedAt())
                .build();
    }

    public MaintenanceResponseDTO getRequestById(Long id) {

        MaintenanceRequest request = maintenanceRepository.findById(id)
                .orElseThrow(() ->
                        new MaintenanceNotFoundException(
                                "Maintenance Request not found with id: " + id
                        ));

        return mapToDTO(request);
    }

    public MaintenanceResponseDTO createRequest(
            MaintenanceRequestDTO dto
    ) {

        AssetApiResponse assetResponse =
                assetClient.getAssetById(dto.getAssetId());

        if (assetResponse == null ||
                assetResponse.getData() == null) {

            throw new RuntimeException(
                    "Asset validation failed for asset: "
                            + dto.getAssetId()
            );
        }

        MaintenanceRequest request = MaintenanceRequest.builder()
                .assetId(dto.getAssetId())
                .reportedByEmail(dto.getReportedByEmail())
                .issueDescription(dto.getIssueDescription())
                .priority(dto.getPriority())
                .status(Status.OPEN)
                .createdAt(LocalDateTime.now())
                .build();

        MaintenanceRequest saved =
                maintenanceRepository.save(request);

        return mapToDTO(saved);
    }
}