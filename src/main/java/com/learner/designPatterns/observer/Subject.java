package com.learner.designPatterns.observer;

/**
 * @author : liufangliang
 * @description :
 * @date : 2017\12\28 0028
 */
public interface Subject {

    void addObserver(Observer o);
    void removeObserver(Observer o);
    void notifyObserver();
}
