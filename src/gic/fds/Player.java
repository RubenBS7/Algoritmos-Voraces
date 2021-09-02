package gic.fds;

public class Player {
	    private String position;  // posicion jugador
	    private String name;     // nombre jugador
	    private int points;     // posicion jugador
	    private int value;     // precio jugador
	    
	    
	    public Player(String position, String name, int points, int value){
	        this.position = position;
	        this.name = name;
	        this.points = points;
	        this.value = value;
	    }

	    
	    
	    public String getPosition() {
	        return position;
	    }

	    public String getName() {
	        return name;
	    }
	    
	    public int getPoints() {
	        return points;
	    }
	    
	    public int getValue() {
	        return value;
	    }
	    
	    public void setPosition(String position) {
	        this.position = position;
	    }

	    public void setName(String name) {
	        this.name = name;
	    }

	    public void setPoints(int points) {
	        this.points = points;
	    }

	    public void setValue(int value) {
	        this.value = value;
	    }

	   

	    public void print(){ // prime los valores de la clase player
	        System.out.println(this.getPosition() + " " + this.getName() + " " + this.getPoints() + " " + this.getValue());
	    }

	    
	    
	    public void printDetail(){ // imprime la informacion de cada jugador
	        System.out.println("Posición: " + this.getPosition());
	        System.out.println("Nombre: " + this.getName());
	        System.out.println("Puntos: " + this.getPoints());
	        System.out.println("Valor: " + this.getValue());
	    }
}
