package group6.eda397_2016.chalmers.se.pinder.TrelloInteraction;

import android.util.Log;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Kyr on 4/27/2016.
 */
public class TrelloAPIConsumer {
    private final static String trelloAPIUrl = "https://api.trello.com/1/";
    private static String authToken = null;
    private final static String keyAndToken = "&key="+TrelloAuth.appKey+"&token="+authToken;
    //Tags to be used for JSON parsing
    private final static String TAG_DEFAULT = "";
    private final static String TAG_ID = "id";
    private final static String TAG_NAME = "name";
    private final static String TAG_FULLNAME = "fullName";
    private final static String TAG_LISTS = "lists";
    private final static String TAG_SKILLS = "bio";
    private final static String TAG_TASKS = "cards";

    //just naming possible functions
    public static ArrayList<HashMap<String, String>> fetschUserSkillFromBio(String username){
        String getUserSkills = trelloAPIUrl+"members/"+username+"?fields=fullName,bio"+keyAndToken;
        return parseJSON(getUserSkills,TAG_DEFAULT,TAG_ID,TAG_SKILLS);
    }
    public static ArrayList<HashMap<String, String>> fetchTeams(String username){
        String getTeamsURL = trelloAPIUrl+username+"/organizations?fields=name"+keyAndToken;
        return parseJSON(getTeamsURL,TAG_DEFAULT,TAG_ID,TAG_NAME);
        //this will return ids and names
    }
    public static ArrayList<HashMap<String, String>> fetchTeamMembers(String teamName){
        String getTeamMembersURL = trelloAPIUrl+"organizations/"+teamName+"/members?fields=fullName"+keyAndToken;
        return parseJSON(getTeamMembersURL,TAG_DEFAULT,TAG_ID,TAG_FULLNAME);
        //this will return ids and names
    }
    public static ArrayList<HashMap<String, String>> fetchBoards(String teamName){
        String getTeamBoardsURL = trelloAPIUrl+"organizations/"+teamName+"/boards?fields=name"+keyAndToken;
        return parseJSON(getTeamBoardsURL,TAG_DEFAULT,TAG_ID,TAG_NAME);
        //this will return ids and names
    }

    public static ArrayList<HashMap<String, String>> fetchBoardLists(String boardID){
        String getBoardListsURL= trelloAPIUrl+"boards/"+boardID+"?lists=open&fields=name&list_fields=name"+keyAndToken;
        return parseJSON(getBoardListsURL,TAG_LISTS, TAG_ID,TAG_NAME);
        //this will return ids and names
    }
    public static ArrayList<HashMap<String, String>> fetchListTasks(String listID){
        String getListTasks = trelloAPIUrl+"lists/"+listID+"?fields=name&cards=open&card_fields=name"+keyAndToken;
        return parseJSON(getListTasks, TAG_TASKS, TAG_ID,TAG_NAME);
        //this will return ids and names
    }

    public static ArrayList<HashMap<String, String>> fetchTaskRequiredSkills(String TaskID){
        //to do. get skills for tasks. need to discuss this first.
        return null;
    }

    public static void setAuthToken(String token)
    {
        authToken = token;
    }

    public static ArrayList<HashMap<String, String>> parseJSON(String url, String mainElementTag, String firstAttributeTag, String secondAttributeTag) {
        // result JSONArray
        JSONArray result = null;
        // Hashmap for ListView
        ArrayList<HashMap<String, String>> resultList = new ArrayList<HashMap<String,String>>();


        // Creating service handler class instance
        ServiceHandler sh = new ServiceHandler();

        // Making a request to url and getting response
        String jsonStr = sh.makeServiceCall(url, ServiceHandler.GET);

        Log.d("Response: ", "> " + jsonStr);

        if (jsonStr != null) {
            try {
                JSONObject jsonObj = new JSONObject(jsonStr);

                // Getting JSON Array node
                result = jsonObj.getJSONArray(mainElementTag);

                // looping through All Contacts
                for (int i = 0; i < result.length(); i++) {
                    JSONObject c = result.getJSONObject(i);

                    if (secondAttributeTag.equals(TAG_SKILLS))
                    {
                        String bio = c.getString(TAG_SKILLS);
                        String[] array = bio.split(",");
                        for (int j=0;j<array.length;j++ )
                        {
                            HashMap<String, String> temp = new HashMap<String, String>();
                            temp.put("ID", Integer.toString(j));
                            temp.put("Skill", array[j]);
                            resultList.add(temp);
                        }
                    }
                    else {
                        String id = c.getString(firstAttributeTag);
                        String name = c.getString(secondAttributeTag);


                        // tmp hashmap for single contact
                        HashMap<String, String> temp = new HashMap<String, String>();

                        // adding each child node to HashMap key => value
                        temp.put(firstAttributeTag, id);
                        temp.put(secondAttributeTag, name);

                        // adding contact to contact list
                        resultList.add(temp);
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else {
            Log.e("ServiceHandler", "Couldn't get any data from the url");
        }

        return resultList;
    }

}
