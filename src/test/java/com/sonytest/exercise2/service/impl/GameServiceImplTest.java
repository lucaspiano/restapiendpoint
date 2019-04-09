package com.sonytest.exercise2.service.impl;

import org.junit.Assert;
import org.junit.Test;

import com.exercise2.dto.PlayStationGame;
import com.exercise2.service.impl.GameServiceImpl;


public class GameServiceImplTest {

	private static final int SCORE_1 = 90;

	private static final int SCORE_2 = 93;

	private static final int SCORE_3 = 80;

	private static final String TITLE_3 = "Fifa 19";

	private static final String TITLE_2 = "Resident Evil 2";

	private static final String TITLE_1 = "Subnautica";
	
	@Test
	public void getInstanceTest()
	{
		Assert.assertNotNull(GameServiceImpl.getInstance());
	}
	
	@Test
	public void addAndGetGamesTest() {
		GameServiceImpl obj = GameServiceImpl.getInstance();
		obj.addGame(new PlayStationGame(TITLE_1, SCORE_1));
		obj.addGame(new PlayStationGame(TITLE_2, SCORE_2));
		obj.addGame(new PlayStationGame(TITLE_3, SCORE_3));
		
		Assert.assertEquals(3, obj.getAllGames().size());
		
		PlayStationGame playStationGame = obj.getGame(TITLE_1);
		Assert.assertEquals(TITLE_1, playStationGame.getTitle());
		Assert.assertEquals(SCORE_1, playStationGame.getScore());
	}
}
