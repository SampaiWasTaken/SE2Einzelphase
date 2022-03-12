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
import java.util.Arrays;


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

        //OnClick Listener for Submit Button
        subBtn.setOnClickListener(view -> serverText.setText(getServerResponse(mnrTxt.getText().toString())));

        //OnClick Listener for Calculate Button
        calcBtn.setOnClickListener(view -> serverText.setText(evenIntToChar(mnrTxt.getText().toString())));
    }

    /**
     * Method that communicates with the server, given a MNR
     * @param mnr The MNR to be used
     * @return Response from the Server
     */
    private String getServerResponse(String mnr)
    {
        ConThread conThread = new ConThread(mnr);
        conThread.start();

        try { conThread.join(); } catch (InterruptedException e) {e.printStackTrace();}

        return conThread.getModmnr();
    }

    /**
     * Converts every other Integer in the MNR String to a character, following the scheme of a=1, b=2, ...
     * @param mnr The MNR to be modified
     * @return The converted MNR
     */
    private String evenIntToChar(String mnr)
    {
        char[] mnrArr = mnr.toCharArray();

        for (int i = 0; i < mnrArr.length; i = i + 2)
            mnrArr[i] = (char) (mnrArr[i] + 48);

        return new String(mnrArr);
    }
}