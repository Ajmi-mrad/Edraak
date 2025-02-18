package projet.spring.edraak.request.registrationFormation;

import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Student;

import java.time.LocalDateTime;
import java.util.List;

public class ResgitrationFormationUpdateRequest {
    private Long id;
    private Long  idFormation;
    private Long idStudent;
    private Long idClassroom;
    private List<LocalDateTime> absenceDates;



    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getIdFormation() {
        return idFormation;
    }

    public void setIdFormation(Long idFormation) {
        this.idFormation = idFormation;
    }

    public Long getIdStudent() {
        return idStudent;
    }

    public void setIdStudent(Long idStudent) {
        this.idStudent = idStudent;
    }

    public Long getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(Long idClassroom) {
        this.idClassroom = idClassroom;
    }

    public List<LocalDateTime> getAbsenceDates() {
        return absenceDates;
    }

    public void setAbsenceDates(List<LocalDateTime> absenceDates) {
        this.absenceDates = absenceDates;
    }
}
