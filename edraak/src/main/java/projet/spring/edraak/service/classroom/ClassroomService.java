package projet.spring.edraak.service.classroom;

import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.ClassroomNotFoundException;
import projet.spring.edraak.model.Classroom;
import projet.spring.edraak.repository.ClassroomRepository;

import java.util.List;
import java.util.Optional;
@Data
@Service
public class ClassroomService implements IClassroomService{
    private final ClassroomRepository classroomRepository;
    public ClassroomService(ClassroomRepository classroomRepository) {
        this.classroomRepository = classroomRepository;
    }
    @Override
    public List<Classroom> getAllClassrooms() {
        return classroomRepository.findAll();
    }

    @Override
    public Classroom getClassroomById(Long id) {
        return classroomRepository.findById(id)
                .orElseThrow(()-> new ClassroomNotFoundException("Classroom not found"));
    }

    @Override
    public Classroom addClassroom(Classroom classroom) {
        if(classroomRepository.existsByName(classroom.getName())){
            throw new ClassroomNotFoundException("Classroom already exists");
        }
        return classroomRepository.save(classroom);
    }

    @Override
    public void deleteClassroom(Long id) {
        classroomRepository.findById(id)
                .ifPresentOrElse(classroomRepository::delete,()->{
                    throw new ClassroomNotFoundException("Classroom not found");
                });
    }

    @Override
    public Classroom updateClassroom(Classroom classroom, Long id) {
        return Optional.ofNullable(getClassroomById(id)).map(oldClassroom->{
            oldClassroom.setName(classroom.getName());
            return classroomRepository.save(oldClassroom);
        }).orElseThrow(()-> new ClassroomNotFoundException("Classroom not found"));
    }

    @Override
    public Classroom getClassroomByName(String name) {
        return classroomRepository.findByName(name);
    }
}
