package projet.spring.edraak.service.registrationFormation;

import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.RegistrationFormation;
import projet.spring.edraak.request.registrationFormation.AddRegistrationFormationRequest;
import projet.spring.edraak.request.registrationFormation.ResgitrationFormationUpdateRequest;

import java.util.List;

public interface IRegistrationFormationService {
    RegistrationFormation getRegistrationFormationById(Long id);
    RegistrationFormation addRegistrationFormation(AddRegistrationFormationRequest registrationFormation);
    void deleteRegistrationFormation(Long id);
    RegistrationFormation updateRegistrationFormation(ResgitrationFormationUpdateRequest registrationFormation, Long id);
    List<RegistrationFormation> getRegistrationFormationByFormation(Formation formation);
    List<RegistrationFormation> getAllRegistrationFormations();

}
