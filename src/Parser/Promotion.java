package Parser;


public class Promotion {

    private int id;
    private int barId;
    private String description;
    private int max;
    private int min;


    public Promotion(int id, int barId, String description, int max, int min) {
        this.id = id;
        this.barId = barId;
        this.description = description;
        this.max = max;
        this.min = min;
    }

    public int getId() {
        return this.id;
    }

    public int barId() {
        return this.barId;
    }

    public String getDescription() {
        return this.description;
    }

    public int max() {
        return max;
    }

    public int min() {
        return min;
    }

    /* (non-Javadoc)
     * @see java.lang.Object#hashCode()
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + id;
        return result;
    }

    @Override
    public String toString() {
        return "Promotion [id=" + id + ", barId=" + barId + ", description="
                + description + ", max=" + max + ", min=" + min + "]";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Promotion other = (Promotion) obj;
        if (id != other.id)
            return false;
        return true;
    }



}
