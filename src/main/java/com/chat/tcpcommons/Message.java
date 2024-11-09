/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons;

import java.io.Serializable;

/**
 *
 * @author felix
 */
public class Message implements Serializable {

    private Object body;
    private User sender;
    private User receiver;
    private MessageType messageType;

    public Message() {
    }

    public Message(Object body, User sender, User receiver, MessageType messageType) {
        this.body = body;
        this.sender = sender;
        this.receiver = receiver;
        this.messageType = messageType;
    }

    public Object getBody() {
        return body;
    }

    public void setBody(Object body) {
        this.body = body;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public void setReceiver(User receiver) {
        this.receiver = receiver;
    }

    public MessageType getMessageType() {
        return messageType;
    }

    public void setMessageType(MessageType messageType) {
        this.messageType = messageType;
    }

    public static class Builder implements Serializable {

        private Object body;
        private User sender;
        private User receiver;
        private MessageType messageType;

        public Builder() {}
        
        public Builder body(Object body) {
            this.body = body;
            return this;
        }
        
        public Builder sender(User sender) {
            this.sender = sender;
            return this;
        }
        
        public Builder receiver(User receiver) {
            this.receiver = receiver;
            return this;
        }
        
        public Builder messageType(MessageType messageType) {
            this.messageType = messageType;
            return this;
        }
        
        public Message build() {
            return new Message(this.body, this.sender, this.receiver, this.messageType);
        }
    }
}
