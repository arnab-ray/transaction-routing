package io.arnab.transactionrouting.application;

import io.arnab.transactionrouting.domain.User;
import io.arnab.transactionrouting.domain.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserService userService;

    @Autowired
    private UserRepository userRepository;

    @Test
    void testCreateUser() {

        var createUserRequest = CreateUserRequest.builder()
                .firstName("Jackie").lastName("Brown")
                .email("jackie@gmail.com").phoneNumber("1234567890").build();
        var response = userService.createUser(createUserRequest);

        assertThat(response).isNotNull();

        var createdUser = userRepository.findById(response.userId());

        assertThat(createdUser).isPresent();
        assertThat(createdUser.get())
                .usingRecursiveComparison()
                .ignoringFields("version", "createdAt", "updatedAt")
                .isEqualTo(createUser(response.userId()));
    }

    @Test
    void testGetUser() {
        var user = createUser(null);
        var createdUser = userRepository.save(user);

        var response = userService.getUser(createdUser.getId());

        assertThat(response)
                .usingRecursiveComparison()
                .isEqualTo(UserDetailsResponse.builder()
                        .firstName(user.getFirstName()).lastName(user.getLastName())
                        .email(user.getEmailId()).phoneNumber(user.getPhoneNumber()).build());
    }

    private User createUser(Long id) {
        var user = new User();
        if (id != null) {
            user.setId(id);
        }
        user.setFirstName("Jackie");
        user.setLastName("Brown");
        user.setEmailId("jackie@gmail.com");
        user.setPhoneNumber("1234567890");

        return user;
    }
}
