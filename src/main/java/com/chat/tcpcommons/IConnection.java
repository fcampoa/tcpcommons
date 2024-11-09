/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.chat.tcpcommons;

import com.chat.tcpcommons.Message;
import com.chat.tcpcommons.User;

/**
 *
 * @author felix
 */
public interface IConnection {
    
    void sendMessage(Message message);
    void closeInbox(InboxChat inbox);
    void openInbox(User friend);
    void init();
    default void disconnect() {};
}
