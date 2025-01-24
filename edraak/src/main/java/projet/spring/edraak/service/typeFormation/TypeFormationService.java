package projet.spring.edraak.service.typeFormation;

import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.TypeFormationNotFoundException;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.repository.TypeFormationRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@Service
public class TypeFormationService implements ITypeFormationService {
    private static final Logger logger = LoggerFactory.getLogger(TypeFormationService.class);
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
        return Optional.of(typeFormation).filter(typeFormation1 -> typeFormationRepository.findByName(typeFormation1.getName()) == null)
                .map(typeFormationRepository::save)
                .orElseThrow(()-> new TypeFormationNotFoundException("TypeFormation already exists"));
    }

    @Override
    public void deleteTypeFormation(Long id) {
        typeFormationRepository.findById(id)
                .ifPresentOrElse(typeFormationRepository::delete,()->{
                    throw new TypeFormationNotFoundException("TypeFormation not found");
                });
    }

    @Override
    public TypeFormation updateTypeFormation(TypeFormation typeFormation, Long id) {
        return Optional.ofNullable(getTypeFormationById(id)).map(oldTypeFormation->{
            oldTypeFormation.setName(typeFormation.getName());
            return typeFormationRepository.save(oldTypeFormation);
        }).orElseThrow(()-> new TypeFormationNotFoundException("TypeFormation not found"));
    }


    /*@Override
    public TypeFormation getTypeFormationByName(String name) {
        logger.info("Fetching TypeFormation with name: {}", name);
        TypeFormation typeFormation = typeFormationRepository.findByName(name);
        if (typeFormation == null) {
            logger.warn("TypeFormation with name {} not found", name);
        } else {
            logger.info("TypeFormation with name {} found: {}", name, typeFormation);
        }
        return typeFormation;
    }
    */
     @Override
     public TypeFormation getTypeFormationByName(String name) {
         return typeFormationRepository.findByName(name);
     }

    @Override
    public List<TypeFormation> getAllTypeFormations() {

        return typeFormationRepository.findAll();
    }
}
