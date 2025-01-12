package projet.spring.edraak.service.speciality;

import projet.spring.edraak.model.Speciality;

import java.util.List;

public interface ISpecialityService {
    Speciality getSpecialityById(Long id);
    Speciality addSpeciality(Speciality speciality);
    public void deleteSpeciality(Long id);
    Speciality updateSpeciality(Speciality speciality, Long id );
    Speciality getSpecialityByName(String name);
    List<Speciality> getAllSpecialities();
}
