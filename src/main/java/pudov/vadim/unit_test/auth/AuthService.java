package pudov.vadim.unit_test.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import pudov.vadim.unit_test.UserDetailServiceConfig;
import pudov.vadim.unit_test.sessions.Session;
import pudov.vadim.unit_test.sessions.SessionRepo;
import pudov.vadim.unit_test.users.User;
import pudov.vadim.unit_test.users.UserRepo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Service
public class AuthService {

    BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder(); // для шифрования паролей
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private SessionRepo sessionRepo;
    @Autowired
    private UserDetailServiceConfig userDetailServiceConfig;


    // метод для добавления пользователя в БД. По сути просто добавляет username и email в POSTGRESQL.
    // При этом сверяет и если в БД уже имеется такой username, то выдаёт ошибку.
    // Также создаёт для данного пользователя роль ROLE="USER" чтобы пользователь имел право идти по url="/login"
    public User register(User user, String password) {
        userDetailServiceConfig.getManager()
                .createUser(org.springframework.security.core.userdetails.User.withUsername(user.getUsername())
                .password(bCryptPasswordEncoder.encode(password))
                .roles("USER")
                .build());
        return userRepo.save(user);
    }


    // метод, который производит подключение к сессии если username и password верны. При этом если сессия c таким
    // пользователем уже существует, то выходит оповещение об этом. В сессии также хранится время её начала.
    public String login(String username) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        List<Session> sessions = sessionRepo.findAll();
        for (Session session : sessions) {
            if (session.getUserName().equals(username)) return "Данный пользователь уже подключен!";
        }
        Session session = new Session(username, LocalDateTime.now().format(formatter));
        sessionRepo.save(session);
        return "Успешное подключение!";
    }

    public String logout(String username) {
        List<Session> sessions = sessionRepo.findAll();
        for (Session session : sessions) {
            if (session.getUserName().equals(username)) {
                sessionRepo.delete(session);
                return "Сессия прекращена!";
            }
        }
        return "Данный пользователь не имеет активных сессий!";
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public User getUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new RuntimeException("Пользователь не найден!"));
    }

    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long id) {
        userRepo.deleteById(id);
    }
}
