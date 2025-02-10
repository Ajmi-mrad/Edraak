package projet.spring.edraak.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class RegistrationFormation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formation_id", referencedColumnName = "id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    private Student student;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    //@Column(name="training_date")
    // list of dates to store the training dates




}
