package org.lgangloff.ttg.domain;

import java.util.ArrayList;
import java.util.List;

public class Player {

	private String name;
	private ArrayList<Card> cards = new ArrayList<>();
	
	
	public Player(String name) {
		super();
		this.name = name;
	}

	public void add(Card card) {
		this.cards.add(card);
	}
	
	public Card play() {
		return countCard() == 0 ? Card.NO_CARD :  this.cards.remove(0);
	}
	
	public void displayGame() {
		System.out.println(name + " a " + this.cards.size() + " cartes");
	}

	public int countCard() {
		return this.cards.size();
	}

	@Override
	public String toString() {
		return name;
	}

	public void add(List<Card> allCardPlayed) {
		this.cards.addAll(allCardPlayed);
	}

	public void displayCards() {
		System.out.println(name + " a " + this.cards.size() + " cartes: " + this.cards);
	} 
	
	
	
}
