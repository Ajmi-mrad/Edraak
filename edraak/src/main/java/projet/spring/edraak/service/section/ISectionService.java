package projet.spring.edraak.service.section;

import projet.spring.edraak.model.Section;


import java.util.List;

public interface ISectionService {
    Section getSectionById(Long id);
    Section addSection(Section section);
    public void deleteSection(Long id);
    Section updateSection(Section section, Long id );
    Section getSectionByName(String name);
    List<Section> getAllSections();
}
