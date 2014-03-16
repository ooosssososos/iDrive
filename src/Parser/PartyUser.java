package Parser;

import java.util.ArrayList;
import java.util.List;


public class PartyUser {

    private int id;
    private int currentPartyId;
    private List<Integer> pastPartiesId = new ArrayList<Integer>();
    private List<Integer> friendsId = new ArrayList<Integer>();
    private String name;

    public PartyUser(int id, int currentPartyId, String name) {
        this.id = id;
        this.currentPartyId = currentPartyId;
        this.name = name;
    }

    public void addPast(Integer i) {
        this.pastPartiesId.add(i);
    }

    public void addFriend(Integer i) {
        this.friendsId.add(i);
    }

    //TODO: implement remove methods later

    public Integer getPastParties(int i) {
        return this.pastPartiesId.get(i);
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
        PartyUser other = (PartyUser) obj;
        if (id != other.id)
            return false;
        return true;
    }

    public int getId() {
        return this.id;
    }

    public int getCurrentParty() {
        return this.currentPartyId;
    }

    public Integer getFriends(int i) {
        return this.friendsId.get(i);
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return "PartyUser [id=" + id + ", currentPartyId=" + currentPartyId
                + ", pastPartiesId=" + pastPartiesId + ", friendsId="
                + friendsId + ", name=" + name + "]";
    }

}
