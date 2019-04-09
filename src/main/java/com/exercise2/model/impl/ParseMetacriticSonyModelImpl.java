package com.exercise2.model.impl;
import java.io.IOException;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import com.exercise2.dto.PlayStationGame;
import com.exercise2.model.ParseMetacriticSonyModel;
import com.exercise2.service.impl.GameServiceImpl;

public class ParseMetacriticSonyModelImpl implements ParseMetacriticSonyModel{
	
	private GameServiceImpl gameServiceImpl = GameServiceImpl.getInstance();
	
	private PlayStationGame playStationGame = null;	

	private void populatePlayStationDTO(List<String> titles, List<String> scores, int linksSize) {		
		for (int i=0; i < linksSize; i++) {
			playStationGame = new PlayStationGame(titles.get(i), Integer.parseInt(scores.get(i)));
			getGameServiceImpl().addGame(playStationGame);				
		}
	}
	
	private void extractDataFromDocument(Document doc) {
		Elements links = doc.select("h3");
		Elements info = doc.getElementsByClass("metascore_w large game positive").not(".pad_10").not(".pad_20").after("metascore_w large game positive");
		
		List<String> titles = links.eachText();
		List<String> scores = info.eachText();
		
		populatePlayStationDTO(titles, scores, links.size());
	}
	
	public void accessPage() {	
		try {
			Document doc = Jsoup.connect("https://www.metacritic.com/game/playstation-4").get();
			extractDataFromDocument(doc);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public GameServiceImpl getGameServiceImpl() {
		return gameServiceImpl;
	}
	
}
