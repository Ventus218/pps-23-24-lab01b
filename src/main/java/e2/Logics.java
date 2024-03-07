package e2;

import java.util.Set;

public interface Logics {

    Set<Pair<Integer, Integer>> mines();

    boolean hit(Pair<Integer, Integer> position);

}
