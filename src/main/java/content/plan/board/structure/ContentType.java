package content.plan.board.structure;

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
        @GeneratedValue(generator = "dict_content_type_seq", strategy = GenerationType.SEQUENCE)
        @Column(name = "id", updatable = false, nullable = false)
        private int id;

        @Column(name = "title", updatable = false, nullable = false)
        private String title;
}
