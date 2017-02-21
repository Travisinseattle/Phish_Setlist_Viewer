package model;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import group7.tcss450.uw.edu.challengeapp.Response;

/**
 * Created by Travis Holloway on 2/14/2017.
 * This class represents setlists, its immutable, and serializable.
 */

public class Setlist implements java.io.Serializable {

    private Integer errorCode;
    private Object errorMessage;
    private Response response;
    private final static long serialVersionUID = -3798034157829962186L;

    /**
     * No args constructor for use in serialization
     *
     */
    private Setlist() {
    }

    /**
     * Private setlist constructor.
     *
     * @param response The response object obtained from the JSON.
     * @param errorMessage The Error Message object obtained from the JSON
     * @param errorCode The Error Code object obtained from the JSON.
     */
    private Setlist(Integer errorCode, Object errorMessage, Response response) {
        super();
        this.errorCode = errorCode;
        this.errorMessage = errorMessage;
        this.response = response;
    }

    public static Setlist createSetlist (JSONObject jsonObject) throws JSONException {

        JSONArray jsonArray = jsonObject.getJSONObject("response").getJSONArray("data");
        Setlist setlist = new Setlist();

        try {
            setlist = new Setlist(jsonObject.getInt("error_code"),
                    jsonObject.get("error_message"),
                    Response.createResponse(jsonObject.getJSONObject("response")
                            .getInt("count"), jsonArray));
        } catch (Exception e) {
            Log.e("Create Setlist Failed", e.getMessage());
        }

        return setlist;
    }

    public Integer getErrorCode() {
        return errorCode;
    }

    public Object getErrorMessage() {
        return errorMessage;
    }

    public Response getResponse() {
        return response;
    }

    @Override
    public String toString() {
        return super.toString() + "Error code: " + errorCode + ", Error Message: " + errorMessage
                + ", Response: " + response + ".";
    }

}
