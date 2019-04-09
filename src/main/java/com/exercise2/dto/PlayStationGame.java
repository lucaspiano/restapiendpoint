package com.exercise2.dto;

/**
 * @author Lucas Abrão
 * POJO that represents a PlayStation Game
 */
public class PlayStationGame {
	
	private final String title;
	private final int score;
	
	public PlayStationGame(final String title, final int score) {
		this.title = title;
		this.score = score;
	}
	
	public String getTitle() {
		return title;
	}

	public int getScore() {
		return score;
	}	
}
