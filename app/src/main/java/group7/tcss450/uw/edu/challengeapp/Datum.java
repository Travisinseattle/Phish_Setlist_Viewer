package group7.tcss450.uw.edu.challengeapp;

import android.util.Log;
import android.util.Pair;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Travis Holloway on 2/18/2017.
 * Datum Class
 */

class Datum implements java.io.Serializable {

    private Pair<String, String> showid;

    private Pair<String, String> showdate;

    private Pair<String, String> shortDate;

    private Pair<String, String> longDate;

    private Pair<String, String> relativeDate;

    private Pair<String, String> url;

    private Pair<String, String> gapchart;

    private Pair<String, String> artist;

    private Pair<String, String> artistid;

    private Pair<String, String> venueid;

    private Pair<String, String> venue;

    private Pair<String, String> location;

    private Pair<String, String> setlistdata;

    private Pair<String, String> setlistnotes;

    private Pair<String, String> rating;

    private List<Pair<String, String>> list;

    private final static long serialVersionUID = 2947053521094617551L;

    /**
     * No args constructor for use in serialization
     *
     */
    private Datum() {}

    /**
     *
     * @param relativeDate The Relative date of the datum.
     * @param showid The showid date of the datum.
     * @param gapchart  The gapchart of the datum.
     * @param location  The location of the datum.
     * @param longDate  The longdate of the datum.
     * @param setlistnotes The setlistnotes of the datum.
     * @param venue  The venue of the datum.
     * @param url  The url of the datum.
     * @param showdate  The showdate of the datum.
     * @param venueid  The venueid of the datum.
     * @param rating  The rating of the datum.
     * @param artist  The artist of the datum.
     * @param artistid  The artistid of the datum.
     * @param setlistdata  The setlistdata of the datum.
     * @param shortDate  The shortDate of the datum.
     */
    private Datum(Integer showid, String showdate, String shortDate, String longDate,
                 String relativeDate, String url, String gapchart, String artist,
                 Integer artistid, Integer venueid, String venue, String location,
                 String setlistdata, String setlistnotes, String rating) {
        super();
        this.showid = new Pair<String, String>("Show Id: ", String.valueOf(showid));
        this.showdate = new Pair<String, String>("Show Date: ", showdate);
        this.shortDate = new Pair<String, String>("Short Date: ", shortDate);
        this.longDate = new Pair<String, String>("Long Date: ", longDate);
        this.relativeDate = new Pair<String, String>("Time Since Show: ", relativeDate);
        this.url = new Pair<String, String>("Url: ", url);
        this.gapchart = new Pair<String, String>("Gap Chart: ", gapchart);
        this.artist = new Pair<String, String>("Artist: ", artist);
        this.artistid = new Pair<String, String>("Artist ID: ", String.valueOf(artistid));
        this.venueid = new Pair<String, String>("Venue ID: ", String.valueOf(venueid));
        this.venue = new Pair<String, String>("Veunue Name: ", venue);
        this.location = new Pair<String, String>("Location: ", location);
        this.setlistdata = new Pair<String, String>("Set List Data: ", setlistdata);
        this.setlistnotes = new Pair<String, String>("Set List Notes: ", setlistnotes);
        this.rating = new Pair<String, String>("Rating: ", rating);
        this.list = new ArrayList<Pair<String, String>>();

        try {
            list.add(this.showid);
            list.add(this.showdate);
            list.add(this.shortDate);
            list.add(this.longDate);
            list.add(this.relativeDate);
            list.add(this.url);
            list.add(this.gapchart);
            list.add(this.artist);
            list.add(this.artistid);
            list.add(this.venueid);
            list.add(this.venue);
            list.add(this.location);
            list.add(this.setlistdata);
            list.add(this.setlistnotes);
            list.add(this.rating);
        } catch (Exception e) {
            Log.e("List creation failed", e.getMessage());
        }
    }

    static Datum createDatum (JSONObject jsonObject) throws JSONException {

        Datum datum = new Datum();

        try {
            datum = new Datum(jsonObject.getInt("showid"),
                    jsonObject.getString("showdate"),
                    jsonObject.getString("short_date"),
                    jsonObject.getString("long_date"),
                    jsonObject.getString("relative_date"),
                    jsonObject.getString("url"),
                    jsonObject.getString("gapchart"),
                    jsonObject.getString("artist"),
                    jsonObject.getInt("artistid"),
                    jsonObject.getInt("venueid"),
                    jsonObject.getString("venue"),
                    jsonObject.getString("location"),
                    jsonObject.getString("setlistdata"),
                    jsonObject.getString("setlistnotes"),
                    jsonObject.getString("rating"));
        } catch (Exception e) {
            Log.e("Create Datum Failed", e.getMessage());
        }


      return datum;
    }

    List<Pair<String, String>> getList() {
        return list;
    }

    public Pair<String, String> getShowid() {
        return showid;
    }

    public Pair<String, String> getShowdate() {
        return showdate;
    }

    public Pair<String, String> getShortDate() {
        return shortDate;
    }

    public Pair<String, String> getLongDate() {
        return longDate;
    }

    public Pair<String, String> getRelativeDate() {
        return relativeDate;
    }

    public Pair<String, String> getUrl() {
        return url;
    }

    public Pair<String, String> getGapchart() {
        return gapchart;
    }

    public Pair<String, String> getArtist() {
        return artist;
    }

    public Pair<String, String> getArtistid() {
        return artistid;
    }

    public Pair<String, String> getVenueid() {
        return venueid;
    }

    public Pair<String, String> getVenue() {
        return venue;
    }

    public Pair<String, String> getLocation() {
        return location;
    }

    public Pair<String, String> getSetlistdata() {
        return setlistdata;
    }

    public Pair<String, String> getSetlistnotes() {
        return setlistnotes;
    }

    public Pair<String, String> getRating() {
        return rating;
    }

    @Override
    public String toString() {
        return super.toString() + "SetlistID: " + showid + ", Show Date: " + showdate + ".";
    }
}
