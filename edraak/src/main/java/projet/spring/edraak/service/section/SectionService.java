package projet.spring.edraak.service.section;

import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.SectionNotFoundException;
import projet.spring.edraak.model.Section;
import projet.spring.edraak.repository.SectionRepository;
import projet.spring.edraak.request.section.AddSectionRequest;
import projet.spring.edraak.request.section.SectionUpdateRequest;

import java.util.List;
import java.util.Optional;

@Data
@Service
public class SectionService implements ISectionService{
    private final SectionRepository sectionRepository;

    public SectionService(SectionRepository sectionRepository) {
        this.sectionRepository = sectionRepository;
    }

    @Override
    public Section getSectionById(Long id) {
        return sectionRepository.findById(id)
                .orElseThrow(()-> new SectionNotFoundException("Section not found"));
    }

    @Override
    public Section addSection(Section section) {
        if (sectionRepository.existsByName(section.getName())) {
            throw new SectionNotFoundException("Section already exists");
        }
        return sectionRepository.save(section);
    }

    @Override
    public void deleteSection(Long id) {
        sectionRepository.findById(id)
                .ifPresentOrElse(sectionRepository::delete,()->{
                    throw new SectionNotFoundException("Section not found");
                });
    }

    @Override
    public Section updateSection(Section section, Long id) {
        return Optional.ofNullable(getSectionById(id)).map(oldSection->{
            oldSection.setName(section.getName());
            return sectionRepository.save(oldSection);
        }).orElseThrow(()-> new SectionNotFoundException("Section not found"));
    }

    @Override
    public Section getSectionByName(String name) {
        return sectionRepository.findByName(name);
    }
    @Override
    public  List<Section> getAllSections(){
        return sectionRepository.findAll();
    }

}
