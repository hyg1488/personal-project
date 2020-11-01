package SpeedGame;

import java.io.Serializable;

public class GameData implements Serializable {
	private int timmer;
	private int collect;
	private int score;
	private String name;
	private String lv;
	private int grade;
	
	
	public GameData() {
		
	}
	public GameData(int score, String name, int grade,String lv) {
		this.score = score;
		this.name = name;
		this.grade = grade;
		this.lv = lv;
	}
	
	public String getLv() {
		return lv;
	}
	
	public void setLv(String lv) {
		this.lv = lv;
	}
	
	public int getGrade() {
		return grade;
	}
	
	public void setGrade(int grade) {
		this.grade = grade;
	}
	
	public int getTimmer() {
		return timmer;
	}


	public void setTimmer(int timmer) {
		this.timmer = timmer;
	}


	public int getCollect() {
		return collect;
	}


	public void setCollect(int collect) {
		this.collect = collect;
	}

	public int getScore() {
		return score;
	}
	
	public void setScore(int score) {
		this.score = score;
	}
	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}
	
	
}
