package projet.spring.edraak.request.formation;

import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import projet.spring.edraak.model.Instructor;
import projet.spring.edraak.model.TypeFormation;
import projet.spring.edraak.utils.DateParser;
import projet.spring.edraak.utils.DateTimeParser;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;


public class AddFormationRequest {
    private String name;
    private String description;
    private TypeFormation typeFormation;
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private String  startDate;
    //@JsonFormat(pattern = "dd-MM-yyyy")
    private String endDate;
    private Float price;
    private Integer maxParticipants;
    private Integer minParticipants;
    private String durationTotal;
    private String durationOfSession;
    private List<String> trainingDates;
    private Instructor instructor;

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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public Integer getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public Integer getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(Integer minParticipants) {
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
         // map the list and parse each date string to LocalDate
        return trainingDates.stream().map(DateTimeParser::parseStringToLocalDateTime).collect(Collectors.toList());
        /*
        // other methode
        List<LocalDate> result = new ArrayList<>();
        for (String dateString : trainingDates) {
            LocalDate date = DateParser.parseStringToLocalDate(dateString);
            result.add(date);
        }
        return result;
         */
    }

    public void setTrainingDates(List<String> trainingDates) {
        this.trainingDates = trainingDates;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
    }

    public AddFormationRequest(String name, String description, TypeFormation typeFormation, String startDate, String endDate, Float price, Integer maxParticipants, Integer minParticipants, String durationTotal, String durationOfSession, List<String> trainingDates, Instructor instructor) {
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
}
