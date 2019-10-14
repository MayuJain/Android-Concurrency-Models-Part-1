package com.example.inclass04;


/*
* InClass Assignment 4
* MainActivity.java
* Group 29
* Mayuri Jain, Narendra Pahuja
* */
import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.SeekBar;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Collections;


public class MainActivity extends AppCompatActivity {

    SeekBar seekBar;
    TextView selectedText;
    Button btGenerate;
    ProgressBar progressBar;
    TextView minText;
    TextView maxText;
    TextView avgText;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        seekBar = findViewById(R.id.seekBar);
        selectedText = findViewById(R.id.selectedValue);
        minText = findViewById(R.id.minValue);
        maxText = findViewById(R.id.maxValue);
        avgText = findViewById(R.id.avgValue);
        btGenerate = findViewById(R.id.bt_generate);
        progressBar = findViewById(R.id.progressBar);
        progressBar.setVisibility(View.INVISIBLE);
        int seekValue = seekBar.getProgress();

        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                selectedText.setText(i + " times");
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        btGenerate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Dowork().execute(seekBar.getProgress());
            }
        });

    }
    class Dowork extends AsyncTask<Integer, Integer, Double[]>{

        @Override
        protected Double[] doInBackground(Integer... integers) {

            Double[] result = new Double[3];
            if(integers[0] == 0){
                result[0] = 0.0;
                result[1] = 0.0;
                result[2] = 0.0;
            }else{
                ArrayList<Double> list = HeavyWork.getArrayNumbers(integers[0]);
                double min = Collections.min(list);
                double max = Collections.max(list);
                double sum = 0.0;
                for (double x : list) {
                    sum = sum + x;
                }
                double avg = sum/list.size();
                result[0] = min;
                result[1] = max;
                result[2] = avg;
            }
            return result;
        }

        @Override
        protected void onPreExecute() {
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Double[] integer) {
            progressBar.setVisibility(View.INVISIBLE);
            minText.setText(integer[0]+"");
            maxText.setText(integer[1]+"");
            avgText.setText(integer[2]+"");
        }

        @Override
        protected void onProgressUpdate(Integer... values) {


        }
    }

}


