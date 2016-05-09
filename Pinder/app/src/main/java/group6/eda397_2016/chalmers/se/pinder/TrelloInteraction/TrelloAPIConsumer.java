package group6.eda397_2016.chalmers.se.pinder.TrelloInteraction;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;

import group6.eda397_2016.chalmers.se.pinder.PinderApplication;
import group6.eda397_2016.chalmers.se.pinder.dao.Database;
import group6.eda397_2016.chalmers.se.pinder.model.Profile;
import group6.eda397_2016.chalmers.se.pinder.model.Task;

/**
 * Created by Kyr on 4/27/2016.
 */
public class TrelloAPIConsumer {
    private final static String trelloAPIUrl = "https://api.trello.com/1/";
    private final static String appKeyandToken = "&key=" + TrelloAuth.appKey + "&token=";
    protected static JSONObject result;
    //Tags to be used for JSON parsing
    private final static String TYPE_USER = "user";
    private final static String TYPE_TEAM = "agilesoftwaredevgroup6";
    private final static String BACKLOGID = "56f3e9b47ebaa6890e8574f3";
    private final static String TAG_ID = "id";
    private final static String TAG_NAME = "name";
    private final static String TAG_FULLNAME = "fullName";
    private final static String TAG_MEMBERS = "members";
    private final static String TAG_LISTS = "lists";
    private final static String TAG_SKILLS = "bio";
    private final static String TAG_TASKS = "cards";

    public static void fetchUserProfile(String username, Context context) {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("authtoken", "empty");
        String getUserProfile = trelloAPIUrl + "members/" + username + "?fields=fullName,bio" + appKeyandToken + authToken;
        makeJSONRequest(getUserProfile,TYPE_USER,  context);
    }

    public static void fetchTeamMembers(Context context) {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("authtoken", "empty");
        String getTeamMembersURL = trelloAPIUrl + "organizations/" + TYPE_TEAM + "/members?fields=fullName,bio" + appKeyandToken + authToken;
        makeJSONArrayRequest(getTeamMembersURL,TAG_MEMBERS, context);

        //this will return ids and names
    }

    public static void fetchBackLogTasks(Context context) {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("authtoken", "empty");
        String getListTasks = trelloAPIUrl + "lists/" + BACKLOGID + "?fields=name&cards=open&card_fields=name,desc" + appKeyandToken + authToken;
        makeJSONRequest(getListTasks,TAG_TASKS, context);
        //this will return ids and names
    }


    public static void makeJSONRequest(final String url, final String mainElementTag, final Context context) {
        // Making a request to url and getting response
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET, url, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {
                parseJSONObject(response,mainElementTag,context);
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error on makeJSONRequest: ","url: "+url);
            }
        });

// Adding request to request queue
        VolleyManager.getInstance(context).addToRequestQueue(jsonObjReq);
    }


    public static void makeJSONArrayRequest(final String url, final String mainElementTag, final Context context)
    {
        JsonArrayRequest jsonArrayReq = new JsonArrayRequest(url, new Response.Listener<JSONArray>()

        {
            @Override
            public void onResponse(JSONArray response)
            {
                parseJSONArray(response, mainElementTag,context);
            }

            }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.e("Error on makeJSONArrayRequest: ","url: "+url);
        }
        });

        // Adding request to request queue
        VolleyManager.getInstance(context).addToRequestQueue(jsonArrayReq);
    }
    public static void parseJSONObject(JSONObject data, String mainElementTag, Context context) {
        PinderApplication app = (PinderApplication) context.getApplicationContext();
        Database db =app.getDatabase();
        try
        {
            if (data == null) {
                Log.e("Null JSON response", "Null response for main element "+mainElementTag);
            }
            else
                {
                    if (mainElementTag.equals(TYPE_USER))
                    {
                        String id = data.getString(TAG_ID);
                        String name = data.getString(TAG_FULLNAME);
                        String bio = data.getString("bio");
                        Profile profile = new Profile(id,name, bio);
                        db.setCurrentUser(profile);
                        //add it somewhere

                    }
                    if (mainElementTag.equals(TAG_TASKS)) {
                        JSONArray array = data.getJSONArray(TAG_TASKS);
                        for (int i = 0; i < array.length(); i++) {
                            JSONObject card = (JSONObject) array.get(i);
                            String id = card.getString(TAG_ID);
                            String nameAndPoints = card.getString(TAG_NAME);
                            String descAndSkills = card.getString("desc");
                            Task task = new Task(id, nameAndPoints, descAndSkills);
                            db.createTask(task);
                            //add it somewhere
                        }
                    }

                }

        }
        catch (JSONException e)
                    {
                     e.printStackTrace();
                        Log.e ("Parse JSONObject Error:", e.toString());
                    }

    }

    public static void parseJSONArray(JSONArray data, String mainElementTag, Context context)
    {
        PinderApplication app = (PinderApplication) context.getApplicationContext();
        Database db =app.getDatabase();
        try
    {
        if (data == null) {
            Log.e("Null JSONArray response", "Null response for main element "+mainElementTag);
        }
        else
        {
            if (mainElementTag.equals(TAG_MEMBERS)) {
                for (int i = 0; i < data.length(); i++) {
                    JSONObject member = (JSONObject) data.get(i);
                    String id = member.getString(TAG_ID);
                    String name = member.getString(TAG_FULLNAME);
                    String bio = member.getString("bio");
                    Profile profile = new Profile(id,name, bio);
                    db.createProfile(profile);
                    //add it somewhere
                }
            }

        }

    }
    catch (JSONException e)
    {
        e.printStackTrace();
    }

}



    ///////////////////////////////////////Not required code at this point///////////////////////////////////////////////
    /*

    public static ArrayList<HashMap<String, String>> fetchBoards(String teamName, Context context) {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("authtoken", "empty");
        String getTeamBoardsURL = trelloAPIUrl + "organizations/" + teamName + "/boards?fields=name" + appKeyandToken + authToken;
        Log.v("Query String to Trello:", getTeamBoardsURL);
        makeJSONRequest(getTeamBoardsURL, context);
        return parseJSONObject(result, TAG_DEFAULT, TAG_ID, TAG_NAME);
        //this will return ids and names
    }

    public static void fetchBoardLists(String boardID, Context context) {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("authtoken", "empty");
        String getBoardListsURL = trelloAPIUrl + "boards/" + boardID + "?lists=open&fields=name&list_fields=name" + appKeyandToken + authToken;
        Log.v("Query String to Trello:", getBoardListsURL);
        makeJSONRequest(getBoardListsURL, context);

        //this will return ids and names
    }

    public static ArrayList<HashMap<String, String>> fetchTeams(String username, Context context) {
        SharedPreferences sharedPref = context.getApplicationContext().getSharedPreferences("authorizeprefs", Context.MODE_PRIVATE);
        String authToken = sharedPref.getString("authtoken", "empty");
        String getTeamsURL = trelloAPIUrl + "members/" + username + "/organizations?fields=name" + appKeyandToken + authToken;
        Log.v("Query String to Trello:", getTeamsURL);
        makeJSONRequest(getTeamsURL, context);
        return parseJSONObject(result, TAG_DEFAULT, TAG_ID, TAG_NAME);
        //this will return ids and names
    }

*/
}
