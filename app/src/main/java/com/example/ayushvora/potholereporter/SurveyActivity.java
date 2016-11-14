package com.example.ayushvora.potholereporter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.nfc.Tag;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.ListView;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ayushvora.potholereporter.customadapters.CheckedTextAdapter;
import com.example.ayushvora.potholereporter.models.SurveyQuestion;
import com.example.ayushvora.potholereporter.types.AverageSpeed;
import com.example.ayushvora.potholereporter.types.CountyName;
import com.example.ayushvora.potholereporter.types.JunctionType;
import com.example.ayushvora.potholereporter.types.PPRRW;
import com.example.ayushvora.potholereporter.types.PotholeType;
import com.example.ayushvora.potholereporter.types.RepairPerformed;
import com.example.ayushvora.potholereporter.types.RoadMaterial;
import com.example.ayushvora.potholereporter.types.SafetyZoneType;
import com.example.ayushvora.potholereporter.types.StreetType;
import com.example.ayushvora.potholereporter.types.TrafficVolume;
import com.example.ayushvora.potholereporter.types.WeatherType;

import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SurveyActivity extends AppCompatActivity {
    private HashMap<String,String> potholeDataMap;
    private int questionIndex;
    private String value;
    private int valPos;
    private List<SurveyQuestion> surveyQuestionArrayList;
    private int surveyQuestionSize;
    private HashMap<Integer,Integer> questionValPosMap;
    private CheckedTextAdapter adapter;
    private ListView optionsListView;
    TextView questionTextView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Intent potholeTypeActivity = getIntent();
        potholeDataMap = (HashMap<String,String>)potholeTypeActivity.getSerializableExtra("potholeDataMap");
        questionValPosMap = new HashMap<Integer,Integer>();
        questionIndex = 0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_survey);
        optionsListView = (ListView) findViewById(R.id.OptionsList);
        questionTextView = (TextView) findViewById(R.id.Question);
        surveyQuestionArrayList = new ArrayList<SurveyQuestion>();
        surveyQuestionArrayList.add(new SurveyQuestion<CountyName>("countyName","Please specify the county of the pohtole",CountyName.class));
        surveyQuestionArrayList.add(new SurveyQuestion<TrafficVolume>("trafficVolume","What is the estimated volume of traffic on this road?",TrafficVolume.class) );
        surveyQuestionArrayList.add(new SurveyQuestion<AverageSpeed>("avgSpeed","What is the relative average speed on this road?", AverageSpeed.class));
        surveyQuestionArrayList.add(new SurveyQuestion<StreetType>("streetType","What is the type of street?",StreetType.class));
        surveyQuestionArrayList.add(new SurveyQuestion<JunctionType>("junctionType","What is the type of junction?",JunctionType.class));
        surveyQuestionArrayList.add(new SurveyQuestion<PPRRW>("pprrw","What is the position of the pothole relative to road-width?",PPRRW.class));
        surveyQuestionArrayList.add(new SurveyQuestion<SafetyZoneType>("safetyZoneType","What is the safety zone that this road belongs to?", SafetyZoneType.class));
        surveyQuestionArrayList.add(new SurveyQuestion<RoadMaterial>("roadMaterial","Identify with your best capacity the type of road material used on the road", RoadMaterial.class));
        surveyQuestionArrayList.add(new SurveyQuestion<WeatherType>("weatherType","What are the current weather conditions?",WeatherType.class));
        surveyQuestionArrayList.add(new SurveyQuestion<RepairPerformed>("repair","Does the pothole show signs of repair work performed",RepairPerformed.class));
        surveyQuestionSize = surveyQuestionArrayList.size();
        populateListView();

    }

