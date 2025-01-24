package projet.spring.edraak.service.session;

import lombok.Data;
import org.springframework.stereotype.Service;
import projet.spring.edraak.exceptions.SessionNotFoundException;
import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.Session;
import projet.spring.edraak.repository.FormationRepository;
import projet.spring.edraak.repository.InstructorRepository;
import projet.spring.edraak.repository.SessionRepository;
import projet.spring.edraak.request.session.AddSessionRequest;

import java.util.List;
@Data
@Service
public class SessionService implements ISessionService{
    private final SessionRepository sessionRepository;
    private final InstructorRepository instructorRepository;
    private final FormationRepository formationRepository;

    public SessionService(SessionRepository sessionRepository, InstructorRepository instructorRepository, FormationRepository formationRepository) {
        this.sessionRepository = sessionRepository;
        this.instructorRepository = instructorRepository;
        this.formationRepository = formationRepository;
    }

    @Override
    public Session getSessionById(Long id) {
        return sessionRepository.findById(id).
                orElseThrow(()-> new SessionNotFoundException("Session not found"));
    }

    @Override
    public Session addSession(AddSessionRequest request) {
        Instructor instructor = instructorRepository.findById(request.getInstructor().getId())
                .orElseThrow(()-> new SessionNotFoundException("Instructor not found"));
        Formation formation = formationRepository.findById(request.getFormation().getId())
                .orElseThrow(()-> new SessionNotFoundException("Formation not found"));
        return sessionRepository.save(createSession(request, instructor, formation));
    }
    public Session createSession(AddSessionRequest request, Instructor instructor, Formation formation){
        return new Session(
                request.getName(),
                request.getDescription(),
                request.getStartDate(),
                request.getEndDate(),
                request.getPrice(),
                request.getMaxParticipants(),
                request.getMinParticipants(),
                instructor,
                formation
        );
    }

    @Override
    public void deleteSession(Long id) {
        sessionRepository.findById(id)
                .ifPresentOrElse(sessionRepository::delete,()->{
                    throw new SessionNotFoundException("Session not found");
                });
    }

    @Override
    public Session updateSession(AddSessionRequest request, Long id) {
        return sessionRepository.findById(id)
                .map(session -> updateExistingSession(request, session))
                .map(sessionRepository::save)
                .orElseThrow(()-> new SessionNotFoundException("Session not found"));
    }
    public Session updateExistingSession(AddSessionRequest request, Session session){
        Instructor instructor = instructorRepository.findById(request.getInstructor().getId())
                .orElseThrow(()-> new SessionNotFoundException("Instructor not found"));
        Formation formation = formationRepository.findById(request.getFormation().getId())
                .orElseThrow(()-> new SessionNotFoundException("Formation not found"));
        session.setName(request.getName());
        session.setDescription(request.getDescription());
        session.setStartDate(request.getStartDate());
        session.setEndDate(request.getEndDate());
        session.setPrice(request.getPrice());
        session.setMaxParticipants(request.getMaxParticipants());
        session.setMinParticipants(request.getMinParticipants());
        session.setInstructor(instructor);
        session.setFormation(formation);
        return sessionRepository.save(session);
    }
    @Override
    public Session getSessionByName(String name) {
        return sessionRepository.findByName(name);
    }

    @Override
    public List<Session> getAllSessions() {
        return sessionRepository.findAll();
    }
}
