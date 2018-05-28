package com.learner.queue;

import java.util.PriorityQueue;

public class ReqPriorityQueue extends PriorityQueue<Request> {

    public void add(char primary,int sencondary,Base.BaseLisener item){
        super.add(new Request(primary,sencondary,item));
    }
}
