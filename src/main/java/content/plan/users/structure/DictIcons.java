package content.plan.users.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dict_icons")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DictIcons {

    @Id
    @GeneratedValue(generator = "dict_icons_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "title", updatable = false, nullable = false)
    private String title;
}
