package com.example.einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConThread extends Thread
{
    private String modmnr;

    public ConThread(String mnr)
    {
        try
        {
            Socket cSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream dso = new DataOutputStream(cSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));

            //write MNR to server
            dso.writeBytes(mnr + "\n");

            //read Server response
            modmnr = in.readLine();

        }
        catch (IOException e)
        {
            e.printStackTrace();
            modmnr = "An unexpected error occurred";
        }
    }

    public String getModmnr() {return modmnr;}

}
