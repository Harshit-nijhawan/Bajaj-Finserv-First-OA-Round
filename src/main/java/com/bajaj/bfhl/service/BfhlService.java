package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.request.BfhlRequest;
import com.bajaj.bfhl.dto.response.BfhlResponse;
import com.bajaj.bfhl.dto.response.GetOperationResponse;

/**
 * Service interface defining the operations for the BFHL challenge.
 */
public interface BfhlService {

    /**
     * Processes the input list of elements, filters, groups, and maps them as per business logic.
     *
     * @param request the request containing the input list.
     * @return the processed response.
     */
    BfhlResponse processData(BfhlRequest request);

    /**
     * Returns the hardcoded operation code.
     *
     * @return the operation response containing the operation code.
     */
    GetOperationResponse getOperationCode();
}
