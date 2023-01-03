package checkers.Utils;

import java.util.Observable;

public class ObservableValue<T> extends Observable {

    private T value;

    public ObservableValue(T value) {
        this.value = value;
    }

    public T getValue() {
        return value;
    }

    public void setValue(T value) {
        this.value = value;
        this.setChanged();
        System.out.println(this.countObservers());
        this.notifyObservers();
    }
}
