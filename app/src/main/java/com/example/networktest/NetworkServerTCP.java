package com.example.networktest;

import android.os.AsyncTask;
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

            //TODO Read response from server

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
