package tamasle.insa.ecoconduite;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import tamasle.insa.ecoconduite.route.Road;
import tamasle.insa.ecoconduite.route.Segment;
import tamasle.insa.ecoconduite.route.SegmentImproved;


public class RoadActivity extends ActionBarActivity {

    private TextView vitesseCouranteTextView,vitesseMax1TextView,vitesseMax2TextView,distance1TextView,distance2TextView;
    public TCPClient client =Start_Activity.client;
    private BestCountDownTimer countDownTimer;
    public static Float vitesseCourante;
    private float metreRestant;
    private int indiceCourant;
    private Road maRoute;
    private ArrayList<Segment> mesSegments = new ArrayList<Segment>();
    private boolean bipAttente=false;
    private MediaPlayer mp;

    private Color[] couleurs = new Color[3];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_road);
        maRoute=Data.currentRoad;
        mesSegments=Algo.generationRoute(maRoute);
        setTitle(maRoute.getName());

        //Recuperation des 5 TextView
        vitesseCouranteTextView= (TextView)findViewById(R.id.text_vitesse_courante);
        vitesseMax1TextView=(TextView)findViewById(R.id.text_vitessemax1);
        vitesseMax2TextView=(TextView)findViewById(R.id.text_vitessemax2);
        distance1TextView=(TextView)findViewById(R.id.text_distance1);
        distance2TextView=(TextView)findViewById(R.id.text_distance2);

        //Initialisation des backgrounds



        //Initialisation des variables
        RoadActivity.vitesseCourante=0f;
        indiceCourant=0;
        metreRestant=mesSegments.get(0).getLength();


        //Initialisation de l'affichage
        updateDisplay();

        countDownTimer=new BestCountDownTimer(Integer.MAX_VALUE,1000) {

            @Override
            public void onTick(long millisUntilFinished) {
                Log.v("Tache","t :"+millisUntilFinished);
                // Demande de mise a jour de la vitesse
                client.sendMessage("Donne moi la vitesse");
                // La mise a jour se fait automatiquement

                updateData();
                updateDisplay();


            }

            @Override
            public void onFinish() {
                Log.v("Tache", "Fini");
            }
        }.start();




    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countDownTimer.cancel();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_road, menu);
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

    private void updateData(){
        float vitesse = (float)RoadActivity.vitesseCourante/3.6f;
        Log.v("UPDATE DATA", vitesseCourante + "");
        Log.v("UPDATE DATA", vitesse + "");
        if (vitesse>metreRestant){
            // passage d'une route à une autre
            float diff=vitesse -metreRestant;
            indiceCourant++;
            if(bipAttente){
                mp = MediaPlayer.create(RoadActivity.this, R.raw.beep);
                mp.start();
                bipAttente=false;
            }
            if (indiceCourant<mesSegments.size())
            metreRestant=mesSegments.get(indiceCourant).getLength()-diff;
        }else {
            metreRestant-=vitesse;
            if(mesSegments.get(indiceCourant) instanceof SegmentImproved){
                SegmentImproved si = (SegmentImproved)mesSegments.get(indiceCourant);
                if(metreRestant<=si.getDistance() && !bipAttente){
                    mp = MediaPlayer.create(RoadActivity.this,R.raw.beep);
                    mp.start();
                    bipAttente=true;
                }
            }
        }


    }

    private void updateDisplay(){
        //Vitesse courante mise a jour
        vitesseCouranteTextView.setText("" + vitesseCourante);

        vitesseMax1TextView.setText("" + mesSegments.get(indiceCourant).getMax_speed());
        distance1TextView.setText("" + metreRestant);

        if (indiceCourant+1>=mesSegments.size()){
            vitesseMax2TextView.setText("Trajet termine");
            distance2TextView.setText("");

            vitesseMax1TextView.setText("");
            distance1TextView.setText("");


            endRoad();
        } else {
            vitesseMax2TextView.setText("" +mesSegments.get(indiceCourant+1).getMax_speed());
            distance2TextView.setText(""+mesSegments.get(indiceCourant+1).getLength());
        }

    }

    private void endRoad(){
    countDownTimer.cancel();
}
}
