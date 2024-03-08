package com.example.networktest;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    EditText txtMatrikelNr;
    Button btnSend;
    TextView lblResponseServer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EdgeToEdge.enable(this);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        txtMatrikelNr = findViewById(R.id.txtMatrikelNr);
        btnSend = findViewById(R.id.btnSend);
        lblResponseServer = findViewById(R.id.lblResponseServer);

    }

    protected String doInBackground(String... strings) {
        try{
            //Connect to the server
            Socket socket = new Socket("se2-submission.at", 20080);

            //Send data to server
            BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
            out.write("Hello Server");

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
}