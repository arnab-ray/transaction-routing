package io.arnab.transactionrouting.application;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;

@Builder
@JsonIgnoreProperties(ignoreUnknown = true)
public record CreateUserResponse(Long userId) {
}
