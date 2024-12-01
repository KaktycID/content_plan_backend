package content.plan.users.structure;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "dict_icons", schema = "users")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Icons {

    @Id
    @GeneratedValue(generator = "dict_icons_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "title", updatable = false, nullable = false)
    private String title;
}
