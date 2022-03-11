package com.example.einzelphase;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.StrictMode;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;


public class MainActivity extends AppCompatActivity
{


    Button subBtn;
    EditText mnrTxt;
    TextView serverText;
    Button calcBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        subBtn = findViewById(R.id.buttonSub);
        mnrTxt = findViewById(R.id.editTextNumber2);
        serverText = findViewById(R.id.serverText);
        calcBtn = findViewById(R.id.calc);
        String mnr = mnrTxt.getText().toString();

        subBtn.setOnClickListener(view -> {

            ConThread conThread = new ConThread(mnr);
            conThread.start();

            try
            {
                conThread.join();
            } catch (InterruptedException e)
            {
                e.printStackTrace();
            }

            serverText.setText(conThread.getModmnr());
        });

    }
}