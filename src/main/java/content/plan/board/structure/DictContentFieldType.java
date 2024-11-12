package content.plan.board.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dict_content_field_type")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class DictContentFieldType {

    @Id
    @GeneratedValue(generator = "dict_content_field_type_seq", strategy = GenerationType.SEQUENCE)
    @Column(name = "id", updatable = false, nullable = false)
    private int id;

    @Column(name = "title", updatable = false, nullable = false)
    private String title;
}
