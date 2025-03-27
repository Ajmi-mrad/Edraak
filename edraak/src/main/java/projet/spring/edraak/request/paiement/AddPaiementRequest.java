package projet.spring.edraak.request.paiement;

import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Lob;
import projet.spring.edraak.model.PaymentType;
import projet.spring.edraak.utils.DateTimeParser;

import java.time.LocalDateTime;

public class AddPaiementRequest {
    private Double amount;
    private String paymentDate;

    @Enumerated(EnumType.STRING)
    private PaymentType paymentType;

    @Lob
    private byte[] checkImage; // Store image as a BLOB in the database

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public LocalDateTime getPaymentDate() {
        return DateTimeParser.parseStringToLocalDateTime(paymentDate);
    }

    public void setPaymentDate(String paymentDate) {
        this.paymentDate = paymentDate;
    }

    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    public byte[] getCheckImage() {
        return checkImage;
    }

    public void setCheckImage(byte[] checkImage) {
        this.checkImage = checkImage;
    }
}
