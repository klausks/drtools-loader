package drtools.aggregator;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.shell.command.annotation.CommandScan;

@SpringBootApplication
@CommandScan
public class AggregatorApplication {
	public static void main(String[] args) {
		SpringApplication.run(AggregatorApplication.class, args);
	}
}
