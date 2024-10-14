package entities;

import jakarta.persistence.*;
import lombok.Data;


@Entity
@Data
public class DnaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 50)
    private String dnaSequence;

    @Column
    private boolean isMutant;
}
