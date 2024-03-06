package e1;

public class LogicInitializationConfigurationImpl implements LogicInitializationConfiguration {

    private final Pair<Integer, Integer> knightPosition;
    private final Pair<Integer, Integer> pawnPosition;

    public LogicInitializationConfigurationImpl(Pair<Integer, Integer> knightPosition,
            Pair<Integer, Integer> pawnPosition) {
        this.knightPosition = knightPosition;
        this.pawnPosition = pawnPosition;
    }

    @Override
    public Pair<Integer, Integer> knightPosition() {
        return knightPosition;
    }

    @Override
    public Pair<Integer, Integer> pawnPosition() {
        return pawnPosition;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((knightPosition == null) ? 0 : knightPosition.hashCode());
        result = prime * result + ((pawnPosition == null) ? 0 : pawnPosition.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        LogicInitializationConfigurationImpl other = (LogicInitializationConfigurationImpl) obj;
        if (knightPosition == null) {
            if (other.knightPosition != null)
                return false;
        } else if (!knightPosition.equals(other.knightPosition))
            return false;
        if (pawnPosition == null) {
            if (other.pawnPosition != null)
                return false;
        } else if (!pawnPosition.equals(other.pawnPosition))
            return false;
        return true;
    }

    
}
