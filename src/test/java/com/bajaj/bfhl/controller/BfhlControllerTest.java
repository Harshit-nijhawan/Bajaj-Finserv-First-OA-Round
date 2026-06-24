package com.bajaj.bfhl.controller;

import com.bajaj.bfhl.dto.request.BfhlRequest;
import com.bajaj.bfhl.dto.response.BfhlResponse;
import com.bajaj.bfhl.dto.response.GetOperationResponse;
import com.bajaj.bfhl.service.BfhlService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Controller tests for BfhlController.
 */
@WebMvcTest({BfhlController.class, HealthController.class})
class BfhlControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BfhlService bfhlService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testPostBfhl_Success() throws Exception {
        BfhlResponse responseDto = BfhlResponse.builder()
                .isSuccess(true)
                .userId("harshit_nijhawan_24062026")
                .email("harshit0876.be23@chitkara.edu.in")
                .rollNumber("2310990876")
                .oddNumbers(Arrays.asList("1"))
                .evenNumbers(Arrays.asList("334", "4"))
                .alphabets(Arrays.asList("A", "R"))
                .specialCharacters(Arrays.asList("$"))
                .sum("339")
                .concatString("Ra")
                .build();

        when(bfhlService.processData(any(BfhlRequest.class))).thenReturn(responseDto);

        BfhlRequest requestDto = BfhlRequest.builder()
                .data(Arrays.asList("a", "1", "334", "4", "R", "$"))
                .build();

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.is_success").value(true))
                .andExpect(jsonPath("$.user_id").value("harshit_nijhawan_24062026"))
                .andExpect(jsonPath("$.email").value("harshit0876.be23@chitkara.edu.in"))
                .andExpect(jsonPath("$.roll_number").value("2310990876"))
                .andExpect(jsonPath("$.odd_numbers[0]").value("1"))
                .andExpect(jsonPath("$.even_numbers[0]").value("334"))
                .andExpect(jsonPath("$.even_numbers[1]").value("4"))
                .andExpect(jsonPath("$.alphabets[0]").value("A"))
                .andExpect(jsonPath("$.alphabets[1]").value("R"))
                .andExpect(jsonPath("$.special_characters[0]").value("$"))
                .andExpect(jsonPath("$.sum").value("339"))
                .andExpect(jsonPath("$.concat_string").value("Ra"));
    }

    @Test
    void testPostBfhl_ValidationError() throws Exception {
        BfhlRequest requestDto = BfhlRequest.builder()
                .data(null)
                .build();

        mockMvc.perform(post("/bfhl")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(requestDto)))
                .andExpect(status().isBadRequest())
                .andExpect(jsonPath("$.is_success").value(false))
                .andExpect(jsonPath("$.message").value("Validation failed: Input data array cannot be null"));
    }

    @Test
    void testGetBfhl_Success() throws Exception {
        GetOperationResponse responseDto = GetOperationResponse.builder()
                .operationCode(1)
                .build();

        when(bfhlService.getOperationCode()).thenReturn(responseDto);

        mockMvc.perform(get("/bfhl"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.operation_code").value(1));
    }

    @Test
    void testGetHealth_Success() throws Exception {
        mockMvc.perform(get("/health"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("UP"));
    }
}
