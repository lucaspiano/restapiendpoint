package com.exercise2.service;

import java.util.List;

import com.exercise2.dto.PlayStationGame;

/**
 * 
 * @author Lucas Abrão
 *
 */
public interface GameService {

	/**
	 * Get a specific game based on a given name
	 * @param nameGame
	 * @return
	 */
	PlayStationGame getGame(String nameGame);
	
	/**
	 * Get all the available games
	 * @return
	 */
	List<PlayStationGame> getAllGames();
	
	/**
	 * Add a game to the list of games
	 * @param playStationGame
	 */
	void addGame(PlayStationGame playStationGame);
}
