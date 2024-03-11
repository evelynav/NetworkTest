package com.example.networktest;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class MainActivity extends AppCompatActivity {

    EditText txtMatNrInput;
    TextView lblServerOutput;
    Button btnSend;
    String message;

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

        txtMatNrInput = findViewById(R.id.txtMatNrInput);
        btnSend = findViewById(R.id.btnSend);
        lblServerOutput = findViewById(R.id.lblServerOutput);
    }

    public void networkServer(View view) {

        txtMatNrInput = findViewById(R.id.txtMatNrInput);
        lblServerOutput = findViewById(R.id.lblServerOutput);

        Thread thread = new Thread(() -> {
            try {
                Socket socket = new Socket("se2-submission.aau.at", 20080);

                PrintWriter printWriter = new PrintWriter(socket.getOutputStream());
                printWriter.println(txtMatNrInput.getText().toString());
                printWriter.flush();

                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                message = in.readLine();

                socket.close();
                printWriter.close();
            } catch (UnknownHostException err){
                message = "Unknown Host:\n" + err.getMessage();
            } catch (IOException err) {
                message = "IO Exception:\n" + err.getMessage();
            }
        });
        thread.start();

        try {
            thread.join();
        }catch (InterruptedException err){
            err.printStackTrace();
        }

        lblServerOutput.setText(message);
    }

    //MatrikelNr 11834666 mod 7 = 4 -> Gemeinsamer Teiler mit Indizes
    public void findCommonDivisors(View view) {
        txtMatNrInput = findViewById(R.id.txtMatNrInput);
        String matrikelNr = txtMatNrInput.getText().toString();
        boolean foundCD = false;

        for (int i = 0; i < matrikelNr.length(); i++) {
            for (int j = i + 1; j < matrikelNr.length(); j++) {
                int num1 = Character.getNumericValue(matrikelNr.charAt(i));
                int num2 = Character.getNumericValue(matrikelNr.charAt(j));

                if(calcCommonDivisor(num1, num2)){
                    message = "Gemeinsame Teiler gefunden:\n" +
                            "Ziffer: " + num1 + ", Index: "+ i +
                            "\nZiffer: " + num2 + ", Index: "+ j;
                    foundCD = true;
                }
            }
        }
        if(!foundCD){
            message = "Keine gemeinsamen Teiler gefunden.";
        }
        lblServerOutput.setText(message);
    }


    private boolean calcCommonDivisor(int num1, int num2) {
        for (int i = 2; i <= Math.min(num1, num2); i++) {
            if(num1 % i == 0 && num2 % i == 0){
                return true;
            }
        } return false;
    }
}