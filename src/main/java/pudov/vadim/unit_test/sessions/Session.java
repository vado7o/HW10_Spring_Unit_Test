package pudov.vadim.unit_test.sessions;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String userName;
    @Column(nullable = false)
    private String startLocalDateTime;

    public Session(String userName, String startLocalDateTime) {
        this.userName = userName;
        this.startLocalDateTime = startLocalDateTime;
    }

    public Session(String userName) {
        this.userName = userName;
    }

    public Session() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getStartLocalDateTime() {
        return startLocalDateTime;
    }

    public void setStartLocalDateTime(String startLocalDateTime) {
        this.startLocalDateTime = startLocalDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Session session = (Session) o;
        return Objects.equals(userName, session.userName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(userName);
    }
}
