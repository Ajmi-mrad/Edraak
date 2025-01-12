package projet.spring.edraak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.model.Speciality;
import projet.spring.edraak.request.section.AddSectionRequest;
import projet.spring.edraak.request.speciality.AddSpecialityRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.speciality.ISpecialityService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/specialities")
public class SpecialityController {
    private final ISpecialityService specialityService;

    public SpecialityController(ISpecialityService specialityService) {
        this.specialityService = specialityService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllSpecialities(){
        List<Speciality> specialities = specialityService.getAllSpecialities();
        return ResponseEntity.ok(new ApiResponse("Specialities fetched successfully",specialities));
    }
    @GetMapping("/{specialityId}")
    public ResponseEntity<ApiResponse> getSpecialityById(@PathVariable Long specialityId){
        try{

            Speciality speciality = specialityService.getSpecialityById(specialityId);
            return ResponseEntity.ok(new ApiResponse("Speciality fetched successfully",speciality));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addSpeciality(@RequestBody AddSpecialityRequest request){
        try{
            Speciality speciality = new Speciality(request.getName());
            Speciality newSpeciality = specialityService.addSpeciality(speciality);
            return ResponseEntity.ok(new ApiResponse("Speciality added successfully",newSpeciality));
        }catch(Exception e){
            // print the exception
            e.printStackTrace();
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/{specialityId}/delete")
    public ResponseEntity<ApiResponse> deleteSpeciality(@PathVariable Long specialityId){
        try{
            specialityService.deleteSpeciality(specialityId);
            return ResponseEntity.ok(new ApiResponse("Speciality deleted successfully",null));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/{specialityId}/update")
    public ResponseEntity<ApiResponse> updateSpeciality(@RequestBody AddSpecialityRequest request, @PathVariable Long specialityId) {
        try {
            Speciality speciality = new Speciality(request.getName());
            Speciality updatedSpeciality = specialityService.updateSpeciality(speciality, specialityId);
            return ResponseEntity.ok(new ApiResponse("Speciality updated successfully", updatedSpeciality));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getSpecialityByName(@PathVariable String name) {
        try {
            Speciality speciality = specialityService.getSpecialityByName(name);
            return ResponseEntity.ok(new ApiResponse("Speciality fetched successfully", speciality));
        } catch (Exception e) {
            return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
