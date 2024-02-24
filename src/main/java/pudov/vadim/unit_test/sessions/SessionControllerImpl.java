package pudov.vadim.unit_test.sessions;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/sessions")
public class SessionControllerImpl {

    @Autowired
    private SessionService service;

    @GetMapping
    public List<Session> getAllSessions() { return service.getAllSessions(); }
}
