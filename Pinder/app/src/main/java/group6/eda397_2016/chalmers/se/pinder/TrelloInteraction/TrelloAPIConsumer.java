package group6.eda397_2016.chalmers.se.pinder.TrelloInteraction;

/**
 * Created by Kyr on 4/27/2016.
 */
public class TrelloAPIConsumer {
    private final static String trelloAPIUrl = "https://api.trello.com/1/";
    private static String authToken = null;
    private final static String keyAndToken = "&key="+TrelloAuth.appKey+"&token="+authToken;
    //just naming possible functions
    public static void fetchTeams(String username){
        String getTeamsURL = trelloAPIUrl+username+"/organizations?fields=name";

    }
    public static void fetchTeamMembers(String teamName){
        String getTeamMembersURL = trelloAPIUrl+"organizations/"+teamName+"/members?fields=fullName"+keyAndToken;

    }
    public static void fetchBoards(String teamName){
        String getTeamBoardsURL = trelloAPIUrl+"organizations/"+teamName+"/boards?fields=Name"+keyAndToken;

    }

    public static void fetchBoardLists(String boardID){
        String getBoardListsURL= trelloAPIUrl+"boards/"+boardID+"?lists=open&fields=name&list_fields=name"+keyAndToken;

    }
    public static void fetchListTasks(String listID){
        String getListTasks = trelloAPIUrl+"lists/"+listID+"?fields=name&cards=open&card_fields=name"+keyAndToken;

    }

    public static void setAuthToken(String token)
    {
        authToken = token;
    }
}
