package Parser;

import java.util.List;
import java.util.ArrayList;


public class Party {

    private int id;
    private int currentCheckingBar;
    private boolean closed;
    private List<Integer> listOfPartyUsersId;
    private List<Integer> pastMembersId = new ArrayList<Integer>();
    private List<Integer> listOfDesignatedDriver;
    private int code;

    public Party(int id, int currentCheckingBar, boolean closed, int code) {
        this.id = id;
        this.currentCheckingBar = currentCheckingBar;
        pastMembersId = new ArrayList<Integer>();
        listOfPartyUsersId = new ArrayList<Integer>();
        listOfDesignatedDriver = new ArrayList<Integer>();
        this.closed = closed;
        this.code = code;
    }


    public void addPartyUser(Integer i) {
        this.listOfPartyUsersId.add(i);
    }

    public void addPastMembers(Integer i) {
        this.pastMembersId.add(i);
    }

    @Override
    public String toString() {
        return "Party [id=" + id + ", currentCheckingBar=" + currentCheckingBar
                + ", closed=" + closed + ", listOfPartyUsersId="
                + listOfPartyUsersId + ", pastMembersId=" + pastMembersId
                + ", listOfDesignatedDriver=" + listOfDesignatedDriver
                + ", code=" + code + "]";
    }


    public void addDesignatedDriver(Integer i) {
        this.listOfDesignatedDriver.add(i);
    }

    //TODO: implement remove methods later

    public Integer getPartyUser(int i) {
        return this.listOfPartyUsersId.get(i);
    }

    public int getId() {
        return this.id;
    }

    public int getCurrentCheckingBar() {
        return this.currentCheckingBar;
    }

    public Integer getPastMembers(int i) {
        return this.pastMembersId.get(i);
    }

    public boolean getClosed() {
        return this.closed;
    }

    public Integer getDesignatedDriver(int i) {
        return this.listOfDesignatedDriver.get(i);
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Party other = (Party) obj;
        if (id != other.id)
            return false;
        return true;
    }

}
