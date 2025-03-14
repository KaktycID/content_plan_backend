package content.board.structure;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "dict_content_type", schema = "content_plan")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ContentType {

        @Id
        @GeneratedValue(generator = "dict_content_type_id_seq", strategy = GenerationType.SEQUENCE)
        @SequenceGenerator(name = "dict_content_type", sequenceName = "dict_content_type", allocationSize = 1, schema = "content_plan")
        @Column(name = "id", updatable = false, nullable = false)
        private int id;

        @Column(name = "title", updatable = false, nullable = false)
        private String title;
}
