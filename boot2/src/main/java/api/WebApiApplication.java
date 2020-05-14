package api;

import api.listener.RefreshListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.ApplicationPidFileWriter;

@SpringBootApplication
public class WebApiApplication {

    public static void main(String[] args) {
        SpringApplication boot = new SpringApplication(WebApiApplication.class);
        boot.addListeners(new ApplicationPidFileWriter());
        boot.run(args);
    }

}
