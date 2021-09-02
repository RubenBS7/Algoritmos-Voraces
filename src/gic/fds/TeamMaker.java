package gic.fds;

import java.util.ArrayList;
import java.util.Iterator;

public class TeamMaker { // CLASE CREADOR DE EQUIPOS
	private int numbeOfTeams; // nº de equipos
	private int maxValue; // valor maximo
	private Players players; // objeto clase jugadores
	private ArrayList<Team> teams; // ArrayList para almacenar equipos creados

	public int getNumberOfTeams() {
		return this.numbeOfTeams;
	}

	public int getMaxValue() {
		return this.maxValue;
	}

	public Players getPlayers() {
		return this.players;
	}

	public TeamMaker(int numberOfTeams, int maxValue, Players players) {
		this.numbeOfTeams = numberOfTeams;
		this.maxValue = maxValue;
		this.players = players;

		teams = new ArrayList<Team>();

		for (int idx = 1; idx <= this.getNumberOfTeams(); idx++) {
			Team team = new Team("Equipo nuemero " + idx, this.getMaxValue());
			this.teams.add(team);
		}
	}

	public void print() {
		for (Team team : this.teams) {
			team.print();
		}
	}

	public void makeTeamsMode1(){
		//Eliminamos los equipos vacios 
        teams.clear(); 
      //Ordenamos por point a los jugadores
        players.sortByPoints(); 
        
        Players jugadores=new Players();
        players.copiarJugadores(jugadores);
        
        for(int i=0;i<getNumberOfTeams();i++){
        	
        	// Se guardan los no eligidos en equipoTemporal para otros posibles equipos.
            Players JugadoresNoUsados=new Players(); 
           // Creamos un equipoTemporal.
            Team equipoTemporal=new Team("Equipo nº: " +(teams.size()+1),maxValue); 
            while(jugadores.getNumberOfPlayers()!=0){
                if(equipoTemporal.getBudget()>=jugadores.getFirstPlayer().getValue()){
                    equipoTemporal.addPlayer(jugadores.getFirstPlayer());
                    equipoTemporal.setBudget(equipoTemporal.getBudget()-jugadores.getFirstPlayer().getValue());
                    jugadores.removePlayer(jugadores.getFirstPlayer());
                }else{
                    // Añadimos a JugadoresNoUsados los jugadores 
                    JugadoresNoUsados.addPlayer(jugadores.getFirstPlayer());
                    // Eliminamos de la lista
                    jugadores.removePlayer(jugadores.getFirstPlayer());
                }
            }
          // Añadimos el equipoTemporal a la lista de equipos, sin contar los ya utilizados.
            teams.add(equipoTemporal); 
            jugadores=JugadoresNoUsados; 
        }
    }

    public void makeTeamsMode2(int minNumberOfPlayersPerTeam) {
    	// Ordenamos el arrayList por puntos.
    	players.sortByPoints(); 
        
        Players jugadores=new Players();
        // Copiamos la lista de jugadores.
        players.copiarJugadores(jugadores); 
      // Creamos una lista de jugadores para poder organizarla.
        Players JugadoresNoUsados = new Players(); 
        
        for(int i=0;i<getNumberOfTeams();i++){
        	//Creamos un equipoTemporal temporal
            Team equipoTemporal=new Team("equipoTemporal número " +(teams.size()+1),maxValue); 
            
            jugadores.sortByPoints(); 
            Players listaOrganizada=new Players(); 
            
            if(equipoTemporal.getPlayers().getNumberOfPlayers() < 0){
            	System.out.println("NO SE PUEDEN GERAR EQUIPOS:\nel presupuesto total indicado puede ser muy peque񯜮el número de jugadores m?mo puede ser muy grande\nuna combinaci󮠤e las dos");
            	
            	System.exit(0);
            	
            }else{
                while(equipoTemporal.getPlayers().getNumberOfPlayers()<minNumberOfPlayersPerTeam){
                	
                    if(equipoTemporal.getBudget() >= jugadores.getFirstPlayer().getValue() ){ 
                        //Si tenemos presupuesto suficiente añadimos el jugador a equipoTemporal.
                        Player jugador=jugadores.getFirstPlayer();
                        equipoTemporal.addPlayer(jugador);
                        equipoTemporal.setBudget(equipoTemporal.getBudget()-jugador.getValue());
                        jugadores.removePlayer(jugador);
                    }else{ 
                        //Si no hay presupuesto suficiente, se añade el jugador a la lista "JugadoresNoUsados".
                        Player jugador=jugadores.getFirstPlayer();
                        JugadoresNoUsados.addPlayer(jugador);
                        jugadores.removePlayer(jugador);
                    }
                    if((equipoTemporal.getPlayers().getNumberOfPlayers() != minNumberOfPlayersPerTeam) && (jugadores.getNumberOfPlayers() == 1)){
                        //Si solo nos queda 1 elemento en la lista de jugadores, y el equipoTemporal aun no tiene los miembros necesarios,
                        //borramos el jugador más caro del equipoTemporal y lo añadimos a la lista de listaOrganizada, así el equipoTemporal recupera presupuesto.
                        listaOrganizada.addPlayer(equipoTemporal.getPlayers().getFirstPlayer());
                        equipoTemporal.setBudget(equipoTemporal.getBudget()+equipoTemporal.getPlayers().getFirstPlayer().getValue());
                        equipoTemporal.removePlayer(equipoTemporal.getPlayers().getFirstPlayer());
                        JugadoresNoUsados.addPlayer(jugadores.getFirstPlayer());
                        JugadoresNoUsados.sortByPoints();
                        jugadores.removePlayer(jugadores.getFirstPlayer());
                        
                      //Copiamos la lista "JugadoresNoUsados" en "jugadores" para poder seguir buscando jugadores.
                        JugadoresNoUsados.copiarJugadores(jugadores); 
                      //Vaciamos la lista de JugadoresNoUsados para que no se multiplique exponencialmente.
                        JugadoresNoUsados=new Players(); 
                    }
                }
            }

            //Ordenamos los jugadores del equipoTemporal por puntos
            equipoTemporal.getPlayers().sortByPoints(); 
            //Añadimos el equipoTemporal creado a la lista de equipos.
            teams.add(equipoTemporal); 
            
            //Pasamos los elementos de "JugadoresNoUsados" y "listaOrganizada" a "jugadores".
            //De esta manera el siguiente equipoTemporal no tendrá jugadores del equipoTemporal.
            for(int j = 0;j<listaOrganizada.getNumberOfPlayers();j++){
                jugadores.addPlayer(listaOrganizada.getFirstPlayer());
                listaOrganizada.removePlayer(listaOrganizada.getFirstPlayer());
            }
            
            for(int k = 0;k<JugadoresNoUsados.getNumberOfPlayers();k++){
                jugadores.addPlayer(JugadoresNoUsados.getFirstPlayer());
                JugadoresNoUsados.removePlayer(JugadoresNoUsados.getFirstPlayer());
            }
        }
    }
}
