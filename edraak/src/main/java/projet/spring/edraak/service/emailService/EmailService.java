package projet.spring.edraak.service.emailService;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;
import projet.spring.edraak.model.user.User;
import projet.spring.edraak.request.registration.RegistrationRequest;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

import static java.nio.charset.StandardCharsets.*;
import static org.springframework.mail.javamail.MimeMessageHelper.MULTIPART_MODE_MIXED;


@Service
public class EmailService {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;
    // i use the @Async annotation to make the method asynchronous so that it doesn't block the main thread

    public EmailService(JavaMailSender mailSender, SpringTemplateEngine templateEngine) {
        this.mailSender = mailSender;
        this.templateEngine = templateEngine;
    }

    @Async
    public void sendEmail(
            String to,
            String username,
            EmailTemplateName emailTemplate,
            String confirmationUrl,
            String activationCode,
            String subject
            ) {try{
            String templateName;
            if(emailTemplate == null){
                templateName = "confirm-email";
            }
            else {
                templateName = emailTemplate.getName();
            }
            // create
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(
                    mimeMessage,
                    MULTIPART_MODE_MIXED,
                    UTF_8.name()
            );
            // set the email properties
            Map<String,Object> properties = new HashMap<>();
            properties.put("username", username);
            properties.put("confirmationUrl", confirmationUrl);
            properties.put("activation_code", activationCode);
            // set the template
            Context context = new Context();
            context.setVariables(properties);
            // set the email properties
            helper.setFrom("ajmimrad02@gmail.com");
            helper.setTo(to);
            helper.setSubject(subject);

            String template = templateEngine.process(templateName, context);
            helper.setText(template, true);
            // send the email
            mailSender.send(mimeMessage);
    }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}

