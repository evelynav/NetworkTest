package com.example.networktest;

import android.os.AsyncTask;

public class NetworkServerTCP extends AsyncTask<String, Void, String> {

    MainActivity mainActivity = new MainActivity();

    @Override
    protected String doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute (String result) {
        mainActivity.lblResponseServer.setText(result);
    }
}
