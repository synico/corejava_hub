package javac.generic;

public class Pair<T> {

    private T first;

    private T second;

    public Pair() {
        first = null;
        second = null;
    }

    public Pair(T first, T second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return this.first;
    }

    public void setFirst(T newValue) {
        this.first = newValue;
    }

    public T getSecond() {
        return this.second;
    }

    public void setSecond(T newValue) {
        this.second = newValue;
    }

}
