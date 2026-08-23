package signalcraft.entities.levelCrossings;

public interface IAnglesAddable {

    void addAngle(int newAngle);
    int[] getAngles();
    void removeLastAngle();
}
