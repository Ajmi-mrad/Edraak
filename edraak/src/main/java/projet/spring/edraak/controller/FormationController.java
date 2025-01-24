package projet.spring.edraak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.request.formation.AddFormationRequest;
import projet.spring.edraak.request.formation.FormationUpdateRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.formation.IFormationService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/formations")
public class FormationController {
    private final IFormationService formationService;

    public FormationController(IFormationService formationService) {
        this.formationService = formationService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllFormations(){
        List<Formation> formations = formationService.getAllFormations();
        return ResponseEntity.ok(new ApiResponse("Formations fetched successfully",formations));
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
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addFormation(@RequestBody AddFormationRequest request){
        try{
            Formation newFormation = formationService.addFormation(request);
            return ResponseEntity.ok(new ApiResponse("Formation added successfully",newFormation));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
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
