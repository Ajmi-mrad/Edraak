package projet.spring.edraak;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.scheduling.annotation.EnableAsync;
import projet.spring.edraak.model.role.Role;
import projet.spring.edraak.repository.role.RoleRepository;

@SpringBootApplication(scanBasePackages = "projet.spring.edraak")
// i enbale the jpa auditing to let @EntityListeners(AuditingEntityListener.class) working
@EnableJpaAuditing
@EnableAsync
public class EdraakApplication {

    public static void main(String[] args) {
        SpringApplication.run(EdraakApplication.class, args);
    }
    @Bean
    public CommandLineRunner runner(RoleRepository roleRepository){
        return args -> {
            if(roleRepository.findByName("USER").isEmpty()){
                roleRepository.save(
                        Role.builder().name("USER").build()
                );
            }
        };
    }

}
