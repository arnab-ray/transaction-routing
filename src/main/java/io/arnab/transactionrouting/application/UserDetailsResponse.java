package io.arnab.transactionrouting.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record UserDetailsResponse(String firstName, String lastName, String email, String phoneNumber) {
}
