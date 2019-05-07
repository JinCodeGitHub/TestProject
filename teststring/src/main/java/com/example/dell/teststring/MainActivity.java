package com.example.dell.teststring;



import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;



public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        String  ss   = "Raw,1654656976,1108000000,,,-1240470277702998975,0.0,3.000000306E9,,,0,7,0.0,17,692855,22,31.01066780090332,683.2000185578217,5.748507411190534,22,130.4627304527967,3.4028234663852886E38,1.57542003E9,,,,0,,1,52.76594161987305,1.57542003E9";

        String [] str   = ss.split(",");
        /*for (String s:str
             ) {

            Log.i("RAW = ",s);

        }*/

        for (int i = 0 ; i <str.length; i++){

            if (str[i].equals("")){
                str[i] = "0";
            }
            Log.i("RAW = ",str[i]);
        }
        byte [] bytes;


//        StringBuilder stringBuilder   = new StringBuilder();
//        stringBuilder.append(ss);
//        String   st     = stringBuilder.substring(4);
//        Log.i("RAW = ",st);
//        Log.i("RAW = ", String.valueOf(str.length));
//        Log.i("RAW =", String.valueOf(ss.startsWith("Raw")));
//        Log.i("RAW =", String.valueOf(ss.length()));




    }
}
