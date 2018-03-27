package org.lgangloff.ttg.domain;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Stack;
import java.util.stream.Collectors;

import org.apache.commons.lang3.StringUtils;

public class Battle {

	private Stack<Map<Player, Card>> history = new Stack<>();
	int lastResult = -1;
	private List<Player> players;
	
	public Battle(List<Player> players) {
		this.players = players;
	}

	public void play(boolean show) {
		Map<Player, Card> game = new HashMap<>();
		lastResult = -1;
		for (Player player : players) {
			Card cardPlayed = player.play();
			game.put(player, cardPlayed);
			lastResult = Math.max(cardPlayed.value, lastResult);
		}
		
		historise(game, show);
		while(show && isBattle()) {
			play(false);
			play(true);
		}
	}

	private void historise(Map<Player, Card> game, boolean show) {
		history.push(game);
		String depth  = StringUtils.repeat(" ", history.size() - (show ? 1 : 0));
		System.out.print(depth + (!show ? "[hidden]" : "") + game);
		
		if (isBattle())
			System.out.println("=> Battle");
		else if(show)
			System.out.println( "=> Winner: " + getWinner());
		else
			System.out.println();
	}
	
	public Entry<Player, Card> getWinner() {
		for (Entry<Player, Card> entry : this.history.peek().entrySet()) {
			if (entry.getValue().value == lastResult) {
				return entry;
			}
		}
		throw new IllegalAccessError("BATTLE !");
	}

	public boolean isBattle() {
		Collection<Card> lastCardsPlayed = this.history.peek().values();
		return lastCardsPlayed.stream().filter(c->c.value == lastResult).count() > 1;
	}

	public List<Card> getAllCardPlayed() {
		return history.stream().flatMap(map->map.values().stream()).filter(card->card!=Card.NO_CARD).collect(Collectors.toList());
	}
}
