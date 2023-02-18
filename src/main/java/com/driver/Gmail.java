package com.driver;

import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;

public class Gmail extends Email {
    class Pair {
        Date date;
        String sender;
        String message;
        Pair(Date date, String sender, String message) {
            this.date = date;
            this.sender = sender;
            this.message = message;
        }
    }

    int inboxCapacity; //maximum number of mails inbox can store
    LinkedList<Pair> inbox;
    //Inbox: Stores mails. Each mail has date (Date), sender (String), message (String). It is guaranteed that message is distinct for all mails.
    LinkedList<Pair> trash;
    //Trash: Stores mails. Each mail has date (Date), sender (String), message (String)
    public Gmail(String emailId, int inboxCapacity) {
        super(emailId);
        this.inboxCapacity = inboxCapacity;
        inbox = new LinkedList<Pair>();
        trash = new LinkedList<Pair>();
    }

    public void receiveMail(Date date, String sender, String message){
        // If the inbox is full, move the oldest mail in the inbox to trash and add the new mail to inbox.
        // It is guaranteed that:
        // 1. Each mail in the inbox is distinct.
        // 2. The mails are received in non-decreasing order. This means that the date of a new mail is greater than equal to the dates of mails received already.
        if(inbox.size()<=inboxCapacity) {
            inbox.addLast(new Pair(date, sender, message));
            return;
        }
        trash.addLast(new Pair(date, sender, message));
    }

    public void deleteMail(String message){
        // Each message is distinct
        // If the given message is found in any mail in the inbox, move the mail to trash, else do nothing
        for(int i=0;i<inbox.size();i++) {
            if(message.equals(inbox.get(i).message)) {
                Pair pair = inbox.remove(i);
                trash.addLast(pair);
                return;
            }
        }
    }

    public String findLatestMessage(){
        // If the inbox is empty, return null
        // Else, return the message of the latest mail present in the inbox
        if(inbox.size()==0) return null;

        int size = inbox.size();

        return inbox.get(size-1).message;
    }

    public String findOldestMessage() {
        // If the inbox is empty, return null
        // Else, return the message of the oldest mail present in the inbox
        if(inbox.size()==0) return null;

        return inbox.get(0).message;
    }

    public int findMailsBetweenDates(Date start, Date end){
        //find number of mails in the inbox which are received between given dates
        //It is guaranteed that start date <= end date
        int startIndex = 0;
        int endIndex = 0;
        for(int i=0;i<inbox.size();i++) {
            if(start.equals(inbox.get(i).date)) {
                startIndex = i;
            }
            if(end.equals(inbox.get(i).date)) {
                endIndex = i;
            }
        }

        return endIndex-startIndex+1;
    }

    public int getInboxSize(){
        // Return number of mails in inbox
        return inbox.size();
    }

    public int getTrashSize(){
        // Return number of mails in Trash
        return trash.size();
    }

    public void emptyTrash(){
        // clear all mails in the trash
        trash.clear();
    }

    public int getInboxCapacity() {
        // Return the maximum number of mails that can be stored in the inbox
        return inboxCapacity;
    }
}
