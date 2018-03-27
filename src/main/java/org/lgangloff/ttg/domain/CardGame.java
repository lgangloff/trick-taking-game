package org.lgangloff.ttg.domain;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CardGame {

	private List<Card> cards;
	static final Map<Integer, String> VALUES = new HashMap<>();

	static {

		for (int j = 2; j <= 10; j++) {
			VALUES.put(j, String.valueOf(j));
		}
		VALUES.put(11, "Valet");
		VALUES.put(12, "Dame");
		VALUES.put(13, "Roi");
		VALUES.put(14, "As");
	}
	
	public CardGame(int size) {
		cards = new ArrayList<>(size);
		for (int i = 14; cards.size() < size && i > 0; i--) {
			for (CardType type : CardType.values()) {
				cards.add(new Card(i, VALUES.get(i), type));
			}
		}
	}

	public void distribute(List<Player>players) {
		while (!cards.isEmpty()) {
			for (Player player : players) {
				player.add(getRandomCard());
				if (cards.isEmpty())
					break;
			}
		}
	}

	private Card getRandomCard() {
		return cards.remove((int) (Math.random()*cards.size()));
	}
}
