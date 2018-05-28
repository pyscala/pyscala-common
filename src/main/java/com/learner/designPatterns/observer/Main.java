package com.learner.designPatterns.observer;

/**
 * @author : liufangliang
 * @description :
 * @date : 2017\12\28 0028
 */
public class Main {

    public static void main(String[] args) {
        SpiderSubject subject = new SpiderSubject();
        ParserObserver observer1= new ParserObserver(subject){
            @Override
            public void parse(String info) {
                System.out.println(info+":dddddd");
            }
        };
        subject.setInfo("100");
    }
}
