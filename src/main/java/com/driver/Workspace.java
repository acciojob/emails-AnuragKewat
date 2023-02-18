package com.driver;

import org.apache.commons.lang3.tuple.Pair;

import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;

public class Workspace extends Gmail{

    private ArrayList<Meeting> calendar; // Stores all the meetings

    public Workspace(String emailId) {
        // The inboxCapacity is equal to the maximum value an integer can store.
        super(emailId, Integer.MAX_VALUE);
        calendar = new ArrayList<>();
    }

    public void addMeeting(Meeting meeting){
        //add the meeting to calendar
        if(calendar.size()==0) {
            calendar.add(meeting);
            return;
        }

        int size = calendar.size();
        for(int i=0;i<size;i++) {
            if(!isValid(calendar.get(i), meeting)) return;
        }

        calendar.add(meeting);
    }

    public int findMaxMeetings(){
        // find the maximum number of meetings you can attend
        // 1. At a particular time, you can be present in at most one meeting
        // 2. If you want to attend a meeting, you must join it at its start time and leave at end time.
        // Example: If a meeting ends at 10:00 am, you cannot attend another meeting starting at 10:00 am
        return calendar.size();
    }

    private boolean isValid(Meeting meet, Meeting meeting) {
        if(meeting.getStartTime().compareTo(meet.getStartTime())==1 && meeting.getStartTime().compareTo(meet.getEndTime())==-1) return false;
        if(meeting.getEndTime().compareTo(meet.getStartTime())==1 && meeting.getEndTime().compareTo(meet.getEndTime())==-1) return false;
        if(meeting.getStartTime().compareTo(meet.getStartTime())==-1 && meeting.getEndTime().compareTo(meet.getEndTime())==1) return false;

        return true;
    }
}
