/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.chat.tcpcommons;

import com.chat.tcpcommons.logging.IChatLogger;
import com.chat.tcpcommons.logging.LoggerFactory;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.net.Socket;

/**
 *
 * @author felix
 */
public class ClientThread extends Observable implements Runnable, Serializable {

    private transient Socket clientSocket;
    private transient ObjectInputStream in;
    private transient ObjectOutputStream out;
    private transient IChatLogger logger = LoggerFactory.getLogger(getClass());

    private User user;

    private boolean connected;

    public ClientThread(Socket clientSocket) {
        this.clientSocket = clientSocket;
        this.connected = true;
        init();
    }
    
    public ClientThread(String host, int port) {
        try {
            connected = true;
            clientSocket = new Socket(host, port);
            init();
        } catch (Exception ex) {
            logger.error(String.format("Error al conectarse al servidor: %s", ex.getMessage()));
        }
    }
    
    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public void notifyObservers(Object obj) {
        observers.forEach(o -> o.onUpdate(obj));
    }

    public void sendMessage(Message message) {
        try {
            out.writeObject(message);
            out.flush();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    public void disconnect() {
        try {
            sendMessage(new Message.Builder().sender(user).messageType(MessageType.DISCONNECT).build());
            connected = false;
            in.close();
            out.close();
            clientSocket.close();
        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
    
    public User getUser() {
        return user;
    }

    @Override
    public void run() {
        try {
            proccessMessage();
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    private void init() {
        try {
            out = new ObjectOutputStream(clientSocket.getOutputStream());
            in = new ObjectInputStream(clientSocket.getInputStream());
        } catch (Exception ex) {
            ex.printStackTrace();
            logger.error(ex.getMessage());
        }
    }

    private void proccessMessage() throws Exception {
        while (connected) {
            Message message = (Message) in.readObject();
            if (message.getMessageType() == MessageType.CONNECTION_MESSAGE) {
                user = message.getSender();
                message.setMessageType(MessageType.USERS_UPDATE);
            }
            if (message.getMessageType() == MessageType.DISCONNECT) {
                connected = false;
                disconnect();
            }
            notifyObservers(message);
        }
    }
}
