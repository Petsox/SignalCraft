package signalcraft.entities;

public interface IActivatable {

    void setIsActive(Boolean active);
    Boolean getIsActive();
    void setBlinkCounter(int counter);

}
