package projet.spring.edraak.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;


@Entity
public class Paiement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "registration_formation_id", referencedColumnName = "id")
    private RegistrationFormation registrationFormation;

    private Double amount;
    private LocalDateTime paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;
    // if the payment is by check, the image of the check is stored in the database
    @Lob
    private byte[] checkImage; // Store image as a BLOB in the database

    public Paiement() {}

    public Paiement(RegistrationFormation registrationFormation, Double amount, LocalDateTime paymentDate, PaymentType paymentType,byte[] checkImage) {
        this.registrationFormation = registrationFormation;
        this.amount = amount;
        this.paymentDate = paymentDate;
        this.paymentType = paymentType;
        this.checkImage = checkImage;
    }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public RegistrationFormation getRegistrationFormation() { return registrationFormation; }

    public void setRegistrationFormation(RegistrationFormation registrationFormation) { this.registrationFormation = registrationFormation; }

    public Double getAmount() { return amount; }

    public void setAmount(Double amount) { this.amount = amount; }

    public LocalDateTime getPaymentDate() { return paymentDate; }

    public void setPaymentDate(LocalDateTime paymentDate) { this.paymentDate = paymentDate; }

    public PaymentType getPaymentType() { return paymentType; }

    public void setPaymentType(PaymentType paymentType) { this.paymentType = paymentType; }

    public byte[] getCheckImage() {
        return checkImage;
    }

    public void setCheckImage(byte[] checkImage) {
        this.checkImage = checkImage;
    }
}
