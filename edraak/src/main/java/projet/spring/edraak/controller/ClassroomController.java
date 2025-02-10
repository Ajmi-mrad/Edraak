package projet.spring.edraak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.request.classroom.AddClassroomRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.classroom.IClassroomService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/classrooms")
public class ClassroomController {
    private final IClassroomService classroomService;
    public ClassroomController(IClassroomService classroomService) {
        this.classroomService = classroomService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllClassrooms(){
        List<Classroom> classrooms = classroomService.getAllClassrooms();
        return ResponseEntity.ok(new ApiResponse("Classrooms fetched successfully",classrooms));
    }
    @GetMapping("/{classroomId}")
    public ResponseEntity<ApiResponse> getClassroomById(@PathVariable Long classroomId) {
        try {
            Classroom classroom = classroomService.getClassroomById(classroomId);
            return ResponseEntity.ok(new ApiResponse("Classroom fetched successfully", classroom));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addClassroom(@RequestBody AddClassroomRequest request) {
        try {
            Classroom classroom = new Classroom(request.getName());
            Classroom newClassroom = classroomService.addClassroom(classroom);
            return ResponseEntity.ok(new ApiResponse("Classroom added successfully", newClassroom));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/{classroomId}/delete")
    public ResponseEntity<ApiResponse> deleteClassroom(@PathVariable Long classroomId) {
        try {
            classroomService.deleteClassroom(classroomId);
            return ResponseEntity.ok(new ApiResponse("Classroom deleted successfully", null));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/{classroomId}/update")
    public ResponseEntity<ApiResponse> updateClassroom(@RequestBody AddClassroomRequest request, @PathVariable Long classroomId) {
        try {
            Classroom classroom = new Classroom(request.getName());
            Classroom updatedClassroom = classroomService.updateClassroom(classroom, classroomId);
            return ResponseEntity.ok(new ApiResponse("Classroom updated successfully", updatedClassroom));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getClassroomByName(@PathVariable String name) {
        try {
            Classroom classroom = classroomService.getClassroomByName(name);
            return ResponseEntity.ok(new ApiResponse("Classroom fetched successfully", classroom));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
