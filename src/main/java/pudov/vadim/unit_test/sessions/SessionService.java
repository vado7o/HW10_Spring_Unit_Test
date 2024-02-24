package pudov.vadim.unit_test.sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SessionService {

    @Autowired
    private SessionRepo sessionRepo;

    public Session createSession(Session session) {
        return sessionRepo.save(session);
    }

    public List<Session> getAllSessions() {
        return sessionRepo.findAll();
    }

    public void deleteSession(Session session) { sessionRepo.delete(session); }
}
