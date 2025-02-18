package projet.spring.edraak.model;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class Absence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registration_id")
    private RegistrationFormation registrationFormation;

    private LocalDateTime absenceDate;
    private String reason; // Optional: Reason for absence (e.g., sick, personal)
    private Boolean justified; // True if absence is justified

    public Absence() {
    }

    public Absence(RegistrationFormation registrationFormation, LocalDateTime absenceDate, String reason, Boolean justified) {
        this.registrationFormation = registrationFormation;
        this.absenceDate = absenceDate;
        this.reason = reason;
        this.justified = justified;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RegistrationFormation getRegistrationFormation() {
        return registrationFormation;
    }

    public void setRegistrationFormation(RegistrationFormation registrationFormation) {
        this.registrationFormation = registrationFormation;
    }

    public LocalDateTime getAbsenceDate() {
        return absenceDate;
    }

    public void setAbsenceDate(LocalDateTime absenceDate) {
        this.absenceDate = absenceDate;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Boolean getJustified() {
        return justified;
    }

    public void setJustified(Boolean justified) {
        this.justified = justified;
    }
}
