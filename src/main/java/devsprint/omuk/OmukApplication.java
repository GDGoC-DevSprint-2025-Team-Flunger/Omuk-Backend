package devsprint.omuk;

import io.github.cdimascio.dotenv.Dotenv;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OmukApplication {

	public static void main(String[] args) {
//		Dotenv dotenv = Dotenv.configure().load();
//		System.setProperty("AWS_ACCESS_KEY", dotenv.get("AWS_ACCESS_KEY"));
//		System.setProperty("AWS_SECRET_KEY", dotenv.get("AWS_SECRET_KEY"));
//		System.setProperty("AWS_REGION", dotenv.get("AWS_REGION"));
//		System.setProperty("AWS_S3_BUCKET", dotenv.get("AWS_S3_BUCKET"));

		SpringApplication.run(OmukApplication.class, args);
	}

}
