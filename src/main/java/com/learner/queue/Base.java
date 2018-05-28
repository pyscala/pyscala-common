package com.learner.queue;

public interface Base {

    void call(BaseLisener baseLisener);

   interface BaseLisener<T>{
       void success(T t);
       void failed();
   };
}

