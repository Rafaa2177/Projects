package org.example.tcp;

import java.util.ArrayList;
import java.util.List;

public class ClientNotifications {
    
    private List<String> candidateNotifications;

    public ClientNotifications(){
        this.candidateNotifications = new ArrayList<>();
    }

    public void addNotification(String notification){
        this.candidateNotifications.add(notification);
    }

    public int numberOfNots(){
        return this.candidateNotifications.size();
    }

    public List<String> getNotifications(){
        return this.candidateNotifications;
    }
}
