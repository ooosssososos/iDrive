package Parser;

import java.util.ArrayList;
import java.util.List;


public class BarUser {

    private int id;
    private List<Integer> currentPartyId;
    private List<Integer> promotionsId;
    private String description;
    private double lat;
    private double lon;

    public BarUser(int id, double lat, double lon, String description) {
        this.id = id;
        promotionsId = new ArrayList<Integer>();
        currentPartyId = new ArrayList<Integer>();
        this.description = description;
        this.lat = lat;
        this.lon = lon;
    }

    public void addPromotions(Integer i) {
        this.promotionsId.add(i);
    }


    //TODO: implement remove methods later

    public Integer getPromotion(int i) {
        return this.promotionsId.get(i);
    }

    public void addCurrentParty(Integer i) {
        this.currentPartyId.add(i);
    }


    //TODO: implement remove methods later

    public Integer getCurrentParty(int i) {
        return this.currentPartyId.get(i);
    }


    public int getId() {
        return this.id;
    }

    public double getLat() {
        return this.lat;
    }

    public double getLon() {
        return this.lon;
    }

    public String getDescription() {
        return this.description;
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
        BarUser other = (BarUser) obj;
        if (id != other.id)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "BarUser [id=" + id + ", currentPartyId=" + currentPartyId
                + ", promotionsId=" + promotionsId + ", description="
                + description + ", lat=" + lat + ", lon=" + lon + "]";
    }



}
