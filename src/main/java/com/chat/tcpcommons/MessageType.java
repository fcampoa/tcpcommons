/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons;

/**
 *
 * @author felix
 */
public enum MessageType {
    MESSAGE_INBOX(1),
    USERS_UPDATE(2),
    MESSAGE_GENERAL(3),
    CONNECTION_MESSAGE(4),
    DISCONNECT(5);
    
    private int type;
    
    private MessageType(int type) {
        this.type = type;
    }    
}
