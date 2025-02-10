package projet.spring.edraak.service.formation;

import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.request.formation.AddFormationRequest;
import projet.spring.edraak.request.formation.FormationUpdateRequest;

import java.util.List;

public interface IFormationService {
    List<Formation> getAllFormations();
    Formation getFormationById(Long id);
    Formation addFormation(AddFormationRequest formation);
    public void deleteFormation(Long id);
    public Formation updateFormation(FormationUpdateRequest formation,Long id);
    List<Formation> getFormationByType(String typeFormation);
    Formation getFormationByName(String name);
}
