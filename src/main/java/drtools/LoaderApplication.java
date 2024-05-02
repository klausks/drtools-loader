package drtools;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class LoaderApplication {
	public static void main(String[] args) {
		SpringApplication.run(LoaderApplication.class, args);
	}
}