//    @Override
//    protected  void onResume(){
//        populateListView();
//    }

    public void onNextClick(View view){
        SurveyQuestion currentQuestion  = surveyQuestionArrayList.get(questionIndex);
        if(questionIndex<surveyQuestionSize){
            questionIndex++;
            populateListView();
        }
        if(questionIndex == (surveyQuestionSize - 1) ){
            Button nextButton = (Button) findViewById(R.id.NextButton);
            nextButton.setVisibility(View.GONE);
            Button submitButton = (Button) findViewById(R.id.SubmitButton);
            submitButton.setVisibility(View.VISIBLE);


        }

    }

    public void onPrevClick(View view){
        if(questionIndex == 0){
            Intent prevIntent = new Intent(this,PotholeTypeActivity.class);
            prevIntent.putExtra("potholeDataMap",potholeDataMap);
            startActivity(prevIntent);

        }
        if(questionIndex>0){
            questionIndex--;
            populateListView();
            if(questionIndex==(surveyQuestionSize-1)-1){
                Button submitButton = (Button) findViewById(R.id.SubmitButton);
                submitButton.setVisibility(View.GONE);
                Button nextButton = (Button) findViewById(R.id.NextButton);
                nextButton.setVisibility(View.VISIBLE);
            }
        }

        if(questionIndex == 0 ){
            Button nextButton = (Button) findViewById(R.id.NextButton);
            nextButton.setVisibility(View.VISIBLE);
        }
    }

    public void onSubmitClick(View view){
        ConnectivityManager connMgr = (ConnectivityManager)
                getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
        if (networkInfo != null && networkInfo.isConnected()) {
            new SavePotholeData().execute();
        } else {
            Toast.makeText(this,"No network",Toast.LENGTH_SHORT);
        }


    }

    private View getViewByPosition(int pos, ListView listView) {
        final int firstListItemPosition = listView.getFirstVisiblePosition();
        final int lastListItemPosition = firstListItemPosition + listView.getChildCount() - 1;

        if (pos < firstListItemPosition || pos > lastListItemPosition ) {
            return listView.getAdapter().getView(pos, null, listView);
        } else {
            final int childIndex = pos - firstListItemPosition;
            return listView.getChildAt(childIndex);
        }
    }

    private void populateListView() {
        adapter = new CheckedTextAdapter(this,surveyQuestionArrayList.get(questionIndex).getOptions(),questionValPosMap,questionIndex);
        optionsListView.setAdapter(adapter);
        optionsListView.setChoiceMode(AbsListView.CHOICE_MODE_SINGLE);
        questionTextView.setText(surveyQuestionArrayList.get(questionIndex).getQuestion());


        optionsListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                optionsListView.setItemChecked(position,true);
                value = (String) optionsListView.getItemAtPosition(position);
                questionValPosMap.put(questionIndex,position);
                potholeDataMap.put(surveyQuestionArrayList.get(questionIndex).getFieldId(),value);
            }
        });

        adapter.notifyDataSetChanged();

    }

    class SavePotholeData extends AsyncTask<String,String,String> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            Toast.makeText(getApplicationContext(),"Saving Data",Toast.LENGTH_SHORT);
        }

        /**
         * Creating product
         * */
        protected String doInBackground(String... args) {
            potholeDataMap.put("priority","HIGH");
            JSONObject request = new JSONObject(potholeDataMap);
            HttpURLConnection client = null;
            String response ="";
            OutputStream outputStream = null;
            try {
//                Connect
                client = (HttpURLConnection) ((new URL("http://192.168.0.101/capstone/receivedata.php").openConnection()));
//                urlConnection.setDoOutput(true);
//                urlConnection.setRequestProperty("Content-Type", "application/json");
//                urlConnection.setRequestProperty("Accept", "application/json");
//                urlConnection.setRequestMethod("POST");
//                urlConnection.connect();
//
//                //Write
//                outputStream = new BufferedOutputStream(urlConnection.getOutputStream());
//
//
//                outputStream.write(potholeObject.toString().getBytes());
//                outputStream.flush();
//
//
//                //Read
//                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), "UTF-8"));
//
//                String line = null;
//                StringBuilder sb = new StringBuilder();
//
//                while ((line = bufferedReader.readLine()) != null) {
//                    sb.append(line);
//                }
//
//                bufferedReader.close();
//                result = sb.toString();


                client.setDoOutput(true);
                client.setDoInput(true);
                client.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
                client.setRequestMethod("POST");
                //client.setFixedLengthStreamingMode(request.toString().getBytes("UTF-8").length);
                client.connect();

                Log.d("doInBackground(Request)", request.toString());

                OutputStreamWriter writer = new OutputStreamWriter(client.getOutputStream());
                String output = request.toString();
                writer.write(output);
                writer.flush();
                writer.close();

                InputStream input = client.getInputStream();
                BufferedReader reader = new BufferedReader(new InputStreamReader(input));
                StringBuilder result = new StringBuilder();
                String line;

                while ((line = reader.readLine()) != null) {
                    result.append(line);
                }
                Log.d("doInBackground(Resp)", result.toString());
                response = result.toString();
            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                client.disconnect();
            }

            return response;

        }

        /**
         * After completing background task Dismiss the progress dialog
         * **/
        protected void onPostExecute(String file_url) {
            Toast.makeText(getApplicationContext(),"Request Sent",Toast.LENGTH_SHORT);
        }

    }

}
