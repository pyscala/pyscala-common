package com.learner.designPatterns.observer;

/**
 * @author : liufangliang
 * @description :
 * @date : 2017\12\28 0028
 */
public abstract class ParserObserver implements Observer {

    private SpiderSubject spider;

    public ParserObserver(SpiderSubject spider) {
        this.spider = spider;
        spider.addObserver(this);
    }
}
