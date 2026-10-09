package io.arnab.transactionrouting.application;

import lombok.Builder;

@Builder
public record CreateUserRequest(String firstName, String lastName, String email, String phoneNumber) {
}
