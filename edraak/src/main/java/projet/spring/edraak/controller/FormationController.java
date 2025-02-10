package projet.spring.edraak.controller;

import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.request.formation.AddFormationRequest;
import projet.spring.edraak.request.formation.FormationUpdateRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.formation.IFormationService;
import projet.spring.edraak.service.instructor.IInstructorService;
import projet.spring.edraak.service.typeFormation.ITypeFormationService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/formations")
public class FormationController {
    private final IFormationService formationService;
    private final ITypeFormationService typeFormationService;
    private final IInstructorService instructorService;

    public FormationController(IFormationService formationService, IInstructorService instructorService, ITypeFormationService typeFormationService) {
        this.formationService = formationService;
        this.instructorService=instructorService;
        this.typeFormationService = typeFormationService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllFormations(){
        try {
            List<Formation> formations = formationService.getAllFormations();
            return ResponseEntity.ok(new ApiResponse("Formations fetched successfully", formations));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @GetMapping("/{formationId}")
    public ResponseEntity<ApiResponse> getFormationById(@PathVariable  Long formationId) {
        try {
            Formation formation = formationService.getFormationById(formationId);
            return ResponseEntity.ok(new ApiResponse("Formation fetched successfully", formation));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
/*
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFormation(
            @RequestParam("name") String name,
            @RequestParam("description") String description,
            @RequestParam("typeFormation") Long typeFormationId,
            @RequestParam("startDate") String startDate,
            @RequestParam("endDate") String endDate,
            @RequestParam("price") Float price,
            @RequestParam("maxParticipants") Integer maxParticipants,
            @RequestParam("minParticipants") Integer minParticipants,
            @RequestParam("duration") String duration,
            @RequestParam("instructor") Long instructorId
    ){
        try{
            //print typeformationId and instructorId
            System.out.println("typeFormationId: "+typeFormationId);
            System.out.println("instructorId: "+instructorId);

            TypeFormation typeFormation = typeFormationService.getTypeFormationById(typeFormationId);
            Instructor instructor = instructorService.getInstructorById(instructorId);
            AddFormationRequest request  = new AddFormationRequest(name,description,typeFormation,startDate,endDate,price,maxParticipants,minParticipants,duration,instructor);
            Formation newFormation = formationService.addFormation(request);
            return ResponseEntity.ok(new ApiResponse("Formation added successfully",newFormation));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

*/

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFormation(@Valid @RequestBody AddFormationRequest request) {
        try {
            TypeFormation typeFormation = typeFormationService.getTypeFormationById(request.getTypeFormation().getId());
            Instructor instructor = instructorService.getInstructorById(request.getInstructor().getId());
            System.out.println(instructor);
            request.setTypeFormation(typeFormation);
            request.setInstructor(instructor);
            Formation newFormation = formationService.addFormation(request);
            return ResponseEntity.ok(new ApiResponse("Formation added successfully", newFormation));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PutMapping("/{formationId}/update")
    public ResponseEntity<ApiResponse> updateFormation(@RequestBody FormationUpdateRequest request , @PathVariable Long formationId){
        try{
            Formation updatedFormation = formationService.updateFormation(request,formationId);
            return ResponseEntity.ok(new ApiResponse("Formation updated successfully",updatedFormation));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @DeleteMapping("/{formationId}/delete")
    public ResponseEntity<ApiResponse> deleteFormation(@PathVariable Long formationId){
        try{
            formationService.deleteFormation(formationId);
            return ResponseEntity.ok(new ApiResponse("Formation deleted successfully",null));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/type/{type}")
    public ResponseEntity<ApiResponse> getFormationByType(@PathVariable String type) {
        try {
            List<Formation> formations = formationService.getFormationByType(type);
            return ResponseEntity.ok(new ApiResponse("Formations fetched successfully", formations));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getFormationByName(@PathVariable String name) {
        try {
            Formation formation = formationService.getFormationByName(name);
            return ResponseEntity.ok(new ApiResponse("Formations fetched successfully", formation));
        }catch (Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }

}
