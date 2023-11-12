package co.edu.unbosque.configuration;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import co.edu.unbosque.model.Score;
import co.edu.unbosque.repository.ScoreRepository;

import co.edu.unbosque.util.AESUtil;

@Configuration
public class LoadDataBase {

	private static final Logger LOG = LoggerFactory.getLogger(LoadDataBase.class);

	@Bean
	CommandLineRunner initDataBase(ScoreRepository scoRep) {
		return args -> {
			Optional<Score> foud = scoRep.findByName(AESUtil.encrypt("adminuser"));
			if (foud.isPresent()) {
				LOG.info("Admin already exist, skipping admin creation");
			} else {
				Score temp = new Score(AESUtil.encrypt(String.valueOf("adminuser")), AESUtil.encrypt(String.valueOf("0")),
						AESUtil.encrypt(String.valueOf("0")), AESUtil.encrypt(String.valueOf("0")),
						AESUtil.encrypt(String.valueOf("0")));
				scoRep.save(temp);
				LOG.info("pre-loading admin-user");
			}
		};
	}

}
