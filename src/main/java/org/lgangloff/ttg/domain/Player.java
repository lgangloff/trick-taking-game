package org.lgangloff.ttg.domain;

import java.util.List;
import java.util.Stack;

public class Player {

	private String name;
	private Stack<Card> cards = new Stack<>();
	
	
	public Player(String name) {
		super();
		this.name = name;
	}

	public void add(Card card) {
		this.cards.addElement(card);
	}
	
	public Card play() {
		return this.cards.pop();
	}
	
	public void displayGame() {
		//System.out.println(name + " a " + this.cards.size() + " cartes: " + this.cards);
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
	
	
	
}
