package projet.spring.edraak.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Formation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    //type of student if a student or an employee
    @Column(name = "name")
    private String name;
    @Column(name = "description")
    private String description;
    @ManyToOne
    @JoinColumn(name = "typeFormation_id", referencedColumnName = "id")
    private TypeFormation typeFormation;
    @Column(name = "start_date")
    private LocalDate startDate;
    @Column(name = "end_date")
    private LocalDate endDate;
    //@NotNull(message = "Price is mandatory")
    @Positive(message = "Price must be positive")
    @Column(name = "price")
    private Float price;

    //@NotNull(message = "Max participants is mandatory")
    @Min(value = 1, message = "Max participants must be at least 1")
    @Column(name = "max_participants")
    private Integer maxParticipants;

    //@NotNull(message = "Min participants is mandatory")
    @Min(value = 1, message = "Min participants must be at least 1")
    @Column(name = "min_participants")
    private Integer minParticipants;
    @Column(name = "duration_total")
    private String durationTotal;
    @Column(name = "durationOfSession")
    private String durationOfSession;
    //@ElementCollection
    //private List<LocalDateTime> trainingDates;
    @ElementCollection
    //@CollectionTable(name = "training_dates", joinColumns = @JoinColumn(name = "formation_id"))
    @Column(name = "training_date")
    private List<LocalDateTime> trainingDates;
    @ManyToOne(fetch = FetchType.EAGER)
    //@NotNull
    @JoinColumn(name = "instructor_id", referencedColumnName = "id")
    //@JsonIgnore
    //@JsonView(Views.FormationView)
    @JsonIgnoreProperties({"formations"})
    private Instructor instructor;

    @OneToMany(mappedBy = "formation")
    private List<RegistrationFormation> registrationFormations;

    public Formation(String name, String description, TypeFormation typeFormation, LocalDate startDate, LocalDate endDate, Float price, Integer maxParticipants, Integer minParticipants, String durationTotal, String durationOfSession, List<LocalDateTime> trainingDates, Instructor instructor) {
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

    public List<LocalDateTime> getTrainingDates() {
        return trainingDates;
    }

    public void setTrainingDates(List<LocalDateTime> trainingDates) {
        this.trainingDates = trainingDates;
    }
}
