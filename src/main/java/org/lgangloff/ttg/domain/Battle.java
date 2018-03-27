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

	public void play() {
		Map<Player, Card> game = new HashMap<>();
		lastResult = -1;
		for (Player player : players) {
			Card cardPlayed = player.play();
			game.put(player, cardPlayed);
			lastResult = Math.max(cardPlayed.value, lastResult);
		}
		
		historise(game);
		while(isBattle()) {
			play();
		}
	}

	private void historise(Map<Player, Card> game) {
		history.push(game);
		String depth  = StringUtils.repeat(" ", history.size());
		System.out.print(depth + game);
		
		if (isBattle())
			System.out.println("=> Battle");
		else
			System.out.println( "=> Winner: " + getWinner());
		
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
		return history.stream().flatMap(map->map.values().stream()).collect(Collectors.toList());
	}
}
