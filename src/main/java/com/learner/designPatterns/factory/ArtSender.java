package com.learner.designPatterns.factory;

public class ArtSender implements Sender {

    public void send(String message) {
        System.out.println("ARTSender"+message);
    }
}
