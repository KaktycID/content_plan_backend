package content.plan.board.structure;

import jakarta.persistence.*;
import lombok.*;

@Builder
@Entity
@Table(name = "dict_types")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Types {

    @Id
    @GeneratedValue(generator = "dict_types_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "title", updatable = false, nullable = false)
    private String title;
}
