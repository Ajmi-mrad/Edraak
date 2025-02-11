package projet.spring.edraak.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.RegistrationFormation;
import projet.spring.edraak.model.Student;
import projet.spring.edraak.request.registrationFormation.AddRegistrationFormationRequest;
import projet.spring.edraak.request.registrationFormation.ResgitrationFormationUpdateRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.classroom.IClassroomService;
import projet.spring.edraak.service.formation.IFormationService;
import projet.spring.edraak.service.registrationFormation.IRegistrationFormationService;
import projet.spring.edraak.service.student.IStudentService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/registrationFormation")
public class RegistrationFormationController {
    private final IRegistrationFormationService registrationFormationService;
    private final IFormationService formationService;
    private final IClassroomService classroomService;
    private final IStudentService studentService;

    public RegistrationFormationController(IRegistrationFormationService registrationFormationService, IFormationService formationService, IClassroomService classroomService, IStudentService studentService) {
        this.registrationFormationService = registrationFormationService;
        this.formationService = formationService;
        this.classroomService = classroomService;
        this.studentService = studentService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllRegistrationFormations() {
        try {
            List<RegistrationFormation> registrationFormations = registrationFormationService.getAllRegistrationFormations();
            return ResponseEntity.ok(new ApiResponse("Registration Formations fetched successfully", registrationFormations));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{registrationFormationId}")
    public ResponseEntity<ApiResponse> getRegistrationFormationById(@PathVariable Long registrationFormationId) {
        try {
            RegistrationFormation registrationFormation = registrationFormationService.getRegistrationFormationById(registrationFormationId);
            return ResponseEntity.ok(new ApiResponse("Registration Formation fetched successfully", registrationFormation));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addRegistrationFormation(@Valid @RequestBody AddRegistrationFormationRequest request) {
        try {
            Formation formation = formationService.getFormationById(request.getFormation().getId());
            Classroom classroom = classroomService.getClassroomById(request.getClassroom().getId());
            Student student = studentService.getStudentById(request.getStudent().getId());
            if (formation == null) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Formation not found", null));
            }
            if (classroom == null) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Classroom not found", null));
            }
            if (student == null) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Student not found", null));
            }
            /*
            if (registrationFormationService.checkIfTrainingSessionsExistsBetweenStartDateAndEndDateAndClassroomId(formation.getStartDate(), formation.getEndDate(), classroom.getId())) {
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("Training sessions already" +
                        " exist between the start date and end date of the formation in the classroom", null));

             */
            request.setClassroom(classroom);
            request.setFormation(formation);
            request.setStudent(student);
            RegistrationFormation newRegistrationFormation = registrationFormationService.addRegistrationFormation(request);
            return ResponseEntity.ok(new ApiResponse("Registration Formation added successfully", newRegistrationFormation));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/{registrationFormationId}/update")
    public ResponseEntity<ApiResponse> updateRegistrationFormation(@RequestBody ResgitrationFormationUpdateRequest request, @PathVariable Long registrationFormationId) {
        try {
            RegistrationFormation updatedRegistrationFormation = registrationFormationService.updateRegistrationFormation(request, registrationFormationId);
            return ResponseEntity.ok(new ApiResponse("Registration Formation updated successfully", updatedRegistrationFormation));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @DeleteMapping("/{registrationFormationId}/delete")
    public ResponseEntity<ApiResponse> deleteRegistrationFormation(@PathVariable Long registrationFormationId) {
        try {
            registrationFormationService.deleteRegistrationFormation(registrationFormationId);
            return ResponseEntity.ok(new ApiResponse("Registration Formation deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
