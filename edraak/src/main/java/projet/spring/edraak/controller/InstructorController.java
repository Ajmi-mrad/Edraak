package projet.spring.edraak.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.Speciality;
import projet.spring.edraak.request.instructor.AddInstructorRequest;
import projet.spring.edraak.request.instructor.InstructorUpdateRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.instructor.IInstructorService;

import java.time.LocalDate;
import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/instructors")
public class InstructorController {
    private final IInstructorService instructorService;

    @Autowired
    public InstructorController(IInstructorService instructorService) {
        this.instructorService = instructorService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        return ResponseEntity.ok(new ApiResponse("Instructors fetched successfully", instructors));
    }

    @GetMapping("/{instructorId}")
    public ResponseEntity<ApiResponse> getInstructorById(@PathVariable Long instructorId) {
        try {
            Instructor instructor = instructorService.getInstructorById(instructorId);
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructor));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{instructorId}/delete")
    public ResponseEntity<ApiResponse> deleteInstructor(@PathVariable Long instructorId) {
        try {
            instructorService.deleteInstructor(instructorId);
            return ResponseEntity.ok(new ApiResponse("Instructor deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @PostMapping(path = "/add", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ApiResponse> addInstructor(@RequestPart("request") AddInstructorRequest request) {
        try {
            Instructor newInstructor = instructorService.addInstructor(request);
            return ResponseEntity.ok(new ApiResponse("Instructor added successfully", newInstructor));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }



    @PutMapping("/{instructorId}/update")
    public ResponseEntity<ApiResponse> updateInstructor(@RequestBody InstructorUpdateRequest request, @PathVariable Long instructorId) {
        try {
            Instructor updatedInstructor = instructorService.updateInstructor(request, instructorId);
            return ResponseEntity.ok(new ApiResponse("Instructor updated successfully", updatedInstructor));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{firstName}/instructor")
    public ResponseEntity<ApiResponse> getInstructorByName(@PathVariable String firstName) {
        try {
            List<Instructor> instructors = instructorService.getInstructorByFirstName(firstName);
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructors));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{lastName}/instructor")
    public ResponseEntity<ApiResponse> getInstructorByLastName(@PathVariable String lastName) {
        try {
            List<Instructor> instructors = instructorService.getInstructorByLastName(lastName);
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructors));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{email}/instructor")
    public ResponseEntity<ApiResponse> getInstructorByEmail(@PathVariable String email) {
        try {
            Instructor instructor = instructorService.getInstructorByEmail(email);
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructor));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{birthDate}/instructor")
    public ResponseEntity<ApiResponse> getInstructorByBirthDate(@PathVariable String birthDate) {
        try {
            List<Instructor> instructors = instructorService.getInstructorByBirthDate(LocalDate.parse(birthDate));
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructors));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{firstName}/{lastName}/instructor")
    public ResponseEntity<ApiResponse> getInstructorByFirstNameAndLastName(@PathVariable String firstName, @PathVariable String lastName) {
        try {
            List<Instructor> instructors = instructorService.getInstructorFirstNameAndLastName(firstName, lastName);
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructors));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }

    @GetMapping("/{speciality}/instructor")
    public ResponseEntity<ApiResponse> getInstructorBySpeciality(@RequestParam String specialityName) {
        try {
            Speciality speciality = new Speciality();
            speciality.setName(specialityName);
            List<Instructor> instructors = instructorService.getInstructorBySpeciality(speciality);
            return ResponseEntity.ok(new ApiResponse("Instructor fetched successfully", instructors));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}