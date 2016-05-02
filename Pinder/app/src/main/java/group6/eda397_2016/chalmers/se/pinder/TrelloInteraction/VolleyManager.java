package group6.eda397_2016.chalmers.se.pinder.TrelloInteraction;
import android.app.Application;
import android.content.Context;
import android.text.TextUtils;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Singleton providing a volley requestqueue for activities.
 */
public class VolleyManager extends Application{

    private static VolleyManager volleyInstance;
    private RequestQueue requestQueue;
    private static Context volleyContext;
    public static final String TAG = VolleyManager.class.getSimpleName();



    private VolleyManager (Context context) {
        volleyContext = context;
        requestQueue = getRequestQueue();

    }


    public static synchronized VolleyManager getInstance(Context context) {
        if (volleyInstance == null) {
            volleyInstance = new VolleyManager (context);
        }
        return volleyInstance;
    }

    public RequestQueue getRequestQueue() {
        if (requestQueue == null) {
            requestQueue = Volley.newRequestQueue(volleyContext);
        }

        return requestQueue;
    }


    public <T> void addToRequestQueue(Request<T> req, String tag) {
        // set the default tag if tag is empty
        req.setTag(TextUtils.isEmpty(tag) ? TAG : tag);
        getRequestQueue().add(req);
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (requestQueue != null) {
            requestQueue.cancelAll(tag);
        }
    }


}