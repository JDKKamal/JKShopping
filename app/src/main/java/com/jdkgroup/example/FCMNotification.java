package com.jdkgroup.example;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import org.json.JSONObject;

public class FCMNotification {

    public static void main(String[] args)
    {
        String tokenid = "";
        String serverkey = "";
        send_FCM_Notification(tokenid, serverkey, "Hi ");
    }

    final static private String FCM_URL = "https://fcm.googleapis.com/fcm/send";

    static void send_FCM_Notification(String tokenId, String server_key, String message)
    {
        try
        {
            URL url = new URL(FCM_URL);
            HttpURLConnection conn;
            conn = (HttpURLConnection) url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Authorization", "key=" + server_key);
            conn.setRequestProperty("Content-Type", "application/json");

            JSONObject body = new JSONObject();
            body.put("to", tokenId);
            body.put("priority", "high");

            JSONObject notification = new JSONObject();
            notification.put("body", message);
            notification.put("title", "JK Shopping");
            // notification.put("icon", "myicon");

            JSONObject data = new JSONObject();
            data.put("userid", "value1");
            data.put("username", "value2");

            body.put("notification", notification);
            body.put("data", data);

            OutputStreamWriter wr = new OutputStreamWriter(conn.getOutputStream());
            wr.write(body.toString());
            wr.flush();

            int status = 0;
            if (null != conn)
            {
                status = conn.getResponseCode();
            }

            if (status != 0)
            {
                if (status == 200)
                {
                    BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                    System.out.println("Android Notification Response : " + reader.readLine());
                }
                else if (status == 401)
                {
                    System.out.println("Notification Response : TokenId : " + tokenId + " Error occurred :");
                }
                else if (status == 501)
                {
                    System.out.println("Notification Response : [errorCode=ServerError] TokenId : " + tokenId);
                }
                else if (status == 503)
                {
                    System.out.println("Notification Response : FCM Service is Unavailable  TokenId : " + tokenId);
                }
            }
        }
        catch (Exception mlfexception)
        {
            System.out.println("Error occurred while sending push Notification!.." + mlfexception.getMessage());
        }

    }

}
