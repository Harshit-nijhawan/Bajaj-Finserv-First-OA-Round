package com.bajaj.bfhl.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Data Transfer Object for BFHL GET Response.
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class GetOperationResponse {

    @JsonProperty("operation_code")
    private int operationCode;
}
