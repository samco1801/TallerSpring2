package co.edu.unbosque.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import co.edu.unbosque.model.Score;

public interface ScoreRepository extends JpaRepository<Score,Long>{

	public Optional<Score> findByName(String username);
	public void deleteByName(String username);
}
