package projet.spring.edraak.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Formation{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    //type of student if a student or an employee
    @Column(name="name")
    private String name;
    @Column(name="description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "typeFormation_id", referencedColumnName = "id")
    private TypeFormation typeFormation;
    @Column(name="start_date")
    private LocalDate startDate;
    @Column(name="end_date")
    private LocalDate endDate;
    @Column(name="price")
    private Float price ;
    @Column(name="max_participants")
    private Integer maxParticipants;
    @Column(name="min_participants")
    private Integer minParticipants;
    @Column(name="duration_total")
    private String durationTotal;
    @Column(name="durationOfSession")
    private String durationOfSession;
    @ElementCollection
    private List<LocalDate> trainingDates;
    @ManyToOne
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    @JsonBackReference
    private Instructor instructor;

    public Formation(String name, String description, TypeFormation typeFormation, LocalDate startDate, LocalDate endDate, Float price, Integer maxParticipants, Integer minParticipants, String durationTotal, String durationOfSession, List<LocalDate> trainingDates, Instructor instructor) {
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
    public Formation(){

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

    public void setPrice(Float price) {
        this.price = price;
    }

    public int getMaxParticipants() {
        return maxParticipants;
    }

    public void setMaxParticipants(Integer maxParticipants) {
        this.maxParticipants = maxParticipants;
    }

    public int getMinParticipants() {
        return minParticipants;
    }

    public void setMinParticipants(Integer minParticipants) {
        this.minParticipants = minParticipants;
    }

    public Instructor getInstructor() {
        return instructor;
    }

    public void setInstructor(Instructor instructor) {
        this.instructor = instructor;
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

    public List<LocalDate> getTrainingDates() {
        return trainingDates;
    }

    public void setTrainingDates(List<LocalDate> trainingDates) {
        this.trainingDates = trainingDates;
    }
}
