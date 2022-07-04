package cz.cvut.fit.sp.botbuilder;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"cz.cvut.fit.sp.botbuilder.authentication", "cz.cvut.fit.sp.botbuilder"})
public class BotBuilderApplication {
    public static void main(String[] args) {
        SpringApplication.run(BotBuilderApplication.class, args);
    }
}
