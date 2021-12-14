package test.prog.app;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = "test.prog")

public class RestfulApplication  {

    public static void main(String[] args) {
        SpringApplication.run(RestfulApplication.class, args);
    }


}