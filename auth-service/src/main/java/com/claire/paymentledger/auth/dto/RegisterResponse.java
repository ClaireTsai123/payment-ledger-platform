package com.claire.paymentledger.auth.dto;

import java.util.UUID;

public record RegisterResponse(
        UUID userId,
        String email,
        String status,
        String message
) {
}
