package sg.nus.iss.visa.ssf;

import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import jakarta.el.ListELResolver;
import sg.nus.iss.visa.ssf.utility.Utility;

import java.util.List;


@SpringBootApplication
public class Workshop13Application {

	public static void main(String[] args) {
		// SpringApplication.run(Workshop13Application.class, args);

		SpringApplication app = new SpringApplication(Workshop13Application.class);

		DefaultApplicationArguments appArgs = new DefaultApplicationArguments(args);

		List<String> opsVal = appArgs.getOptionValues("dataDir");

		System.out.println(opsVal);

		if (opsVal != null) {
			Utility.createDir(opsVal.get(0));
		}
		else {
			//terminate program
			System.out.println("no data dir provided..");
			System.exit(1);
		}

		app.run(args);
	}

}
