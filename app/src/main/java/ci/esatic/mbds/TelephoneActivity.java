package ci.esatic.mbds;

import androidx.appcompat.app.AppCompatActivity;
import android.net.Uri;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Button;
import  android.widget.ImageButton;
import android.widget.TextView;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;

import android.content.pm.PackageManager;

public class TelephoneActivity extends AppCompatActivity {
    private TextView phoneScreen ;
    static int PERMISSION_CODE= 100;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_telephone);
        phoneScreen = findViewById(R.id.phoneScreen);

        if (ContextCompat.checkSelfPermission(TelephoneActivity.this,Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED){

            ActivityCompat.requestPermissions(TelephoneActivity.this,new String[]{Manifest.permission.CALL_PHONE},PERMISSION_CODE);

        }

        int[] buttonIds = {
                R.id.touchOne,
                R.id.touchTwo,
                R.id.touchTree,
                R.id.touchFour,
                R.id.touchFive,
                R.id.touchSix,
                R.id.touchSeven,
                R.id.touchEight,
                R.id.touchNine,
                R.id.touchStar,
                R.id.touchZero,
                R.id.touchHash
        };

        for (int buttonId : buttonIds) {
            Button button = findViewById(buttonId);

            button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Button clickedButton = (Button) v;
                String buttonText = clickedButton.getText().toString();
                appendToTextView(buttonText);

            }
            });
        }
        ImageButton touchCall = findViewById(R.id.touchCall);

        touchCall.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                String phoneno = phoneScreen.getText().toString();
                Intent i = new Intent(Intent.ACTION_CALL);
                i.setData(Uri.parse("tel:"+phoneno));
                startActivity(i);
            }
        });
        ImageButton annuler = findViewById(R.id.buttonBack);
        annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Naviguer de MainActivity à Page2Activity
                Intent intent = new Intent(TelephoneActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageButton deleteButton = findViewById(R.id.deleteButton);
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                removeFromTextView();
            }
        });

    }



    private void removeFromTextView() {
        String currentText = phoneScreen.getText().toString();
        if (!currentText.isEmpty()) {
            // Vérifiez d'abord si le texte n'est pas vide
            currentText = currentText.substring(0, currentText.length() - 1);
            phoneScreen.setText(currentText);
        }
    }

    private void appendToTextView(String text) {
        String currentText = phoneScreen.getText().toString();
        currentText += text;
        phoneScreen.setText(currentText);
    }
}