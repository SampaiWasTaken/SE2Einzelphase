package com.example.einzelphase;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class ConThread extends Thread
{

    private String mnr;
    private String modmnr;

    Socket cSocket;

    public ConThread(String mnr)
    {
        try
        {
            cSocket = new Socket("se2-isys.aau.at", 53212);
            DataOutputStream dso = new DataOutputStream(cSocket.getOutputStream());
            BufferedReader in = new BufferedReader(new InputStreamReader(cSocket.getInputStream()));

            dso.writeBytes(mnr + "\n");
            modmnr = in.readLine();

        } catch (IOException e)
        {
            e.printStackTrace();
            modmnr = "An unexpected error occurred";
        }
    }

    public String getModmnr() {return modmnr;}

}
