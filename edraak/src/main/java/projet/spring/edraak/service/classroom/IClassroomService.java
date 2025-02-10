package projet.spring.edraak.service.classroom;

import projet.spring.edraak.model.Classroom;

import java.util.List;

public interface IClassroomService {
    List<Classroom> getAllClassrooms();
    Classroom getClassroomById(Long id);
    Classroom addClassroom(Classroom classroom);
    public void deleteClassroom(Long id);
    Classroom updateClassroom(Classroom classroom,Long id);
    Classroom getClassroomByName(String name);

}
