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

public class MainActivity extends AppCompatActivity {

    EditText txtMatNrInput;
    Button btnSend;
    TextView lblServerOutput;
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

    //MatrikelNr 11834666 mod 7 = 4 -> Gemeinsamer Teiler mit Indizes
    public void findCommonDivisors(View view) {
        txtMatNrInput = findViewById(R.id.txtMatNrInput);
        String matrikelNr = txtMatNrInput.getText().toString();

        for (int i = 0; i < matrikelNr.length(); i++) {
            for (int j = i + 1; j < matrikelNr.length(); j++) {
                int num1 = Character.getNumericValue(matrikelNr.charAt(i));
                int num2 = Character.getNumericValue(matrikelNr.charAt(j));

                if(calcCommonDivisor(num1, num2)){
                    message = "Gemeinsame Teiler gefunden:\n" +
                            "Ziffer: " + num1 + ", Index: "+ i +
                            "\nZiffer: " + num2 + ", Index: "+ j;
                }
            }
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