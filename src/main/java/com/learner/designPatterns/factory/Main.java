package com.learner.designPatterns.factory;

public class Main {
    public static void main(String[] args) {
        SenderFactory factory=new SenderFactory();
        Sender sender= factory.getMessageSender();
        sender.send("ddd");
        Sender sender1= factory.getArtSender();
        sender1.send("bbb");
    }
}
