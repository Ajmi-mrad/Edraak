package projet.spring.edraak.request.session;

import projet.spring.edraak.model.Formation;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.utils.DateParser;

import java.time.LocalDate;

public class AddSessionRequest {
    private String name;
    private String description;
    private String startDate;
    private String endDate;
    private float price;
    private int maxParticipants;
    private int minParticipants;
    private Instructor instructor;
    private Formation formation;

    public AddSessionRequest(String name, String description, String startDate, String endDate, float price, int maxParticipants, int minParticipants, Instructor instructor, Formation formation) {
        this.name = name;
        this.description = description;
        this.startDate = startDate;
        this.endDate = endDate;
        this.price = price;
        this.maxParticipants = maxParticipants;
        this.minParticipants = minParticipants;
        this.instructor = instructor;
        this.formation = formation;
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

    public LocalDate getStartDate() {
        return DateParser.parseStringToLocalDate(startDate);
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return DateParser.parseStringToLocalDate(endDate);
    }

    public void setEndDate(String endDate) {
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

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public Formation getFormation() {
        return formation;
    }

    public void setFormation(Formation formation) {
        this.formation = formation;
    }
}
