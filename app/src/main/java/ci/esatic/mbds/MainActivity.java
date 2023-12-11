package ci.esatic.mbds;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private float prevX, prevY, centerX, centerY;
    private double initialAngle, currentAngle;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);




        ImageButton telephone = findViewById(R.id.button2);
        telephone.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Naviguer de MainActivity à Page2Activity
                Intent intent = new Intent(MainActivity.this, TelephoneActivity.class);
                startActivity(intent);
            }
        });

        ImageButton message = findViewById(R.id.button3);
        message.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Naviguer de MainActivity à Page2Activity
                Intent intent = new Intent(MainActivity.this, MessageActivity.class);
                startActivity(intent);
            }
        });

        ImageButton buttonMap = findViewById(R.id.buttonMap);
        buttonMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://www.google.com/maps/place/ECOLE+SUP%C3%89RIEURE+AFRICAINE+DES+TIC+(+ESATIC)/@5.290714,-3.998846,17z/data=!3m1!4b1!4m6!3m5!1s0xfc1e95a7230b815:0xb42441cd6ea4939c!8m2!3d5.290714!4d-3.998846!16s%2Fg%2F11h0r0235?entry=ttu");
            }
        });


        ImageButton audio = findViewById(R.id.audio);
        audio.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Naviguer de MainActivity à Page2Activity
                Intent intent = new Intent(MainActivity.this, PlayMusicActivity.class);
                startActivity(intent);
            }
        });

        ImageButton video = findViewById(R.id.video);
        video.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Naviguer de MainActivity à Page2Activity
                Intent intent = new Intent(MainActivity.this, VideoActivity.class);
                startActivity(intent);
            }
        });

        ImageButton esatic= findViewById(R.id.esatic);
        esatic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoUrl("https://esatic.ci/");
             }
        });
    }

    private void gotoUrl(String s) {
        Uri uri = Uri.parse(s);
        startActivity(new Intent(Intent.ACTION_VIEW, uri));
    }



}