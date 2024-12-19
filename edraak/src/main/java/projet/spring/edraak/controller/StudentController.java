package projet.spring.edraak.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import projet.spring.edraak.service.student.IStudentService;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.model.Student;
import projet.spring.edraak.request.AddStudentRequest;
import projet.spring.edraak.request.StudentUpdateRequest;
import projet.spring.edraak.exceptions.StudentNotFoundException;
import static org.springframework.http.HttpStatus.NOT_FOUND;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

import java.time.LocalDate;
import java.util.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
    private final IStudentService studentService;

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllStudents(){
        List<Student> students = studentService.getAllStudents();
        return ResponseEntity.ok(new ApiResponse("Students fetched successfully",students));
    }
    @GetMapping("/student/{studentId}/student")
    public ResponseEntity<ApiResponse> getStudentById(@PathVariable  Long studentId){
        try{
            Student student = studentService.getStudentById(studentId);
            return ResponseEntity.ok(new ApiResponse("Student fetched successfully",student));
        }catch(Exception e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addStudent(@RequestBody AddStudentRequest student){
        try{
            Student newStudent = studentService.addStudent(student);
            return ResponseEntity.ok(new ApiResponse("Student added successfully",newStudent));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/student/{studentId}/update")
    public ResponseEntity<ApiResponse> updateStudent(@RequestBody StudentUpdateRequest request , @PathVariable Long studentId){
        try{
            Student updatedStudent = studentService.updateStudent(request,studentId);
            return ResponseEntity.ok(new ApiResponse("Student updated successfully",updatedStudent));
        }catch(StudentNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @DeleteMapping("/student/{studentId}/delete")
    public ResponseEntity<ApiResponse> deleteStudent(@PathVariable Long studentId){
        try{
            studentService.deleteStudent(studentId);
            return ResponseEntity.ok(new ApiResponse("Student deleted successfully",null));
        }catch(StudentNotFoundException e){
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/student/{name}/students")
    public ResponseEntity<ApiResponse> getStudentByName(@PathVariable String name){
        try{
            List<Student> students= studentService.getStudentByName(name);
            if(students.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Student not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Student fetched successfully",students));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("erreur",e.getMessage()));
        }
    }
    @GetMapping("/student/{name}/{lastName}/students")
    public ResponseEntity<ApiResponse> getStudentByNameAndLastName(@PathVariable String name,@PathVariable String lastName){
        try{
            List<Student> students= studentService.getStudentByNameAndLastName(name,lastName);
            if(students.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Student not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Student fetched successfully",students));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("erreur",e.getMessage()));
        }
    }
    @GetMapping("/student/{lastName}/students")
    public ResponseEntity<ApiResponse> getStudentByLastName(@PathVariable String lastName){
        try{
            List<Student> students= studentService.getStudentByLastName(lastName);
            if(students.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Student not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Student fetched successfully",students));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("erreur",e.getMessage()));
        }
    }
    @GetMapping("/student/{birthDate}/students")
    public ResponseEntity<ApiResponse> getStudentByBirthDate(@PathVariable String birthDate){
        try{
            List<Student> students= studentService.getStudentByBirthDate(LocalDate.parse(birthDate));
            if(students.isEmpty()){
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse("Student not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("Student fetched successfully",students));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("erreur",e.getMessage()));
        }
    }
    @GetMapping("/student/{email}/students")
    public ResponseEntity<ApiResponse> getStudentByEmail(@PathVariable String email){
        try{
            Student student= studentService.getStudentByEmail(email);
            return ResponseEntity.ok(new ApiResponse("Student fetched successfully",student));
        }
        catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("erreur",e.getMessage()));
        }
    }

}
