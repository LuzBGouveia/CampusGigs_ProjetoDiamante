package fiap.com.br.campusgigs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.service.registry.ImportHttpServices;

@SpringBootApplication
@ImportHttpServices()
public class CampusGigsApplication {

    public static void main(String[] args) {
        SpringApplication.run(CampusGigsApplication.class, args);
    }

}
