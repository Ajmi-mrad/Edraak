package projet.spring.edraak.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import projet.spring.edraak.model.Session;
import projet.spring.edraak.request.session.AddSessionRequest;
import projet.spring.edraak.response.ApiResponse;
import projet.spring.edraak.service.session.ISessionService;

import java.util.List;

import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestController
@RequestMapping("/api/v1/sessions")
public class SessionController {
    private final ISessionService sessionService;
    public SessionController(ISessionService sessionService) {
        this.sessionService = sessionService;
    }
    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllSessions(){
        List<Session> sessions = sessionService.getAllSessions();
        return ResponseEntity.ok(new ApiResponse("Sessions fetched successfully",sessions));
    }
    @GetMapping("/{sessionId}")
    public ResponseEntity<ApiResponse> getSessionById(@PathVariable Long sessionId) {
        try {
            Session session = sessionService.getSessionById(sessionId);
            return ResponseEntity.ok(new ApiResponse("Session fetched successfully", session));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addSession(@RequestBody AddSessionRequest request){
        try{
            Session newSession = sessionService.addSession(request);
            return ResponseEntity.ok(new ApiResponse("Session added successfully",newSession));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @PutMapping("/{sessionId}/update")
    public ResponseEntity<ApiResponse> updateSession(@RequestBody AddSessionRequest request , @PathVariable Long sessionId){
        try{
            Session updatedSession = sessionService.updateSession(request,sessionId);
            return ResponseEntity.ok(new ApiResponse("Session updated successfully",updatedSession));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @DeleteMapping("/{sessionId}/delete")
    public ResponseEntity<ApiResponse> deleteSession(@PathVariable Long sessionId){
        try{
            sessionService.deleteSession(sessionId);
            return ResponseEntity.ok(new ApiResponse("Session deleted successfully",null));
        }catch(Exception e){
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(),null));
        }
    }
    @GetMapping("/session/{sessionName}")
    public ResponseEntity<ApiResponse> getSessionByName(@PathVariable String sessionName) {
        try {
            Session session = sessionService.getSessionByName(sessionName);
            return ResponseEntity.ok(new ApiResponse("Session fetched successfully", session));
        } catch (Exception e) {
            return ResponseEntity.status(INTERNAL_SERVER_ERROR).body(new ApiResponse(e.getMessage(), null));
        }
    }
}
