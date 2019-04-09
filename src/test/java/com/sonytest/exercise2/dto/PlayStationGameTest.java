package com.sonytest.exercise2.dto;

import org.junit.Assert;
import org.junit.Test;

import com.exercise2.dto.PlayStationGame;

public class PlayStationGameTest {
	
	@Test
	public void playStationGameTest() {
		String title = "Test";
		int score = 10;
		PlayStationGame playStationGame = new PlayStationGame(title, score);
		Assert.assertEquals(title, playStationGame.getTitle());
		Assert.assertEquals(score, playStationGame.getScore());
	}

}
