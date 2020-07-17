package com.codingDojo.tennisGame;

import java.util.Scanner;

public class TennisGame {
	// Player names
	private static String firstPlayer;
	private static String secondPlayer;
	
	// Players scores
	private static int firstPlayerScore;
	private static int secondPlayerScore;

	public TennisGame(String firstPlayer, String secondPlayer) {
		this.firstPlayer = firstPlayer;
		this.secondPlayer = secondPlayer;
		this.firstPlayerScore = 0;
		this.secondPlayerScore = 0;
	}
	
	public enum SCORE_ENUM {
        SCORE_LOVE(0, "Love"),
        SCORE_FIFTEEN(1, "Fifteen"),
        SCORE_THIRTY(2, "Thirty"),
        SCORE_FORTY(3, "Forty"),
        SCORE_ADVANTAGE(4, "Advantage");

        private int code;
        private String label;

        SCORE_ENUM(int code, String label) {
            this.code = code;
            this.label = label;
        }

        public static String getScore(int id) {
            for (SCORE_ENUM e : values()) {
                if (e.code == id) return e.label;
            }
            return null;
        }
    }
	
	/**
	 * Add a point if the 1st player scores
	 * If deuce: Advantage for 1st player
	 * If 2nd player has advantage: deuce
	 */
	private void firstPlayerScores() {
    	if (secondPlayerScore == 4) {
    		secondPlayerScore--;
    	} else {
    		firstPlayerScore++;
    	}
	}
	
	/**
	 * Add a point if the 2nd player scores
	 * If deuce: Advantage for 2nd player
	 * If 1st player has advantage: deuce
	 */
	private void secondPlayerScores() {
    	if (firstPlayerScore == 4) {
    		firstPlayerScore--;
    	} else {
    		secondPlayerScore++;
    	}
	}
	
	/**	
	 * @return true if the 1st player won the game
	 * else return false
	 */
	private boolean hasFirstPlayerWon() {
		if (firstPlayerScore == 4 && secondPlayerScore < 3) {
			return true;
		} else if (firstPlayerScore == 5 &&  secondPlayerScore ==3) {
			return true;
		} else {
		return false;
		}
	}
	
	/**	
	 * @return true if the 2nd player won the game
	 * else return false
	 */
	private boolean hasSecondPlayerWon() {
		if (secondPlayerScore == 4 && firstPlayerScore < 3) {
			return true;
		} else if (secondPlayerScore == 5 &&  firstPlayerScore ==3) {
			return true;
		} else {
		return false;
		}
	}
	
	/**
	 * @return true if the game is finished
	 */
	private boolean isGameFinished() {
		return hasFirstPlayerWon() || hasSecondPlayerWon();
	}
	
	private boolean isDeuce() {
		return firstPlayerScore == 3 && secondPlayerScore == firstPlayerScore;
	}
	
	private boolean hasFirstPlayerAdvantage() {
		return firstPlayerScore == 4 && secondPlayerScore == 3;
	}
	
	private boolean hasSecondPlayerAdvantage() {
		return secondPlayerScore == 4 && firstPlayerScore == 3;
	}
	
	
	/**
	 * Test the methods in console
	 */
	public static void main(String[] args) {
		  
		// Init the game and read player names 
		Scanner sc = new Scanner(System.in);
		System.out.println("Please insert first player's name:"); 
		firstPlayer = sc.nextLine();
		System.out.println("Please insert second player's name:");
		secondPlayer = sc.nextLine();
		
		TennisGame game = new TennisGame(firstPlayer, secondPlayer);

		//Scanner input = new Scanner(System.in);
		while (!game.isGameFinished()) {
			System.out.println("\nWho scored the point? (Enter 1 or 2)");
		    System.out.println(String.format("  1 - %s scored a point", firstPlayer));
		    System.out.println(String.format("  2 - %s scored a point", secondPlayer));
		      
		    switch (sc.nextInt()) {
		    case 1:
		    	game.firstPlayerScores();
		        break;
		    case 2:
		    	game.secondPlayerScores();
		        break;
		    default:
		    	System.out.println("Unexpected choice, please retry.");
		    }
		    if(!game.isGameFinished()) {
		    	if (game.isDeuce()) {
		    		System.out.println("Current Score: Deuce");
		    	} else if(game.hasFirstPlayerAdvantage()) {
		    		System.out.println(String.format("Current Score: Advantage for %s", firstPlayer));
		    	} else if(game.hasSecondPlayerAdvantage()) {
		    		System.out.println(String.format("Current Score: Advantage for %s", secondPlayer));
		    	} else {
		    		System.out.println(String.format("Current Score: %s: %s - %s: %s",
		    		firstPlayer, SCORE_ENUM.getScore(firstPlayerScore),
			    	secondPlayer, SCORE_ENUM.getScore(secondPlayerScore)));
		    	}
		    			
		    }
		    System.out.println("-------------------------\n");
		}
		
		if(game.hasFirstPlayerWon()) {
			System.out.println(String.format("%s won the game", firstPlayer));
		} else if(game.hasSecondPlayerWon()) {
			System.out.println(String.format("%s won the game", secondPlayer));
		}
		
  }
}