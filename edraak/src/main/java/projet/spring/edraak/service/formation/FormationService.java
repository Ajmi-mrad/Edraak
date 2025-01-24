package projet.spring.edraak.service.formation;

import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.FormationNotFoundException;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.repository.FormationRepository;
import projet.spring.edraak.repository.TypeFormationRepository;
import projet.spring.edraak.request.formation.AddFormationRequest;
import projet.spring.edraak.request.formation.FormationUpdateRequest;
import projet.spring.edraak.request.typeFormation.TypeFormationUpdateRequest;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class FormationService implements IFormationService{
    private final FormationRepository formationRepository;
    private final TypeFormationRepository typeFormationRepository;

    public FormationService(FormationRepository formationRepository,TypeFormationRepository typeFormationRepository) {
        this.formationRepository = formationRepository;
        this.typeFormationRepository = typeFormationRepository;
    }

    @Override
    public List<Formation> getAllFormations() {
        return formationRepository.findAll();
    }

    @Override
    public Formation getFormationById(Long id) {
        return formationRepository.findById(id).
                orElseThrow(()-> new FormationNotFoundException("Formation not found"));
    }

    @Override
    public Formation addFormation(AddFormationRequest request) {
        TypeFormation typeFormation = Optional.ofNullable(request.getTypeFormation())
                .orElseGet(()->{
                    TypeFormation newTypeFormation = new TypeFormation(request.getTypeFormation().getName());
                    return typeFormationRepository.save(newTypeFormation);
                });
        request.setTypeFormation(typeFormation);
        return formationRepository.save(createFormation(request,typeFormation));
    }
    public Formation createFormation(AddFormationRequest request, TypeFormation typeFormation){
        return new Formation(
                request.getName(),
                request.getDescription(),
                typeFormation
        );
    }

    @Override
    public void deleteFormation(Long id) {
        formationRepository.findById(id)
                .ifPresentOrElse(formationRepository::delete,()->{
                    throw new FormationNotFoundException("Formation not found");
                });
    }

    @Override
    public Formation updateFormation(FormationUpdateRequest request, Long id) {
        return Optional.ofNullable(getFormationById(id)).map(existingFormation->{
            Formation updatedFormation = updateExistingFormation(existingFormation,request);
            return formationRepository.save(updatedFormation);
        }).orElseThrow(()-> new FormationNotFoundException("Formation not found"));

    }
    private Formation updateExistingFormation(Formation existingFormation, FormationUpdateRequest request){
        existingFormation.setName(request.getName());
        existingFormation.setDescription(request.getDescription());
        TypeFormation typeFormation = typeFormationRepository.findByName(request.getTypeFormation().getName());
        existingFormation.setTypeFormation(typeFormation);
        return existingFormation;
    }

    @Override
    public List<Formation> getFormationByType(String typeFormationName) {
        TypeFormation typeFormation = typeFormationRepository.findByName(typeFormationName);
        if (typeFormation == null) {
            return List.of(); // Return an empty list if the typeFormation is not found
        }
        return formationRepository.findByTypeFormation(typeFormation);
    }
    @Override
    public Formation getFormationByName(String name) {
        return formationRepository.findByName(name);
    }
}
