package eu.ivan.colorservice;

import static org.springframework.web.reactive.function.server.RequestPredicates.*;
import static org.springframework.web.reactive.function.server.RouterFunctions.*;
import static org.springframework.web.reactive.function.server.ServerResponse.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.Environment;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.ServerResponse;

import eu.ivan.colorservice.service.Color;
import eu.ivan.colorservice.service.ColorService;
import eu.ivan.colorservice.service.RandomColorService;
import eu.zderadicka.spoiler.DelayingSpoiler;
import eu.zderadicka.spoiler.FailingAndDelayingSpoiler;
import eu.zderadicka.spoiler.FailingSpoiler;
import eu.zderadicka.spoiler.NeverFailingSpoiler;
import eu.zderadicka.spoiler.Spoiler;

@SpringBootApplication
public class ColorServiceApplication {
	public static final Logger LOG = LoggerFactory.getLogger(ColorServiceApplication.class);
	public static void main(String[] args) {
		SpringApplication.run(ColorServiceApplication.class, args);
	}

	@Bean
	public RouterFunction<ServerResponse> randomColor(ColorService colorService) {
		return route(GET("/color"), req -> {
			return ok().body(colorService.generateColor(req.queryParam("name")), Color.class);
		});
	}

	@Bean
	public ColorService randomColorService(Spoiler spoiler) {
		return new RandomColorService(spoiler);
	}

	@Bean
	public Spoiler spoilerBean(Environment env) {
		String spoilerType = env.getProperty("spoiler.type");
		if (spoilerType == null) spoilerType = "NONE";
		String mttfProperty = env.getProperty("spoiler.mttf");
		String delayProperty = env.getProperty("spoiler.delay");
		double  mttf = mttfProperty != null ? Double.parseDouble(mttfProperty) : 1000;
		double delay = delayProperty != null ?Double.parseDouble(delayProperty): 1000;
		switch (spoilerType) {
			case "FAIL":
				LOG.info(String.format("Spoiler will fail service in app %f calls", mttf));
				return new FailingSpoiler(mttf);
			case "DELAY":
				LOG.info(String.format("Spoiler will delay calls to service %f ms on average", delay));
				return new DelayingSpoiler(delay);

			case "FAIL_DELAY":
				LOG.info(String.format("Spoiler will fail service in app %f calls", mttf));
				LOG.info(String.format("Spoiler will delay calls to service %f ms on average", delay));
				return new FailingAndDelayingSpoiler(mttf, delay);

			case "NONE":
				return new NeverFailingSpoiler();

			default:
				throw new RuntimeException("Invalid parameter for spoiler.type: " + spoilerType);

			
		}
	}

}
