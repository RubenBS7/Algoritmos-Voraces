package gic.fds;

import java.util.Comparator;

public class PlayerSorterByName implements Comparator<Player> {
	 @Override
     public int compare(Player o1, Player o2) {
         return o1.getName().compareToIgnoreCase(o2.getName());
     }
	

}
