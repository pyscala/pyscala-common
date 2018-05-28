package com.learner.designPatterns.factory;

public class SenderFactory {

        public  Sender getMessageSender(){
            return new MessageSender();
        }

        public Sender getArtSender(){
            return new ArtSender();
        }
}
