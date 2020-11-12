package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONObject;
import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForcast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        ForecastQuery  req = new ForecastQuery ();
        req.execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric",
                "http://api.openweathermap.org/data/2.5/uvi?appid=7e943c97096a9784391a981c4d878b22&lat=45.348945&lon=-75.759389"
                );

    }


 private class ForecastQuery extends AsyncTask< String, Integer, String> {

     TextView tempSet;
     TextView minSet;
     TextView maxSet;
     TextView uvSet;
     double uvRating;
     String maxTemp, minTemp, curTemp;

    public String doInBackground(String ... args)
    {
        try {

            //create a URL object of what server to contact:
            URL url = new URL(args[0]);

            //open the connection
            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

            //wait for data:
            InputStream response = urlConnection.getInputStream();


            //From part 3: slide 19
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(false);
            XmlPullParser xpp = factory.newPullParser();
            xpp.setInput( response  , "UTF-8"); //response is data from the server



            //From part 3, slide 20
            String parameter = null;

            int eventType = xpp.getEventType(); //The parser is currently at START_DOCUMENT

            while(eventType != XmlPullParser.END_DOCUMENT)
            {

                if(eventType == XmlPullParser.START_TAG)
                {
                    //If you get here, then you are pointing at a start tag
                    if(xpp.getName().equals("temperature"))
                    {
                        //If you get here, then you are pointing to a <Weather> start tag
                       curTemp= xpp.getAttributeValue(null,    "value");

                         minTemp = xpp.getAttributeValue(null, "min");

                         maxTemp = xpp.getAttributeValue(null, "max");



                    }

                }
                eventType = xpp.next(); //move to the next xml event and store it in a variable
            }




            //done xml
            //create a URL object of what server to contact:
             url = new URL(args[1]);

            //open the connection
             urlConnection = (HttpURLConnection) url.openConnection();

            //wait for data:
             response = urlConnection.getInputStream();

    // json is UTF-8 by default
            BufferedReader reader = new BufferedReader(new InputStreamReader(response, "UTF-8"), 8);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null)
            {
                sb.append(line + "\n");
            }
            String result = sb.toString();
            JSONObject resp = new JSONObject((result));//convet string to jsonobject
            uvRating = resp.getDouble("value");


        }
        catch (Exception e)
        {

        }

        return "Done";
    }

    //Type 2
    public void onProgressUpdate(Integer ... args)
    {

    }
    //Type3
    public void onPostExecute(String fromDoInBackground)
    {     tempSet = findViewById(R.id.currentTemp);
        tempSet.setText("Current Temp: "+ curTemp+" ");
        minSet = findViewById(R.id.minTemp);
        minSet.setText("Min: "+minTemp+" ");
        maxSet = findViewById(R.id.maxTemp);
        maxSet.setText("Max: "+maxTemp+" ");
        uvSet = findViewById(R.id.UV);
      String  uvR=String.valueOf( uvRating);
        uvSet.setText("UV: " +uvR+" ");


        Log.i("HTTP", fromDoInBackground);
    }
}
}
