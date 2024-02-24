package pudov.vadim.unit_test.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import pudov.vadim.unit_test.users.User;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class AuthController {
    @Autowired
    private AuthService authService;

    @GetMapping
    public List<User> getAllUsers() {
        return authService.getAllUsers();
    }

    @PostMapping("/register")
    public User createUser(@RequestBody User user, @RequestParam String password ) {
        return authService.register(user, password);
    }

    @PostMapping("/login")
    public String login() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return authService.login(username);
    }

    @PostMapping("/logout")
    public String logout() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        return authService.logout(username);
    }

    @GetMapping("/{id}")
    public User getUser(@PathVariable Long id) {
        return authService.getUserById(id);
    }

    @PutMapping("/{id}")
    public User updateUser(@PathVariable Long id, @RequestBody User updateUser) {
        User user = authService.getUserById(id);
        user.setUsername(updateUser.getUsername());
        user.setEmail(updateUser.getEmail());
        return authService.updateUser(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        authService.deleteUser(id);
    }
}
