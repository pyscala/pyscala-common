package com.learner.designPatterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : liufangliang
 * @description :
 * @date : 2017\12\28 0028
 */
public class SpiderSubject implements Subject {

    private List<Observer>  observers= new ArrayList<Observer>();

    private String info;

    @Override
    public void addObserver(Observer o) {
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        int i = observers.indexOf(o);
        if(i>=0){
            observers.remove(o);
        }
    }

    @Override
    public void notifyObserver() {
        for (Observer observer : observers) {
            observer.parse(info);
        }
    }


    public void setInfo(String info){
        this.info= info;
        this.notifyObserver();
    }
}
