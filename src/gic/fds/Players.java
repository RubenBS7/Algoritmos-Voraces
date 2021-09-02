package gic.fds;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;


public class Players {
    
	private ArrayList<Player> players; //Arraylist de los jugadores "EXCELL"
    
    
    
    public Players() {
        this.players = new ArrayList<>();
    }
	
    
    public int getNumberOfDL() {
        return this.countPositions("DL");
    }

    public int getNumberOfMC() {
        return this.countPositions("MC");
    }

    public int getNumberOfDF() {
        return this.countPositions("DF");
    }

    public int getNumberOfPT() {
        return this.countPositions("PT");
    }

    public int getValue() { // se calcula el valor total de los jugadores
        int totalValue = 0;
        for (Player player : this.players) {
            totalValue += player.getValue();
        }
        return totalValue; 
    }

    public int getPoints() { // se calcula el numero de puntos total de los jugadores
        int totalPoints = 0;
        for (Player player : this.players) {
            totalPoints += player.getPoints();
        }
        return totalPoints;
    }

    public double getValueAverage() { // media del valor
        return ((double)this.getValue() / (double)this.players.size());
    }

    public double getPointsAverage() { // media de puntos
        return ((double)this.getPoints() / (double)this.players.size());
    }
    
    
    
    public ArrayList<Player> getPlayers() {
		return players;
	}


	public void setPlayers(ArrayList<Player> players) {
		this.players = players;
	}


	public void load(String filename){ // cargamos el archivo .csv con la bateria de jugadores
        try (Scanner reader = new Scanner(new File(filename))) {
            while (reader.hasNextLine()) {
                String playerEntry = reader.nextLine();
                String[] fields = playerEntry.split(";");

                String position = fields[0];
                String name = fields[1];
                int points = Integer.parseInt(fields[2]);
                int value = Integer.parseInt(fields[3].trim());
                players.add(new Player(position, name, points, value));
            }
            reader.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public int getNumberOfPlayers() { return this.players.size(); } // contar el numero de los jugadores

    public void printPlayers() { // mostramos los atributos de cada jugador
        for (Player player: this.players) { player.print(); };
    }

    public void print() { 
        String doubleFormat = "%.3f%n";
        System.out.println("------- JUGADORES ------------");
        for (Player player : this.players) { player.print(); }
        System.out.println("------------------------------");
        System.out.println("Jugadores: " + this.players.size());
        System.out.println("    Porteros: " + this.getNumberOfPT());
        System.out.println("    Defensas: " + this.getNumberOfDF());
        System.out.println("    Centrocampistas: " + this.getNumberOfMC());
        System.out.println("    Delanteros: " + this.getNumberOfDL());
        System.out.println("------------------------------");
        System.out.println("Valor total: " + this.getValue());
        System.out.println("Valor medio: " + String.format(doubleFormat, this.getValueAverage()));
        System.out.println("Puntos totales: " + this.getPoints());
        System.out.println("Media de puntos: " + String.format(doubleFormat, this.getPointsAverage()));
    }

    public void sortByValue() {  // ordenar por valor
        this.players.sort(new PlayerSorterByValue());
    }

    public void sortByName() { // ordenar por nombre
        this.players.sort(new PlayerSorterByName());
    }

    public void sortByPoints() { // ordenar por puntos
        this.players.sort(new PlayerSorterByPoints());
    }

    public void addPlayer(Player playerToAdd) { // añadir jugador
        this.players.add(playerToAdd);
    }

    public void removePlayer(Player playerToRemove) { // borrar jugador
        this.players.remove(playerToRemove);
    }

    public Player getFirstPlayer(int maxValue) { //delvuelve el jugador siempre y cuando su valor sea <= al valor maximo
        for (int idx = 0; idx < this.players.size(); idx++) {
            if (this.players.get(idx).getValue() <= maxValue) {
                return this.players.get(idx);
            }
        }
        return null;
    }

    
    public Player getFirstPlayer() {       // hace lo mismo que el metodo getFirstPlayer pero le pasamos el maximo valor de los jugadores
    	return this.getFirstPlayer(Integer.MAX_VALUE); 
    } 

    
    private int countPositions(String positionId){  
        int items = 0;
        for (Player player : this.players) { // si el id existe entonces el jugador exite y se suma al contador
            if (player.getPosition().toLowerCase().contains(positionId.toLowerCase())) items++; 
        }
        return items;
    }
    
    
    public void copiarJugadores(Players cp){ //Hacemos una copia de los jugadores
    	
    	for(int i = 0; i < this.players.size(); i++){
    		
    		cp.addPlayer(this.players.get(i));
    	}
    }
}
