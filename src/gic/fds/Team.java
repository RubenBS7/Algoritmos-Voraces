package gic.fds;

import java.util.ArrayList;

public class Team {
	private String name;     //nombre equipo
	private int budget;      //presupuesto
	private Players players; //objeto clase jugadores
	
	public String getName(){
		return this.name;
	}
	
	public int getBudget(){
		return this.budget;
	}

	public Players getPlayers(){
		return this.players;
	}
	
	public void setBudget(int budget){
		this.budget = budget;
	}
	
	
	public Team(String name, int initialBudget){
		this.name = name;
		this.budget = initialBudget;
		this.players = new Players();
	}
	
	public void addPlayer(Player player){ //añadir jugador
		this.players.addPlayer(player);
	}
	
	public void removePlayer(Player player){ //eliminar jugador
		this.players.removePlayer(player);
	}
	
	public void print() {
        System.out.println("########################################");
        System.out.println("-- EQUIPO: " + this.getName());
        this.players.print();
        System.out.println("------------------------------");
        System.out.println("Presupuesto restante: " + this.getBudget());
        System.out.println("------------------------------");
        System.out.println("########################################");
	}
}
