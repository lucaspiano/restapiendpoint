package com.sonytest.exercise2.model.impl;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.exercise2.model.impl.ParseMetacriticSonyModelImpl;

@RunWith(PowerMockRunner.class)
@PrepareForTest(Jsoup.class)
public class ParseMetacriticSonyModelImplTest {

	private static final String SCORE_1 = "90";

	private static final String SCORE_2 = "93";

	private static final String SCORE_3 = "80";

	private static final String TITLE_3 = "Fifa 19";

	private static final String TITLE_2 = "Resident Evil 2";

	private static final String TITLE_1 = "Subnautica";

	@InjectMocks
	private ParseMetacriticSonyModelImpl parse;
	
	@Mock
	private Connection connection;
	
	@Mock
	private Elements links;
	
	@Mock
	private Elements info;
	
	@Mock
	private Document doc;
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void accessPageTest() throws IOException {
		PowerMockito.mockStatic(Jsoup.class);
		List<String> titles = Arrays.asList(TITLE_1, TITLE_2, TITLE_3);
		List<String> scores = Arrays.asList(SCORE_1, SCORE_2, SCORE_3);
		Mockito.when(Jsoup.connect(Mockito.anyString())).thenReturn(connection);
		Mockito.when(connection.get()).thenReturn(doc);
		Mockito.when(doc.select(Mockito.anyString())).thenReturn(links);
		Mockito.when(doc.getElementsByClass(Mockito.anyString())).thenReturn(info);
		Mockito.when(info.not(Mockito.anyString())).thenReturn(info);
		Mockito.when(info.after(Mockito.anyString())).thenReturn(info);
		Mockito.when(links.eachText()).thenReturn(titles);
		Mockito.when(info.eachText()).thenReturn(scores);
		Mockito.when(links.size()).thenReturn(titles.size());
		
		parse.accessPage();
		Assert.assertEquals(3, parse.getGameServiceImpl().getAllGames().size());
		Assert.assertEquals(TITLE_1, parse.getGameServiceImpl().getAllGames().get(0).getTitle());
		Assert.assertEquals(TITLE_2, parse.getGameServiceImpl().getAllGames().get(1).getTitle());
		Assert.assertEquals(TITLE_3, parse.getGameServiceImpl().getAllGames().get(2).getTitle());
		Assert.assertEquals(Integer.parseInt(SCORE_1), parse.getGameServiceImpl().getAllGames().get(0).getScore());
		Assert.assertEquals(Integer.parseInt(SCORE_2), parse.getGameServiceImpl().getAllGames().get(1).getScore());
		Assert.assertEquals(Integer.parseInt(SCORE_3), parse.getGameServiceImpl().getAllGames().get(2).getScore());
	}
}
