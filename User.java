package com.wipro.elevator.entity;

public class User {
	
	private int weight;
	private int source;
	private int destination;
	
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public int getSource() {
		return source;
	}
	public void setSource(int source) {
		this.source = source;
	}
	public int getDestination() {
		return destination;
	}
	public User(int weight, int source, int destination) {
		super();
		this.weight = weight;
		this.source = source;
		this.destination = destination;
	}
	public void setDestination(int destination) {
		this.destination = destination;
	}

}
