package co.edu.unbosque.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "notasestudiantes")
public class Score {

	private @Id @GeneratedValue(strategy = GenerationType.IDENTITY) Long id;
	@Column(unique = true)
	private String name;
	@NotNull
	private String firstScore;
	@NotNull
	private String secondScore;
	@NotNull
	private String thirdScore;
	@NotNull
	private String prom;

	public Score() {
	}

	public Score(String name, @NotNull String firstScore, @NotNull String secondScore, @NotNull String thirdScore,
			@NotNull String prom) {
		super();
		this.name = name;
		this.firstScore = firstScore;
		this.secondScore = secondScore;
		this.thirdScore = thirdScore;
		this.prom = prom;
	}

	public Score(String name, @NotNull String firstScore, @NotNull String secondScore, @NotNull String thirdScore) {
		super();
		this.name = name;
		this.firstScore = firstScore;
		this.secondScore = secondScore;
		this.thirdScore = thirdScore;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getFirstScore() {
		return firstScore;
	}

	public void setFirstScore(String firstScore) {
		this.firstScore = firstScore;
	}

	public String getSecondScore() {
		return secondScore;
	}

	public void setSecondScore(String secondScore) {
		this.secondScore = secondScore;
	}

	public String getThirdScore() {
		return thirdScore;
	}

	public void setThirdScore(String thirdScore) {
		this.thirdScore = thirdScore;
	}

	public String getProm() {
		return prom;
	}

	public void setProm(String prom) {
		this.prom = prom;
	}

	@Override
	public String toString() {
		return "Student [id=" + id + ", name=" + name + ", firstScore=" + firstScore + ", secondScore=" + secondScore
				+ ", thirdScore=" + thirdScore + ", prom=" + prom + "]";
	}

}
