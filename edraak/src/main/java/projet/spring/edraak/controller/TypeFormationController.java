package projet.spring.edraak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.exceptions.TypeFormationNotFoundException;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.request.typeFormation.AddTypeFormationRequest;
import projet.spring.edraak.request.typeFormation.TypeFormationUpdateRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.typeFormation.ITypeFormationService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;


@RestController
@RequestMapping("/api/v1/typeFormations")   
public class TypeFormationController {
    private final ITypeFormationService typeFormationService;
    public TypeFormationController(ITypeFormationService typeFormationService){
        this.typeFormationService = typeFormationService;
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllTypeFormations(){
        List<TypeFormation> typeFormations = typeFormationService.getAllTypeFormations();
        if(typeFormations.isEmpty()){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("No TypeFormations found",null));
        }
        return ResponseEntity.ok(new ApiResponse("TypeFormations fetched successfully",typeFormations));
    }
    @GetMapping("/{typeFormationId}")
    public ResponseEntity<ApiResponse> getTypeFormationById(@PathVariable Long typeFormationId){
        try{
            TypeFormation typeFormation = typeFormationService.getTypeFormationById(typeFormationId);
            return ResponseEntity.ok(new ApiResponse("TypeFormation fetched successfully",typeFormation));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addTypeFormation(@RequestBody AddTypeFormationRequest request){
        try{
            TypeFormation typeFormation = new TypeFormation(request.getName());
            TypeFormation newTypeFormation = typeFormationService.addTypeFormation(typeFormation);
            return ResponseEntity.ok(new ApiResponse("TypeFormation added successfully",newTypeFormation));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/{typeFormationId}/update")
    public ResponseEntity<ApiResponse> updateTypeFormation(@RequestBody TypeFormationUpdateRequest request,@PathVariable Long typeFormationId){
        try{
            TypeFormation typeFormation = new TypeFormation(request.getName());
            TypeFormation updatedTypeFormation = typeFormationService.updateTypeFormation(typeFormation,typeFormationId);
            return ResponseEntity.ok(new ApiResponse("TypeFormation updated successfully",updatedTypeFormation));
        }catch(TypeFormationNotFoundException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PostMapping("/{typeFormationId}/delete")
    public ResponseEntity<ApiResponse> deleteTypeFormation(@PathVariable Long typeFormationId){
        try{
            typeFormationService.deleteTypeFormation(typeFormationId);
            return ResponseEntity.ok(new ApiResponse("TypeFormation deleted successfully",null));
        }catch(TypeFormationNotFoundException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<ApiResponse> getTypeFormationByName(@PathVariable String name){
        try{
            TypeFormation typeFormation = typeFormationService.getTypeFormationByName(name);
            if(typeFormation == null){
                return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse("TypeFormation not found",null));
            }
            return ResponseEntity.ok(new ApiResponse("TypeFormation fetched successfully",typeFormation));
        }catch(TypeFormationNotFoundException e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
}
