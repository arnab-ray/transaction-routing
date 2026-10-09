package io.arnab.transactionrouting.application;

import io.arnab.transactionrouting.domain.User;
import io.arnab.transactionrouting.domain.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    @Transactional
    public CreateUserResponse createUser(CreateUserRequest createUserRequest) {
        var maybeUser = userRepository.findByEmailId(createUserRequest.email());
        if (maybeUser.isEmpty()) {
            var user = new User();
            user.setFirstName(createUserRequest.firstName());
            user.setLastName(createUserRequest.lastName());
            user.setEmailId(createUserRequest.email());
            user.setPhoneNumber(createUserRequest.phoneNumber());
            var createdUser = userRepository.save(user);
            return new CreateUserResponse(createdUser.getId());
        } else  {
            var user = maybeUser.get();
            return new CreateUserResponse(user.getId());
        }
    }

    @Transactional(readOnly = true)
    public UserDetailsResponse getUser(Long userId) {
        var maybeUser = userRepository.findById(userId);

        if (maybeUser.isEmpty()) {
            throw new IllegalArgumentException("User not found");
        }

        var user = maybeUser.get();
        return UserDetailsResponse.builder()
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .email(user.getEmailId())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }
}
