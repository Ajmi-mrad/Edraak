package projet.spring.edraak.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
    @JsonIgnoreProperties({"registrationFormations"})
    //@JsonBackReference
    private Student student;

    @ManyToOne
    @JoinColumn(name = "classroom_id", referencedColumnName = "id")
    private Classroom classroom;

    @ElementCollection
    private List<LocalDateTime> absenceDates;
/*
    @OneToMany(mappedBy = "registrationFormation")
    private List<Student> students;
*/
    public RegistrationFormation(Formation formation, Student student, Classroom classroom , List<LocalDateTime> absenceDates) {
        this.formation = formation;
        this.student = student;
        this.classroom = classroom;
        this.absenceDates = absenceDates;
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

    public List<LocalDateTime> getAbsenceDates() {
        return absenceDates;
    }

    public void setAbsenceDates(List<LocalDateTime> absenceDates) {
        this.absenceDates = absenceDates;
    }
}
