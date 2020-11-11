package com.example.lab3_1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class WeatherForcast extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_forcast);
        ForecastQuery  req = new ForecastQuery ();
        req.execute("http://api.openweathermap.org/data/2.5/weather?q=ottawa,ca&APPID=7e943c97096a9784391a981c4d878b22&mode=xml&units=metric");
    }
}

 class ForecastQuery extends AsyncTask< String, Integer, String> {
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
                    if(xpp.getName().equals("Temperature"))
                    {
                        //If you get here, then you are pointing to a <Weather> start tag
                        String tempValue = xpp.getAttributeValue(null,    "value");
                        String minTempValue = xpp.getAttributeValue(null, "min");
                        String maxTempValue = xpp.getAttributeValue(null, "max");
                    }

                    else if(xpp.getName().equals("AMessage"))
                    {
                        parameter = xpp.getAttributeValue(null, "message"); // this will run for <AMessage message="parameter" >
                    }
                    else if(xpp.getName().equals("Weather"))
                    {
                        parameter = xpp.getAttributeValue(null, "outlook"); //this will run for <Weather outlook="parameter"
                        parameter = xpp.getAttributeValue(null, "windy"); //this will run for <Weather windy="paramter"  >
                    }
                    else if(xpp.getName().equals("Temperature"))
                    {
                        xpp.next(); //move the pointer from the opening tag to the TEXT event
                        parameter = xpp.getText(); // this will return  20
                    }
                }
                eventType = xpp.next(); //move to the next xml event and store it in a variable
            }


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
    {
        Log.i("HTTP", fromDoInBackground);
    }
}
}