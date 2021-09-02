package gic.fds;

import java.util.Comparator;
import java.lang.Integer;


public class PlayerSorterByPoints implements Comparator<Player> {
	@Override
	public int compare(Player o1, Player o2){
		return Integer.compare(o2.getPoints(), o1.getPoints());
	}

}
