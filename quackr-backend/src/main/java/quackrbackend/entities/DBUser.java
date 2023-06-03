package quackrbackend.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "user", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DBUser extends DBIdentified {

    private String username;

    private String password;

    private String displayName;

    private Role role;

    @OneToMany(mappedBy = "users")
    private Set<DBPost> posts;
}
