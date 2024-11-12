package content.plan.users.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.Instant;

@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Users {

    @Id
    @GeneratedValue(generator = "users_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private Long id;

    @Column(name = "email", updatable = false, nullable = false)
    private String email;

    @Column(name = "password", updatable = false, nullable = false)
    private String password;

    @Column(name = "name", updatable = false, nullable = false)
    private String name;

    @ManyToOne
    @JoinColumn(name = "icon_id", updatable = false, nullable = false)
    private DictIcons iconId;

    @Column(name = "create_date", updatable = false, nullable = false)
    private Instant createDate;

    @Column(name = "update_date", updatable = false)
    private Instant updateDate;

    @Column(name = "is_active", updatable = false, nullable = false)
    private boolean isActive;
}
