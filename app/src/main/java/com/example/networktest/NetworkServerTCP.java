package com.example.networktest;

import android.os.AsyncTask;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.Socket;
import java.io.IOException;
import java.net.UnknownHostException;

public class NetworkServerTCP extends AsyncTask<String, Void, String> {

    MainActivity mainActivity = new MainActivity();

    @Override
    protected String doInBackground(String... strings) {
        try{
            //Connect to the server
            Socket socket = new Socket("se2-submission.at", 20080);

            //TODO Send data to server

            //Read response from server
            BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            String receivedMessage = in.readLine();

            //Close connection
            socket.close();
        } catch (UnknownHostException err){
            return "Unknown Host: " + err.getMessage();
        } catch (IOException err) {
            return "IO Exception: " + err.getMessage();
        }
        return null;
    }

    @Override
    protected void onPostExecute (String result) {
        mainActivity.lblResponseServer.setText(result);
    }
}
