package tamasle.insa.ecoconduite;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import tamasle.insa.ecoconduite.route.Road;
import tamasle.insa.ecoconduite.route.Segment;
import tamasle.insa.ecoconduite.route.SegmentImproved;

/**
 * Created by Aurelien on 25/05/2015.
 */
public class Algo {
    public static ArrayList<Segment> generationRoute(Road route){
        List<Segment> list = Arrays.asList(route.getSegments());

        for(Segment s :list){
            System.out.println(s);
        }
        Collections.reverse(list);

        Segment segmentP=null;
        int maVitesse,vitesseP;
        int distance;

        ArrayList<Segment> listeFinale=new ArrayList<Segment>();
        for(Segment s :list){
            if (segmentP==null){
                //C'est le premier segment de la liste on l'ajoute simplement
                segmentP=s;
                listeFinale.add(s);
            } else {
                maVitesse=s.getMax_speed();
                vitesseP=segmentP.getMax_speed();
                if (maVitesse>vitesseP){
                    //Il faut decelerer
                    //on ajoute un segment improved
                    distance=distForSpeed(maVitesse,vitesseP);
                    listeFinale.add(new SegmentImproved(s, distance));
                    segmentP=s;
                } else {
                    //Il faut accelerer
                    //on ajoute le segment simplement
                    listeFinale.add(s);
                    segmentP=s;
                }
            }
        }
        System.out.println("Liste finale");
        for(Segment s:listeFinale){

            System.out.println(s);
        }
        Collections.reverse(listeFinale);
        System.out.println("Liste finale");
        for(Segment s:listeFinale){

            System.out.println(s);
        }
        return listeFinale;


    }

    public static int distForSpeed(int s1,int s2){
        //5.01	14.91
        if (s1 ==50){
            if (s2==30){
                return 70;
            } else {
                return 130;
            }
        } else{
            return 130-70;
        }

    }

}
