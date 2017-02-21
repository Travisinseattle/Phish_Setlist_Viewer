package group7.tcss450.uw.edu.challengeapp;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Travis Holloway on 2/18/2017.
 * The Response Class.
 */

public class Response implements java.io.Serializable {


    private Integer count;
    private List<Datum> data = null;
    private final static long serialVersionUID = 458022990559547397L;

    /**
     * No args constructor for use in serialization
     */
    private Response() {
    }

    /**
     * @param count The count returned from the JSON Object.
     * @param data The list of Datum objects returned from the JSON object.
     */
    private Response(Integer count, List<Datum> data) {
        super();
        this.count = count;
        this.data = data;
    }

    public static Response createResponse(Integer count, JSONArray jsonArray) throws JSONException {

        List<Datum> list = new ArrayList<Datum>();
        Response response = new Response();

        try {
            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject temp = jsonArray.getJSONObject(i);
                list.add(Datum.createDatum(temp));
            }
        } catch (Exception e) {
            Log.e("Create Temp Datum", e.getMessage());
        }

        try {
            response = new Response(count, list);
        } catch (Exception e) {
            Log.e("Create Response Failed", e.getMessage());
        }

        return response;
    }

    public Integer getCount() {
        return count;
    }

    public List<Datum> getData() {
        return data;
    }

    @Override
    public String toString() {
        return super.toString() + " Count: " + count + ", Data: " + data + ".";
    }
}
