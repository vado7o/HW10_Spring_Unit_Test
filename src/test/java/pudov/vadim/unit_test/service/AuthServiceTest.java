package pudov.vadim.unit_test.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pudov.vadim.unit_test.UserDetailServiceConfig;
import pudov.vadim.unit_test.sessions.Session;
import pudov.vadim.unit_test.sessions.SessionRepo;
import pudov.vadim.unit_test.users.User;
import pudov.vadim.unit_test.users.UserRepo;
import pudov.vadim.unit_test.auth.AuthService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class AuthServiceTest {

    @InjectMocks
    private AuthService authService;

    @Mock
    private UserRepo userRepo;
    @Mock
    private SessionRepo sessionRepo;
    @Mock
    private UserDetailServiceConfig userDetailServiceConfig;


    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRegisterUser() {
        User user = new User("IvanDurak", "ivan@test.com");
        InMemoryUserDetailsManager mockImudm = new InMemoryUserDetailsManager();

        when(userDetailServiceConfig.getManager()).thenReturn(mockImudm);

        authService.register(user, "123");

        Mockito.verify(userRepo, Mockito.times(1)).save(user);
    }

    @Test
    public void testSessionCreate() {
        Session mockSession = new Session("IvanDurak");

        authService.login("IvanDurak");

        Mockito.verify(sessionRepo, Mockito.times(1)).save(mockSession);
    }

    @Test
    public void testStopSession() {
        Session session = new Session("IvanDurak", LocalDateTime.now().toString());
        List<Session> sessions = new ArrayList<>();
        sessions.add(session);

        when(sessionRepo.findAll()).thenReturn(sessions);
        authService.logout("IvanDurak");

        Mockito.verify(sessionRepo, Mockito.times(1)).delete(session);
    }
}
