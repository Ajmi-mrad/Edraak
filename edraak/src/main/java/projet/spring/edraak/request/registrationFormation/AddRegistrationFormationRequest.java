package projet.spring.edraak.request.registrationFormation;

import jakarta.validation.constraints.NotNull;
import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Student;
import projet.spring.edraak.utils.DateTimeParser;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class AddRegistrationFormationRequest {
    @NotNull
    private Long formationId;

    @NotNull
    private Long studentId;

    @NotNull
    private Long classroomId;
    private List<String> absenceDates;

    public Long getFormationId() {
        return formationId;
    }

    public void setFormationId(Long formationId) {
        this.formationId = formationId;
    }

    public Long getStudentId() {
        return studentId;
    }

    public void setStudentId(Long studentId) {
        this.studentId = studentId;
    }

    public Long getClassroomId() {
        return classroomId;
    }

    public void setClassroomId(Long classroomId) {
        this.classroomId = classroomId;
    }
    public List<LocalDateTime> getAbsenceDates() {
        return absenceDates.stream().map(DateTimeParser::parseStringToLocalDateTime).collect(Collectors.toList());
    }
    public void setAbsenceDates(List<String> absenceDates) {
        this.absenceDates = absenceDates;
    }
}
