package com.upl.nibss.hcmlib.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by toyin.oladele on 09/10/2017.
 */
@Data
@Entity
@NoArgsConstructor
@Table(name = "users")
public class User extends SuperModel  implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;
    private boolean loggedIn;
    private Timestamp lastLogIn;
    private boolean activated;
    private boolean generatedPassword;
    private String currentSessionId;

    @OneToOne
    @JoinColumn(name = "EMPLOYEE_ID",unique = true)
    private Employee employee;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "USER_GROUPS",
            joinColumns = @JoinColumn(name = "users_id"),
            inverseJoinColumns = @JoinColumn(name = "groups_id"))
    private Set<Group> groups = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        User user = (User) o;
        return loggedIn == user.loggedIn &&
                activated == user.activated &&
                Objects.equals(username, user.username) &&
                Objects.equals(password, user.password) &&
                Objects.equals(lastLogIn, user.lastLogIn);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), username, password, loggedIn, lastLogIn, activated);
    }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("User{");
            sb.append("username='").append(username).append('\'');
            sb.append(", password='").append(password).append('\'');
            sb.append(", loggedIn=").append(loggedIn);
            sb.append(", lastLogIn=").append(lastLogIn);
            sb.append(", activated=").append(activated);
            sb.append('}');
            return sb.toString();
        }
}
