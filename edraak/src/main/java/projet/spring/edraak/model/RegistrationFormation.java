package projet.spring.edraak.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
public class RegistrationFormation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "formation_id", referencedColumnName = "id")
    private Formation formation;

    @ManyToOne
    @JoinColumn(name = "student_id", referencedColumnName = "id")
    @JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;
/*
    @OneToMany(mappedBy = "registrationFormation")
    private List<Student> students;
*/
    public RegistrationFormation(Formation formation, Student student, Classroom classroom) {
        this.formation = formation;
        this.student = student;
        this.classroom = classroom;
    }
    public RegistrationFormation(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Classroom getClassroom() {
        return classroom;
    }

    public void setClassroom(Classroom classroom) {
        this.classroom = classroom;
    }
}
