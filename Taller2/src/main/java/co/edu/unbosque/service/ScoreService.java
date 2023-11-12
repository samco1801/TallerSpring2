package co.edu.unbosque.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.unbosque.model.Score;
import co.edu.unbosque.repository.ScoreRepository;
import co.edu.unbosque.util.AESUtil;

@Service
public class ScoreService implements CRUDOperations<Score> {

	@Autowired
	private ScoreRepository scoRep;

	public ScoreService() {
	}

	@Override
	public int create(Score data) {
		if (findStudentnameAlreadyTaken(data)) {
			return 1;
		} else {
			String finalScore = definitive(data);
			Score temp = new Score(AESUtil.encrypt(data.getName()),
					AESUtil.encrypt(String.valueOf(data.getFirstScore())),
					AESUtil.encrypt(String.valueOf(data.getSecondScore())),
					AESUtil.encrypt(String.valueOf(data.getThirdScore())),
					AESUtil.encrypt(String.valueOf(finalScore)));
			scoRep.save(temp);
		}
		return 0;
	}

	@Override
	public List<Score> getAll() {
		List<Score> database = scoRep.findAll();
		List<Score> decrypted = new ArrayList<>();

		for (Score data : database) {
			Score newScore = new Score();
			newScore.setName(AESUtil.decrypt(data.getName()));
			newScore.setFirstScore(AESUtil.decrypt(String.valueOf(data.getFirstScore())));
			newScore.setSecondScore(AESUtil.decrypt(String.valueOf(data.getSecondScore())));
			newScore.setThirdScore(AESUtil.decrypt(String.valueOf(data.getThirdScore())));
			newScore.setProm(AESUtil.decrypt(String.valueOf(data.getProm())));
			decrypted.add(newScore);
		}

		return decrypted;
	}

	@Override
	public int deleteById(Long id) {

		return 0;
	}

	@Override
	public int update(Long id, Score newData) {

		return 0;
	}

	@Override
	public long count() {
		return scoRep.count();
	}

	@Override
	public boolean exists(Long id) {

		return scoRep.existsById(id) ? true : false;
	}

	public boolean findStudentnameAlreadyTaken(Score newStudent) {
		String encryptedName = AESUtil.encrypt(newStudent.getName());
		Optional<Score> found = scoRep.findByName(encryptedName);
		if (found.isPresent()) {
			return true;
		} else {
			return false;
		}
	}

	public String definitive(Score newScore) {
		try {
			String firstScore = newScore.getFirstScore();
			String secondScore = newScore.getSecondScore();
			String thirdScore = newScore.getThirdScore();
			double court1 = Double.parseDouble(firstScore) * 0.30;
			double court2 = Double.parseDouble(secondScore) * 0.30;
			double court3 = Double.parseDouble(thirdScore) * 0.40;
			double definitiveScore = court1 + court2 + court3;
			
			String sco = String.valueOf(definitiveScore);
			
			return sco;
			
		} catch (NumberFormatException e) {
			return "";
		}
	}
}
