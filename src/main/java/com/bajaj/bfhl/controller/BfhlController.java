package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.request.BfhlRequest;
import com.bajaj.bfhl.dto.response.BfhlResponse;
import com.bajaj.bfhl.dto.response.GetOperationResponse;
import com.bajaj.bfhl.service.BfhlService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Controller exposing the /bfhl endpoints.
 */
@RestController
@RequestMapping("/bfhl")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class BfhlController {

    private final BfhlService bfhlService;

    /**
     * POST endpoint to process a list of alphanumeric inputs.
     * Always returns HTTP 200 for valid requests.
     *
     * @param request the payload containing data array.
     * @return the processed classification result.
     */
    @PostMapping
    public ResponseEntity<BfhlResponse> processData(@Valid @RequestBody BfhlRequest request) {
        BfhlResponse response = bfhlService.processData(request);
        return ResponseEntity.ok(response);
    }

    /**
     * GET endpoint to retrieve the standard operation code.
     *
     * @return the operation code.
     */
    @GetMapping
    public ResponseEntity<GetOperationResponse> getOperationCode() {
        GetOperationResponse response = bfhlService.getOperationCode();
        return ResponseEntity.ok(response);
    }
}
