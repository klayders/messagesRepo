package sugar.sugardemo;

import io.sentry.Sentry;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SugarDemoApplication {

	public static void main(String[] args) {
    Sentry.capture("App starting...");
		SpringApplication.run(SugarDemoApplication.class, args);
	}
}
