package projet.spring.edraak.service.typeFormation;

import projet.spring.edraak.model.TypeFormation;

import java.util.List;

public interface ITypeFormationService {
    TypeFormation getTypeFormationById(Long id);
    TypeFormation addTypeFormation(TypeFormation typeFormation);
    public void deleteTypeFormation(Long id);
    TypeFormation updateTypeFormation(TypeFormation typeFormation, Long id );
    TypeFormation getTypeFormationByName(String name);
    List<TypeFormation> getAllTypeFormations();
}
