package com.bajaj.bfhl.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * Data Transfer Object for BFHL POST Request.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BfhlRequest {

    @NotNull(message = "Input data array cannot be null")
    private List<String> data;
}
