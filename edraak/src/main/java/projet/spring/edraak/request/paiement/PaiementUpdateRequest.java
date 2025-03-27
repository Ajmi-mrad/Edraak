package projet.spring.edraak.request.paiement;

import projet.spring.edraak.utils.DateTimeParser;

import java.time.LocalDateTime;

public class PaiementUpdateRequest {
    private Long id;
    private Double amount;
    private String paymentDate;
    private String paymentType;
    private byte[] checkImage;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public byte[] getCheckImage() {
        return checkImage;
    }

    public void setCheckImage(byte[] checkImage) {
        this.checkImage = checkImage;
    }
}
