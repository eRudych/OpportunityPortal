package task.NewOpportunityPortal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class NewOpportunityPortalApplication {

    public static void main(String[] args) {
        SpringApplication.run(NewOpportunityPortalApplication.class, args);
    }

}
