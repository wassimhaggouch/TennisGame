package com.codingDojo.tennisGame;

import org.junit.Test;

import static org.junit.Assert.*;

public class TennisGameTest {
	TennisGame game = new TennisGame("Novak", "Roger");
	
	private void setGameDeuce() {
    	game.resetScore();
    	
    	//Fifteen
    	game.firstPlayerScores();
    	game.secondPlayerScores();
    	
    	//Thirty
    	game.firstPlayerScores();
    	game.secondPlayerScores();
    	
    	//Deuce
    	game.firstPlayerScores();
    	game.secondPlayerScores();
	}
	
    @Test
    public void testNewGame() {
    	game.resetScore();
    	assertEquals(0, game.getFirstPlayerScore());
    	assertEquals(0, game.getSecondPlayerScore());
    }
    
    @Test
    public void testPlayerscores() {
    	game.resetScore();
    	game.firstPlayerScores();
    	game.secondPlayerScores();
    	assertEquals("Fifteen", TennisGame.SCORE_ENUM.getScore(game.getFirstPlayerScore()));
    	assertEquals("Fifteen", TennisGame.SCORE_ENUM.getScore(game.getSecondPlayerScore()));
    	assertFalse(game.isGameFinished());
    }
    
    @Test
    public void testDeuce() {
    	setGameDeuce();
    	assertTrue(game.isDeuce());
    	assertFalse(game.isGameFinished());
    }
    
    @Test
    public void testFirstPlayerAdvantage() {
    	setGameDeuce();
    	
    	//1st player has Advantage
    	game.firstPlayerScores();

    	assertFalse(game.hasSecondPlayerAdvantage());
    	assertFalse(game.isDeuce());
    	assertTrue(game.hasFirstPlayerAdvantage());
    	assertFalse(game.isGameFinished());
    }
    
    @Test
    public void testSecondPlayerAdvantage() {
    	setGameDeuce();
    	
    	//1st player has Advantage
    	game.secondPlayerScores();

    	assertFalse(game.hasFirstPlayerAdvantage());
    	assertFalse(game.isDeuce());
    	assertTrue(game.hasSecondPlayerAdvantage());
    	assertFalse(game.isGameFinished());
    }

    @Test
    public void testFirstPlayerWins() {
    	game.resetScore();
    	//Fifteen
    	game.firstPlayerScores();
    	//Thirty
    	game.firstPlayerScores();
    	//Deuce
    	game.firstPlayerScores();
    	//1st player won
    	game.firstPlayerScores();
    	
    	assertFalse(game.hasFirstPlayerAdvantage());
    	assertFalse(game.hasSecondPlayerAdvantage());
    	assertFalse(game.isDeuce());
    	assertFalse(game.hasSecondPlayerWon());
    	assertTrue(game.hasFirstPlayerWon());
    	assertTrue(game.isGameFinished());
    }
    
    @Test
    public void testFirstPlayerWinsAfterAdvantage() {
    	setGameDeuce();
    	
    	//1st player has Advantage
    	game.firstPlayerScores();
    	
    	//1st player won
    	game.firstPlayerScores();
    	
    	assertFalse(game.hasFirstPlayerAdvantage());
    	assertFalse(game.hasSecondPlayerAdvantage());
    	assertFalse(game.isDeuce());
    	assertFalse(game.hasSecondPlayerWon());
    	assertTrue(game.hasFirstPlayerWon());
    	assertTrue(game.isGameFinished());
    }

    @Test
    public void testSecondPlayerWins() {
    	game.resetScore();
    	//Fifteen
    	game.secondPlayerScores();
    	//Thirty
    	game.secondPlayerScores();
    	//Deuce
    	game.secondPlayerScores();
    	//1st player won
    	game.secondPlayerScores();
    	
    	assertFalse(game.hasFirstPlayerAdvantage());
    	assertFalse(game.hasSecondPlayerAdvantage());
    	assertFalse(game.isDeuce());
    	assertFalse(game.hasFirstPlayerWon());
    	assertTrue(game.hasSecondPlayerWon());
    	assertTrue(game.isGameFinished());
    }
    
    @Test
    public void testSecondPlayerWinsAfterAdvantage() {
    	setGameDeuce();
    	
    	//1st player has Advantage
    	game.secondPlayerScores();
    	
    	//1st player won
    	game.secondPlayerScores();
    	
    	assertFalse(game.hasFirstPlayerAdvantage());
    	assertFalse(game.hasSecondPlayerAdvantage());
    	assertFalse(game.isDeuce());
    	assertFalse(game.hasFirstPlayerWon());
    	assertTrue(game.hasSecondPlayerWon());
    	assertTrue(game.isGameFinished());
    }
}
