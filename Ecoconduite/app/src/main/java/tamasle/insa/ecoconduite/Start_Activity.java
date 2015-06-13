package tamasle.insa.ecoconduite;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;

import java.util.ArrayList;

import tamasle.insa.ecoconduite.route.Road;
import tamasle.insa.ecoconduite.route.RoadWrapper;


public class Start_Activity extends ActionBarActivity implements AdapterView.OnItemClickListener {

    private Button connectButton;
    private TextView textRoad;
    private ListView listView;
    public static TCPClient client=null;
    private connectTask task;
    public ArrayList <Road> messageList;
    private RoadAdapter adapter;

    private RoadWrapper roadWrapper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_layout);
        messageList=new ArrayList<Road>();
        connectButton=(Button)findViewById(R.id.connect_button);
        textRoad=(TextView)findViewById(R.id.road_textview);
        listView=(ListView)findViewById(R.id.listview);
        adapter=new RoadAdapter(this,messageList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(this);


        connectButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                connectButton.setVisibility(View.INVISIBLE);
                textRoad.setText("Choisissez une route");
                task = new connectTask();
                task.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }
        });
    }

    @Override
    protected void onDestroy() {
        try {
            System.out.println("onDestroy.");
           client.sendMessage("bye");
            client.stopClient();
            task.cancel(true);
            task = null;
        } catch (Exception e) {
            e.printStackTrace();
        }
        super.onDestroy();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void onItemClick(AdapterView<?> parent, View view, int position,long id) {
        Road r=(Road)adapter.getItem(position);
        Log.v("CLICK",r.toString());
        Data.currentRoad=r;
        Intent i = new Intent(Start_Activity.this,RoadActivity.class);
        startActivity(i);

    }

    public static boolean isNumeric(String str)
    {
        try
        {
            Float f = Float.parseFloat(str);
        }
        catch(NumberFormatException nfe)
        {
            return false;
        }
        return true;
    }

    public class connectTask extends AsyncTask<String, String, TCPClient> {
        private  boolean premiere=true;
        @Override
        protected TCPClient doInBackground(String... message) {
            //we create a TCPClient object and
            client = new TCPClient(new TCPClient.OnMessageReceived() {
                @Override
                //here the messageReceived method is implemented
                public void messageReceived(String message) {
                    try {
                        //this method calls the onProgressUpdate
                        publishProgress(message);
                        if (message != null) {
                            System.out.println("Return Message from Socket::::: >>>>> " + message);
                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
            client.run();
            if (client != null) {
                client.sendMessage("Initial Message when connected with Socket Server");
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(String... values) {
            super.onProgressUpdate(values);

            Log.v("VALEUR",values[0]);
            String json =values[0];

            if (premiere && json.charAt(0)=='{'){
                premiere=false;

                Gson gson = new Gson();
                roadWrapper = gson.fromJson(json, RoadWrapper.class);
                Log.v("json", roadWrapper.toString());
                for (int i = 0; i < roadWrapper.getRoads().length; i++) {
                    messageList.add(roadWrapper.getRoads()[i]);
                }

                Log.v("COPIE", "Copie terminee");

                for (Road r : messageList) {
                    Log.v("route", r.toString());
                }
                adapter.notifyDataSetChanged();

            }else {

                if (isNumeric(json)){
                    Log.v("VALEUR","number");
                    RoadActivity.vitesseCourante = Float.parseFloat(json);
                } else {
                    Log.v("VALEUR","nop");
                }
            }


        }
    }
}
