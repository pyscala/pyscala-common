package com.learner.designPatterns.factory;

public class MessageSender implements Sender {

    public void send(String message) {
        System.out.println("MessageSender"+message);
    }
}
