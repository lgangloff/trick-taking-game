package org.lgangloff.ttg.domain;

public class Card {
	public Card(int value, String name, CardType type) {
		this.value = value;
		this.name = name;
		this.type = type;
	}
	int value;
	private String name;
	private CardType type;
	
	@Override
	public String toString() {
		return " [" + name + " de " + type + "]";
	}
	
	
}
