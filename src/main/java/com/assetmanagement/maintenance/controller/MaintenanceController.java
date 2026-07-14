package com.assetmanagement.maintenance.controller;

import com.assetmanagement.maintenance.dto.MaintenanceRequestDTO;
import com.assetmanagement.maintenance.dto.MaintenanceResponseDTO;
import com.assetmanagement.maintenance.dto.StatusUpdateDTO;
import com.assetmanagement.maintenance.entity.MaintenanceRequest;
import com.assetmanagement.maintenance.service.MaintenanceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@RestController
@RequestMapping("/maintenance")
@RequiredArgsConstructor
@Tag(
        name = "Maintenance API",
        description = "APIs for managing asset maintenance requests and maintenance lifecycle"
)
public class MaintenanceController {

    private final MaintenanceService maintenanceService;

    @Operation(
            summary = "Create maintenance request",
            description = "Creates a new maintenance request for an asset. " +
                    "The request is automatically created with OPEN status."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Maintenance request created successfully"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Invalid maintenance request"
            )
    })
    @PostMapping
    public ResponseEntity<MaintenanceResponseDTO> createMaintenanceRequest(
            @Valid @RequestBody MaintenanceRequestDTO dto
    ) {

        MaintenanceResponseDTO response =
                maintenanceService.createRequest(dto);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    @Operation(
            summary = "Get all maintenance requests",
            description = "Returns all maintenance requests managed by the Maintenance Service."
    )
    @ApiResponse(
            responseCode = "200",
            description = "Maintenance requests retrieved successfully"
    )
    @GetMapping
    public ResponseEntity<List<MaintenanceResponseDTO>> getAllRequests() {

        return ResponseEntity.ok(
                maintenanceService.getAllRequests()
        );
    }


    @Operation(
            summary = "Get maintenance request by ID",
            description = "Returns a maintenance request using its unique maintenance request ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Maintenance request found"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Maintenance request not found"
            )
    })
    @GetMapping("/{id}")
    public ResponseEntity<MaintenanceResponseDTO> getRequestById(
            @PathVariable Long id
    ) {

        return ResponseEntity.ok(
                maintenanceService.getRequestById(id)
        );
    }


    @Operation(
            summary = "Update maintenance status",
            description = "Updates the lifecycle status of a maintenance request. " +
                    "When status becomes COMPLETED, the resolution timestamp is automatically recorded."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Maintenance status updated successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Maintenance request not found"
            )
    })
    @PutMapping("/{id}/status")
    public ResponseEntity<MaintenanceResponseDTO> updateStatus(
            @PathVariable Long id,
            @RequestBody StatusUpdateDTO dto
    ) {

        return ResponseEntity.ok(
                maintenanceService.updateStatus(id, dto)
        );
    }

    @Operation(
            summary = "Delete maintenance request",
            description = "Deletes an existing maintenance request using its ID."
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Maintenance request deleted successfully"
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Maintenance request not found"
            )
    })
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRequest(
            @PathVariable Long id
    ) {

        maintenanceService.deleteRequest(id);

        return ResponseEntity.noContent().build();
    }
}