package pudov.vadim.unit_test.users;

import jakarta.persistence.*;

@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false, unique = true)
    private String username;
    @Column(nullable = false)
    private String email;

    public User(String username, String email) {
        this.username = username;
        this.email = email;
    }

    public User() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() { return email; }

    public void setEmail(String email)  { this.email = email; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }
}
