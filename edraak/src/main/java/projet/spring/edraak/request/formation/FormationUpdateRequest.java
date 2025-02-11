package projet.spring.edraak.request.formation;

import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.TypeFormation;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class FormationUpdateRequest {
    private Long id;
    private String name;
    private String description;
    private TypeFormation typeFormation;
    private LocalDate startDate;
    private LocalDate endDate;
    private float price;
    private int maxParticipants;
    private int minParticipants;
    private String durationTotal;
    private String durationOfSession;
    private List<LocalDateTime> trainingDates;
    private Instructor instructor;

    public FormationUpdateRequest(Long id, String name, String description, TypeFormation typeFormation, LocalDate startDate, LocalDate endDate, float price, int maxParticipants, int minParticipants, String durationTotal, String durationOfSession, List<LocalDateTime> trainingDates, Instructor instructor) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.typeFormation = typeFormation;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.minParticipants = minParticipants;
        this.durationTotal = durationTotal;
        this.durationOfSession = durationOfSession;
        this.trainingDates = trainingDates;
        this.instructor = instructor;
    }

    public FormationUpdateRequest(){
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public TypeFormation getTypeFormation() {
        return typeFormation;
    }

    public void setTypeFormation(TypeFormation typeFormation) {
        this.typeFormation = typeFormation;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(int maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(int minParticipants) {
        this.minParticipants = minParticipants;
    }

    public String getDurationTotal() {
        return durationTotal;
    }

    public void setDurationTotal(String durationTotal) {
        this.durationTotal = durationTotal;
    }

    public String getDurationOfSession() {
        return durationOfSession;
    }

    public void setDurationOfSession(String durationOfSession) {
        this.durationOfSession = durationOfSession;
    }

    public List<LocalDateTime> getTrainingDates() {
        return trainingDates;
    }

    public void setTrainingDates(List<LocalDateTime> trainingDates) {
        this.trainingDates = trainingDates;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }
}
