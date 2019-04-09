
package com.exercise2.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.exercise2.dto.PlayStationGame;
import com.exercise2.service.GameService;

public class GameServiceImpl implements GameService {
	
	private static final GameServiceImpl INSTANCE = new GameServiceImpl();
	private static final List<PlayStationGame> list = new ArrayList<>();
	
	
	public static GameServiceImpl getInstance() {
		return INSTANCE;
	}
	
	private GameServiceImpl()
	{
		
	}
	
	public PlayStationGame getGame(String nameGame) {
		for (int i=0; i < list.size(); i++) {
			if (nameGame.equals(list.get(i).getTitle())) {
				return list.get(i);
			}
		}
		return null;
	}
	
	public List<PlayStationGame> getAllGames() {
		return this.list;
	}
	
	public void addGame(PlayStationGame playStationGame) {
		list.add(playStationGame);
	}
}
