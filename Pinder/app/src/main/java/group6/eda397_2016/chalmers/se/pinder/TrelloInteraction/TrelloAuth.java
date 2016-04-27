package group6.eda397_2016.chalmers.se.pinder.TrelloInteraction;

import android.content.Context;
import android.content.SharedPreferences;
import android.widget.Toast;

/**
 * Created by Kyr on 4/25/2016.
 */
public class TrelloAuth {
    public final static String appKey = "c007e8e064afd2378471b337d02b32a3";
    public final static String appName = "Pinder";
    public final static String expiration = "never";
    public final static String callbackMethod = "fragment";
    public final static String returnUrl = "ase://oauthresponse";
    public final static String trelloAuthorizeUrl = "https://trello.com/1/authorize?expiration="+expiration+"&name="+appName+"&key="+appKey+"&callback_method="+callbackMethod+"&return_url="+returnUrl;



}