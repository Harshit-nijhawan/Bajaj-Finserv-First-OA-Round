package com.bajaj.bfhl.service;

import com.bajaj.bfhl.dto.request.BfhlRequest;
import com.bajaj.bfhl.dto.response.BfhlResponse;
import com.bajaj.bfhl.dto.response.GetOperationResponse;
import com.bajaj.bfhl.service.impl.BfhlServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for BfhlServiceImpl.
 */
class BfhlServiceImplTest {

    private BfhlServiceImpl bfhlService;

    @BeforeEach
    void setUp() {
        bfhlService = new BfhlServiceImpl();
        // Inject configuration values using ReflectionTestUtils
        ReflectionTestUtils.setField(bfhlService, "userId", "harshit_nijhawan_24062026");
        ReflectionTestUtils.setField(bfhlService, "email", "harshit0876.be23@chitkara.edu.in");
        ReflectionTestUtils.setField(bfhlService, "rollNumber", "2310990876");
    }

    @Test
    void testProcessData_withStandardInput() {
        List<String> input = Arrays.asList("a", "1", "334", "4", "R", "$");
        BfhlRequest request = BfhlRequest.builder().data(input).build();

        BfhlResponse response = bfhlService.processData(request);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("harshit_nijhawan_24062026", response.getUserId());
        assertEquals("harshit0876.be23@chitkara.edu.in", response.getEmail());
        assertEquals("2310990876", response.getRollNumber());

        // Numbers assertion
        assertEquals(Arrays.asList("1"), response.getOddNumbers());
        assertEquals(Arrays.asList("334", "4"), response.getEvenNumbers());
        assertEquals("339", response.getSum());

        // Alphabets assertion (converted to uppercase)
        assertEquals(Arrays.asList("A", "R"), response.getAlphabets());

        // Special characters assertion
        assertEquals(Arrays.asList("$"), response.getSpecialCharacters());

        // Concat string assertion:
        // Collected: 'a', 'R'
        // Reversed: 'R', 'a'
        // Alternating case: R (upper), a (lower) -> "Ra"
        assertEquals("Ra", response.getConcatString());
    }

    @Test
    void testProcessData_withComplexConcatStringLogic() {
        List<String> input = Arrays.asList("A", "ABCD", "DOE");
        BfhlRequest request = BfhlRequest.builder().data(input).build();

        BfhlResponse response = bfhlService.processData(request);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals("0", response.getSum());
        assertEquals(Arrays.asList("A", "ABCD", "DOE"), response.getAlphabets());
        assertTrue(response.getOddNumbers().isEmpty());
        assertTrue(response.getEvenNumbers().isEmpty());
        assertTrue(response.getSpecialCharacters().isEmpty());

        // Concat string assertion:
        // Collected: 'A', 'A', 'B', 'C', 'D', 'D', 'O', 'E'
        // Reversed: 'E', 'O', 'D', 'D', 'C', 'B', 'A', 'A'
        // Alternating case: E o D d C b A a -> "EoDdCbAa"
        assertEquals("EoDdCbAa", response.getConcatString());
    }

    @Test
    void testProcessData_withEmptyAndNullElements() {
        List<String> input = Arrays.asList(null, "", "  ", "12");
        BfhlRequest request = BfhlRequest.builder().data(input).build();

        BfhlResponse response = bfhlService.processData(request);

        assertNotNull(response);
        assertTrue(response.isSuccess());
        assertEquals(Arrays.asList("12"), response.getEvenNumbers());
        assertEquals("12", response.getSum());
        // Empty space and spaces are treated as special characters under the rule
        assertEquals(Arrays.asList("", "  "), response.getSpecialCharacters());
    }

    @Test
    void testGetOperationCode() {
        GetOperationResponse response = bfhlService.getOperationCode();
        assertNotNull(response);
        assertEquals(1, response.getOperationCode());
    }
}
