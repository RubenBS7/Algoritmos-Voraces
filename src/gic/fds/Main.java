package gic.fds;

public class Main {
    
	public static void main(String[] args) {

        Players allPlayers = new Players();
        allPlayers.load("listado_jugadores.csv");
        allPlayers.sortByName();
        allPlayers.print();

        TeamMaker generator = new TeamMaker(3, 40000000, allPlayers);
        generator.makeTeamsMode1();
        generator.makeTeamsMode2(3);
        generator.print();
    }
}
