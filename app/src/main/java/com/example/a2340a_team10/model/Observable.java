package com.example.a2340a_team10.model;

public interface Observable {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void removeAllObservers();
    void notifyAllObservers();
}
