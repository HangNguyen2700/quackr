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
@Table(name = "users", uniqueConstraints = {
        @UniqueConstraint(columnNames = {
                "username"
        })
})
@Data
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder
public class DBUser extends DBIdentified {

    private String username;

    private String password;

    private String displayName;

    private Role role;

    @OneToMany(mappedBy = "publishedBy")
    private Set<DBPost> posts;
}
