package pudov.vadim.unit_test.sessions;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SessionRepo extends JpaRepository<Session, Long> {}
