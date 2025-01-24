package projet.spring.edraak.service.session;

import projet.spring.edraak.model.Session;
import projet.spring.edraak.request.session.AddSessionRequest;

import java.util.List;

public interface ISessionService {
    Session getSessionById(Long id);
    Session addSession(AddSessionRequest request);
    public void deleteSession(Long id);
    Session updateSession(AddSessionRequest request, Long id );
    Session getSessionByName(String name);
    List<Session> getAllSessions();
}
