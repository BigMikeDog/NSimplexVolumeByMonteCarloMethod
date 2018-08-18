package com.michaelthomasfreeman.android.n_simplexvolumebymontecarlomethod;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    EditText nValueField;
    EditText dartNumberField;
    TextView answerField;

    int dimension;
    int numDarts;
    int dartsInside;

    Random mRandom=new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nValueField=findViewById(R.id.edit_text_n_value);
        dartNumberField=findViewById(R.id.edit_text_dart_value);
        answerField=findViewById(R.id.answer_field);
    }

    public void findVolume(View view){
        if (nValueField.getText().toString().isEmpty()){
            answerField.setText(R.string.empty_error);
            return;
        }
        dimension=Integer.parseInt(nValueField.getText().toString());
        if(dimension==0){
            answerField.setText(R.string.n_error);
            return;
        }
        if (dartNumberField.getText().toString().isEmpty()){
            answerField.setText(R.string.empty_error);
            return;
        }
        numDarts=Integer.parseInt(dartNumberField.getText().toString());
        double dart[]= new double[dimension];
        dartsInside=0;

        for (int d=0;d<=numDarts;d++){
            dart=throwDart(dart);
            double sum=0;
            for (int i=0;i<dimension;i++){
                sum=sum+dart[i];
            }
            if (sum<=1){
                dartsInside++;
            }
        }
        double volume=dartsInside/(double) numDarts;
        Log.d("debug", "findVolume: inside: "+dartsInside);
        Log.d("debug", "findVolume: tot: "+numDarts);
        Log.d("debug", "findVolume: "+volume);
        updateAnswer(volume);
    }

    public double[] throwDart(double dart[]){
        for (int i=0;i<dimension;i++){
            dart[i]=mRandom.nextDouble();
        }
        return dart;
    }

    public void updateAnswer(double volume){
        answerField.setText(getString(R.string.answer_text,String.valueOf(dimension),Double.toString(volume)));
    }

}
