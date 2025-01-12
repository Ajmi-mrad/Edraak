package projet.spring.edraak.service.speciality;

import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.SectionNotFoundException;
import projet.spring.edraak.exceptions.SpecialityNotFoundException;
import projet.spring.edraak.model.Speciality;
import projet.spring.edraak.repository.SpecialityRepository;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class SpecialityService implements ISpecialityService{
    private final SpecialityRepository specialityRepository;

    public SpecialityService(SpecialityRepository specialityRepository) {
        this.specialityRepository = specialityRepository;
    }

    @Override
    public Speciality getSpecialityById(Long id) {
        return specialityRepository.findById(id)
                .orElseThrow(()-> new SpecialityNotFoundException("Section not found"));
    }

    @Override
    public Speciality addSpeciality(Speciality speciality) {
        if(specialityRepository.existsByName(speciality.getName())){
            throw new SpecialityNotFoundException("Speciality already exists");
        }
        return specialityRepository.save(speciality);
    }

    @Override
    public void deleteSpeciality(Long id) {
        specialityRepository.findById(id)
                .ifPresentOrElse(specialityRepository::delete,()->{
                    throw new SectionNotFoundException("Speciality not found");
                });
    }

    @Override
    public Speciality updateSpeciality(Speciality speciality, Long id) {
        return Optional.ofNullable(getSpecialityById(id)).map(oldSpeciality->{
            oldSpeciality.setName(speciality.getName());
            return specialityRepository.save(oldSpeciality);
        }).orElseThrow(()-> new SpecialityNotFoundException("Speciality not found"));
    }

    @Override
    public Speciality getSpecialityByName(String name) {
        return specialityRepository.findByName(name);
    }

    @Override
    public List<Speciality> getAllSpecialities() {
        return specialityRepository.findAll();
    }


}
