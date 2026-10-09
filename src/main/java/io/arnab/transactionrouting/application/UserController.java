package io.arnab.transactionrouting.application;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user/v1")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping
    public CreateUserResponse create(@RequestBody CreateUserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/id/{id}")
    public UserDetailsResponse findById(@PathVariable Long id) {
        return userService.getUser(id);
    }
}
