package co.istad.chhaya.webmvc.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.jspecify.annotations.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class User implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true, nullable = false, length = 60)
    private String username;
    @Column(unique = true, nullable = false, length = 60)
    private String email;
    @Column(nullable = false)
    private String encryptedPassword;

    @Column(nullable = false, length = 60)
    private String familyName;

    @Column(nullable = false, length = 60)
    private String givenName;

    @Column(nullable = false, length = 8)
    private String gender;

    @ManyToMany
    @JoinTable(
            name = "users_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    private Set<Role> roles;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return this.roles;
    }

    @Override
    public @Nullable String getPassword() {
        return this.encryptedPassword;
    }
}
