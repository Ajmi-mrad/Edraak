package projet.spring.edraak.request.registrationFormation;

import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Student;

public class AddRegistrationFormationRequest {
    private Formation formation;
    private Student student;
    private Classroom classroom;

    public AddRegistrationFormationRequest(Formation formation, Student student, Classroom classroom) {
        this.formation = formation;
        this.student = student;
        this.classroom = classroom;
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
