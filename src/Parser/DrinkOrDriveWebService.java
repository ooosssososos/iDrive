package Parser;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;



public class DrinkOrDriveWebService {

    private final static String DRINK_OR_DRIVE_WEB_SERVICE = "http://idrivedjango-env-qrs5vkxvvi.elasticbeanstalk.com/api/";

    /**
     * Constructor
     */
    List<BarUser> listOfBarUsers;
    List<PartyUser> listOfPartyUsers;
    List<Party> listOfParties;
    List<Promotion> listOfPromotions;



    public DrinkOrDriveWebService() {
        listOfBarUsers = new ArrayList<BarUser>();
        listOfPartyUsers = new ArrayList<PartyUser>();
        listOfParties = new ArrayList<Party>();
        listOfPromotions = new ArrayList<Promotion>();

    }

    public void parseBarUsers() {
        // CPSC 210 Students: You will need to complete this method
        // builds the URL to initialize session in the Waldo API
        StringBuilder urlBuilder = new StringBuilder(DRINK_OR_DRIVE_WEB_SERVICE);
        urlBuilder.append("/baruser/");
        String input = makeJSONQuery(urlBuilder);
        JSONArray obj;
        try {
            // parses the name, location, lat, lon, and timestamp of each Waldo generated
            obj = (JSONArray) (new JSONTokener(input).nextValue());
            System.out.println(obj.toString());
            if (obj.length() != 0) {
                // For all Waldos generated
                for (int i = 0, var = obj.length(); i<var; i++) {
                    System.out.println("sdfgh");

                    JSONObject barUser = obj.getJSONObject(i);
                    System.out.println(obj.length());
                    int barId = barUser.getInt("id");
                    System.out.println(barId);
                    double lat = Double.parseDouble(barUser.getString("geo_lat"));
                    double lon = Double.parseDouble(barUser.getString("geo_long"));
                    String description = barUser.getString("description");
                    BarUser b = new BarUser(barId, lat, lon, description);
                    JSONArray promotions = barUser.getJSONArray("promotions");
                    if (promotions.length()!=0) {
                        // For all Waldos generated
                        for (int k = 0; k < promotions.length(); k++) {
                            Integer m = promotions.getInt(k);
                            b.addPromotions(m);
                            // Finds and adds all messages parsed
                        }
                    }
                    JSONArray currentParties = barUser.getJSONArray("cur_parties");
                    if (currentParties.length()!=0) {
                        // For all Waldos generated
                        for (int s = 0; s < currentParties.length(); s++) {
                            Integer m = Integer.parseInt(currentParties.getString(s));
                            b.addCurrentParty(m);
                            // Finds and adds all messages parsed
                        }
                        // Add new Waldos to the list
                    }
                    this.listOfBarUsers.add(b);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parsePartyUsers() {
        // CPSC 210 Students: You will need to complete this method
        // builds the URL to initialize session in the Waldo API
        StringBuilder urlBuilder = new StringBuilder(DRINK_OR_DRIVE_WEB_SERVICE);
        urlBuilder.append("/partyuser/");
        String input = makeJSONQuery(urlBuilder);
        JSONArray obj;
        try {
            // parses the name, location, lat, lon, and timestamp of each Waldo generated
            obj = (JSONArray) (new JSONTokener(input).nextValue());
            System.out.println(obj.toString());
            if (obj.length() != 0) {
                // For all Waldos generated
                for (int i = 0, var = obj.length(); i<var; i++) {

                    JSONObject partyUser = obj.getJSONObject(i);
                    int userId = partyUser.getInt("id");
                    String name = partyUser.getString("name");

                    PartyUser p;
                    if(partyUser.get("cur_party").equals(null)) {
                        p = new PartyUser(userId, -1, name);
                    } else {
                        p = new PartyUser(userId, partyUser.getInt("cur_party"), name);
                    }


                    JSONArray pastParties = partyUser.getJSONArray("past_parties");
                    if (pastParties.length()!=0) {
                        // For all Waldos generated
                        for (int s = 0; s < pastParties.length(); s++) {
                            Integer m = pastParties.getInt(s);
                            p.addPast(m);
                            // Finds and adds all messages parsed
                        }
                        // Add new Waldos to the list
                    }
                    this.listOfPartyUsers.add(p);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseParty() {
        // CPSC 210 Students: You will need to complete this method
        // builds the URL to initialize session in the Waldo API
        StringBuilder urlBuilder = new StringBuilder(DRINK_OR_DRIVE_WEB_SERVICE);
        urlBuilder.append("/party/");
        String input = makeJSONQuery(urlBuilder);
        JSONArray obj;
        try {
            // parses the name, location, lat, lon, and timestamp of each Waldo generated
            obj = (JSONArray) (new JSONTokener(input).nextValue());
            System.out.println(obj.toString());
            if (obj.length() != 0) {
                // For all Waldos generated
                for (int i = 0, var = obj.length(); i<var; i++) {

                    JSONObject party = obj.getJSONObject(i);
                    int partyId = party.getInt("id");

                    int barId = party.getInt("cur_checkin_bar");

                    boolean isClosed = party.getBoolean("closed");
                    int code = party.getInt("code");

                    Party p = new Party(partyId, barId, isClosed, code);

                    JSONArray activeMembers = party.getJSONArray("active_members");
                    if (activeMembers.length()!=0) {
                        for (int s = 0; s < activeMembers.length(); s++) {
                            Integer m = activeMembers.getInt(s);
                            p.addPartyUser(m);
                        }
                    }
                    JSONArray designatedDrivers = party.getJSONArray("des_drivers");
                    if (designatedDrivers.length()!=0) {
                        for (int s = 0; s < designatedDrivers.length(); s++) {
                            Integer m = designatedDrivers.getInt(s);
                            p.addDesignatedDriver(m);
                        }
                    }

                    JSONArray pastMembers = party.getJSONArray("past_members");
                    if (pastMembers.length()!=0) {
                        for (int s = 0; s < pastMembers.length(); s++) {
                            Integer m = pastMembers.getInt(s);
                            p.addPastMembers(m);
                        }
                    }
                    this.listOfParties.add(p);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public void parsePromotion() {
        // CPSC 210 Students: You will need to complete this method
        // builds the URL to initialize session in the Waldo API
        StringBuilder urlBuilder = new StringBuilder(DRINK_OR_DRIVE_WEB_SERVICE);
        urlBuilder.append("/promotion/");
        String input = makeJSONQuery(urlBuilder);
        JSONArray obj;
        try {
            // parses the name, location, lat, lon, and timestamp of each Waldo generated
            obj = (JSONArray) (new JSONTokener(input).nextValue());
            System.out.println(obj.toString());
            if (obj.length() != 0) {
                // For all Waldos generated
                for (int i = 0, var = obj.length(); i<var; i++) {

                    JSONObject promo = obj.getJSONObject(i);
                    int promoId = promo.getInt("id");

                    int barId = promo.getInt("bar");

                    String description = promo.getString("description");

                    int max = promo.getInt("max_amount");

                    int min = promo.getInt("min_amount");

                    Promotion p = new Promotion(promoId, barId, description, max, min);


                    this.listOfPromotions.add(p);
                }
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }


    public List<PartyUser> getPartyUsers() {
        return this.listOfPartyUsers;
    }

    public List<BarUser> getBarUsers() {
        return this.listOfBarUsers;
    }

    public List<Party> getParties() {
        return this.listOfParties;
    }

    public List<Promotion> getPromos() {
        return this.listOfPromotions;
    }
    /**
     * Return the current list of Waldos that have been retrieved
     *
     * @return The current Waldos
     */


    /**
     * Retrieve messages available for the user from the Waldo web service
     *
     * @return A list of messages
     */


    /**
     * Execute a given query
     *
     * @param urlBuilder The query with everything but http:
     * @return The JSON returned from the query
     */
    private static String makeJSONQuery(StringBuilder urlBuilder) {
        try {
            URL url = new URL(urlBuilder.toString());
            HttpURLConnection client = (HttpURLConnection) url.openConnection();
            client.setRequestProperty("accept", "application/json");
            InputStream in = client.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(in));
            String returnString = br.readLine();
            client.disconnect();
            return returnString;
        } catch (Exception e) {
            e.printStackTrace();
            throw new WebServiceException("Unable to make JSON query: " + urlBuilder.toString());
        }
    }

}
