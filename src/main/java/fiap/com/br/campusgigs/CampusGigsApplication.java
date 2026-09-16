package fiap.com.br.campusgigs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices()
@ConfigurationPropertiesScan
public class CampusGigsApplication {
    public static void main(String[] args) {
        SpringApplication.run(CampusGigsApplication.class, args);
    }
}