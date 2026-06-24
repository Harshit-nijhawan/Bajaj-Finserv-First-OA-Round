package com.bajaj.bfhl.service.impl;

import com.bajaj.bfhl.dto.request.BfhlRequest;
import com.bajaj.bfhl.dto.response.BfhlResponse;
import com.bajaj.bfhl.dto.response.GetOperationResponse;
import com.bajaj.bfhl.service.BfhlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Service implementation for processing BFHL requests.
 */
@Service
public class BfhlServiceImpl implements BfhlService {

    @Value("${bfhl.user.id}")
    private String userId;

    @Value("${bfhl.email}")
    private String email;

    @Value("${bfhl.roll.number}")
    private String rollNumber;

    @Override
    public BfhlResponse processData(BfhlRequest request) {
        List<String> inputData = request.getData();
        if (inputData == null) {
            return BfhlResponse.builder()
                    .isSuccess(false)
                    .build();
        }

        List<String> oddNumbers = new ArrayList<>();
        List<String> evenNumbers = new ArrayList<>();
        List<String> alphabets = new ArrayList<>();
        List<String> specialCharacters = new ArrayList<>();
        BigInteger sum = BigInteger.ZERO;
        List<Character> collectedChars = new ArrayList<>();

        for (String element : inputData) {
            if (element == null) {
                continue;
            }

            // 1. Numbers: If value contains only digits
            if (element.matches("^\\d+$")) {
                BigInteger val = new BigInteger(element);
                sum = sum.add(val);
                if (val.mod(BigInteger.TWO).equals(BigInteger.ZERO)) {
                    evenNumbers.add(element);
                } else {
                    oddNumbers.add(element);
                }
            }
            // 2. Alphabets: If value contains only alphabets
            else if (element.matches("^[a-zA-Z]+$")) {
                alphabets.add(element.toUpperCase());
                for (char c : element.toCharArray()) {
                    collectedChars.add(c);
                }
            }
            // 3. Special Characters: Anything that is neither purely alphabetic nor purely numeric
            else {
                specialCharacters.add(element);
            }
        }

        // Generate concat_string from the collected alphabet characters
        String concatString = buildConcatString(collectedChars);

        return BfhlResponse.builder()
                .isSuccess(true)
                .userId(userId)
                .email(email)
                .rollNumber(rollNumber)
                .oddNumbers(oddNumbers)
                .evenNumbers(evenNumbers)
                .alphabets(alphabets)
                .specialCharacters(specialCharacters)
                .sum(sum.toString())
                .concatString(concatString)
                .build();
    }

    @Override
    public GetOperationResponse getOperationCode() {
        return GetOperationResponse.builder()
                .operationCode(1)
                .build();
    }

    /**
     * Helper method to build the concat string:
     * - Reverse the collected characters
     * - Alternating caps starting with Uppercase (even indices Upper, odd indices Lower)
     */
    private String buildConcatString(List<Character> chars) {
        if (chars == null || chars.isEmpty()) {
            return "";
        }

        // Reverse the list
        List<Character> reversed = new ArrayList<>(chars);
        Collections.reverse(reversed);

        // Apply alternating caps starting with Uppercase
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < reversed.size(); i++) {
            char c = reversed.get(i);
            if (i % 2 == 0) {
                sb.append(Character.toUpperCase(c));
            } else {
                sb.append(Character.toLowerCase(c));
            }
        }
        return sb.toString();
    }
}
