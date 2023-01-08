/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import Model.Message;
import java.util.ArrayList;

/**
 *
 * @author mega
 */
public interface MessageInterface {
    
    public void addMsg(Message m);
    public ArrayList<Message> showMsg();
    public ArrayList<Message> showMsgUser(String ch);
    public ArrayList<Message> showMsgAdmin(String ch);
    public void deleteM(int id);
    public void deleteM();
    
}
