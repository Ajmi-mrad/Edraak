package projet.spring.edraak.request.registrationFormation;

import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Student;

public class ResgitrationFormationUpdateRequest {
    private Long id;
    private Formation formation;
    private Student student;
    private Classroom classroom;

    public ResgitrationFormationUpdateRequest(Long id, Formation formation, Student student, Classroom classroom) {
        this.id = id;
        this.formation = formation;
        this.student = student;
        this.classroom = classroom;
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
