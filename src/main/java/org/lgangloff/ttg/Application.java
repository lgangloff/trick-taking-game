package org.lgangloff.ttg;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.lgangloff.ttg.domain.Battle;
import org.lgangloff.ttg.domain.Card;
import org.lgangloff.ttg.domain.CardGame;
import org.lgangloff.ttg.domain.Player;

public class Application{
	

	final static int GAME_SIZE = 32;
	final static List<Player> players = new ArrayList<>();

	public static void main(String[] args) {
		CardGame game = new CardGame(GAME_SIZE);
		
		players.add(new Player("Loïc"));
		players.add(new Player("Nathan"));
		players.add(new Player("Jessy"));
		players.add(new Player("Dylan"));
		

		game.distribute(players);
		
		displayPlayersGame();
		
		int round = 0;
		while(!playerWithAllCard().isPresent()) {
			Battle battle = new Battle(players);
			battle.play(true);
			List<Card> allCardPlayed = battle.getAllCardPlayed();
			battle.getWinner().getKey().add(allCardPlayed);
			round++;
			displayPlayersGame();
			
			if (round > 5000) {
				for (Player player : players) {
					player.displayCards();
				}
				System.out.println("Unable to find a winner after "+round+" rouds");
				System.exit(0);;
			}
		}
		System.out.println(playerWithAllCard().get() + " wins in "+round+" rouds");
	}
	
	
	private static Optional<Player> playerWithAllCard() {
		return players.stream().filter(p->p.countCard() == GAME_SIZE).findFirst();
	}


	public static void displayPlayersGame() {
		for (Player player : players) {
			player.displayGame();
		}
	}
}
