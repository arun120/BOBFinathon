package util;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import Util.ServerDetails;

/**
 * Created by Home on 10-05-2017.
 */
public class HTTPClient {

    public static String post(String surl,String params,String body){
        return post(surl,null,params,body);
    }

    private static String post(String surl,String header,String params,String body){

        String result="";

        HttpURLConnection connection = null;

        System.out.println(body);
        URL url = null;
        try {
            if(params!=null)
            url = new URL(surl + "?" + params);
            else
                url = new URL(surl);
                        connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("POST");
            if(header!=null){
                connection.setRequestProperty("apikey","NnvPUyCWl6YtP7r");
                connection.setRequestProperty("Content-Type","application/json");
            }
            if(body!=null){

                byte[] outputInBytes = body.getBytes("UTF-8");
                connection.setDoOutput(true);
                OutputStream os = connection.getOutputStream();
                os.write( outputInBytes );
                os.close();
            }
            connection.connect();
            InputStream input = connection.getInputStream();
            char c;
            while ((c = (char) input.read()) != (char) -1)
                result += c;
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;



    }

    public static String postBOB(String url,String body){


        return post(ServerDetails.BOBServer+url,"add",null,body);
    }
}
