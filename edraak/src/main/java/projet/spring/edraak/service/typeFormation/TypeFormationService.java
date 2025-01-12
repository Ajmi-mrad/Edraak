package projet.spring.edraak.service.typeFormation;

import projet.spring.edraak.exceptions.TypeFormationNotFoundException;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.repository.TypeFormationRepository;

import java.util.List;

public class TypeFormationService implements ITypeFormationService {
    private final TypeFormationRepository typeFormationRepository;
    public TypeFormationService(TypeFormationRepository typeFormationRepository) {
        this.typeFormationRepository = typeFormationRepository;
    }
    @Override
    public TypeFormation getTypeFormationById(Long id) {
        return typeFormationRepository.findById(id)
                .orElseThrow(()-> new TypeFormationNotFoundException("TypeFormation not found"));
    }

    @Override
    public TypeFormation addTypeFormation(TypeFormation typeFormation) {
        if(typeFormationRepository.existsByName(typeFormation.getName())){
            throw new TypeFormationNotFoundException("TypeFormation already exists");
        }
        return typeFormationRepository.save(typeFormation);
    }

    @Override
    public void deleteTypeFormation(Long id) {

    }

    @Override
    public TypeFormation updateTypeFormation(TypeFormation typeFormation, Long id) {
        return null;
    }

    @Override
    public TypeFormation getTypeFormationByName(String name) {
        return null;
    }

    @Override
    public List<TypeFormation> getAllTypeFormations() {
        return null;
    }
}
