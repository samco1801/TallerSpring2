package co.edu.unbosque.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.edu.unbosque.model.Score;
import co.edu.unbosque.service.ScoreService;
import co.edu.unbosque.util.AESUtil;
import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/score")
@CrossOrigin(origins = { "http://localhost:8080", "http://localhost:8081", "*" })
@Transactional
public class ScoreController {

	@Autowired
	private ScoreService scoServ;

	public ScoreController() {
	}

	@PostMapping(path = "/create", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<String> create(@RequestBody Score newScore) {
		Score temp = new Score(AESUtil.encrypt(String.valueOf(newScore.getName())),
				AESUtil.encrypt(String.valueOf(newScore.getFirstScore())),
				AESUtil.encrypt(String.valueOf(newScore.getSecondScore())),
				AESUtil.encrypt(String.valueOf(newScore.getThirdScore())));
		int status = scoServ.create(temp);
		if (status == 0) {

			double def = Double.parseDouble(String.valueOf(scoServ.definitive(temp)));

			if (def == 3) {
				return new ResponseEntity<String>("You passed but try harder next time", HttpStatus.CREATED);
			} else if (def > 3) {
				return new ResponseEntity<String>("You did it, well done!", HttpStatus.CREATED);}
			else if (def < 3) {
				return new ResponseEntity<String>("It's a shame but you lost the subject", HttpStatus.CREATED);}
			else {
				return new ResponseEntity<String>("(-_-)", HttpStatus.CREATED);
			}

		} else if (status == 1) {
			return new ResponseEntity<>("Error: Username already taken", HttpStatus.CONFLICT);
		} else {
			return new ResponseEntity<>("Error creating the user", HttpStatus.NOT_ACCEPTABLE);
		}
	}

	@GetMapping(path = "/getall")
	public ResponseEntity<List<Score>> getAll() {
		List<Score> users = scoServ.getAll();
		if (users.isEmpty()) {
			return new ResponseEntity<List<Score>>(users, HttpStatus.NO_CONTENT);
		} else {
			return new ResponseEntity<List<Score>>(users, HttpStatus.ACCEPTED);
		}
	}

}
