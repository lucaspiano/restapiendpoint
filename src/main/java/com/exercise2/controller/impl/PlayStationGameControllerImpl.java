package com.exercise2.controller.impl;
import com.exercise2.controller.PlayStationGameController;
import com.exercise2.model.ParseMetacriticSonyModel;
import com.exercise2.model.impl.ParseMetacriticSonyModelImpl;

public class PlayStationGameControllerImpl implements PlayStationGameController {
	
	ParseMetacriticSonyModel parseMetacriticSonyModel = new ParseMetacriticSonyModelImpl();
	
	public void init() {
		parseMetacriticSonyModel.accessPage();
	}
}
