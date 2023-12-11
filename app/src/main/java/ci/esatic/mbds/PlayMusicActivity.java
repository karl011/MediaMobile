package ci.esatic.mbds;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;

public class PlayMusicActivity extends AppCompatActivity {



    private MediaPlayer mediaPlayer;
    private ImageButton buttonPlay, buttonPause, buttonStop;
    private SeekBar seekBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_music);

        mediaPlayer = MediaPlayer.create(this, R.raw.son);

        buttonPlay = findViewById(R.id.buttonPlay);
        buttonPause = findViewById(R.id.buttonPause);


        buttonPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                playAudio();
            }
        });

        buttonPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pauseAudio();
            }
        });

        ImageButton annuler = findViewById(R.id.audioRetour);
        annuler.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                // Naviguer de MainActivity à Page2Activity
                Intent intent = new Intent(PlayMusicActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void playAudio() {
        if (!mediaPlayer.isPlaying()) {
            mediaPlayer.start();
            buttonPlay.setVisibility(View.GONE);
            buttonPause.setVisibility(View.VISIBLE);

        }
    }

    private void pauseAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.pause();
            buttonPlay.setVisibility(View.VISIBLE);
            buttonPause.setVisibility(View.GONE);

        }
    }

    private void stopAudio() {
        if (mediaPlayer.isPlaying()) {
            mediaPlayer.stop();
            mediaPlayer.release();
            mediaPlayer = MediaPlayer.create(this, R.raw.son);
            buttonPlay.setVisibility(View.VISIBLE);
            buttonPause.setVisibility(View.GONE);
            buttonStop.setVisibility(View.GONE);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mediaPlayer != null) {
            mediaPlayer.release();
        }

    }
}
