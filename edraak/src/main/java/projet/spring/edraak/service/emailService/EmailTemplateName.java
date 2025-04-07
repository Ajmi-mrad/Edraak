package projet.spring.edraak.service.emailService;

public enum EmailTemplateName {
    ACCOUNT_ACTIVATION("activate_account");
    private final String name;
    EmailTemplateName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }
}
