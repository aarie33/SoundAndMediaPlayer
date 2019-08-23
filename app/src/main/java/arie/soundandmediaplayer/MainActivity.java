package arie.soundandmediaplayer;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    Button btnSound;
    Button btnMedia;
    Button btnMediaStop;
    MediaPlayer mPlayer;
    SoundPool sp ;
    int wav;
    boolean spLoaded = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSound = (Button)findViewById(R.id.btn_soundpool);
        btnSound.setOnClickListener(this);
        btnMedia = (Button)findViewById(R.id.btn_mediaplayer);
        btnMediaStop = (Button)findViewById(R.id.btn_mediaplayer_stop);
        btnMedia.setOnClickListener(this);
        btnMediaStop.setOnClickListener(this);
        mPlayer = MediaPlayer.create(this, R.raw.guitar_background);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            sp = new SoundPool.Builder()
                    .setMaxStreams(10)
                    .build();
        } else {
            sp = new SoundPool(10, AudioManager.STREAM_MUSIC, 1);
        }

        sp.setOnLoadCompleteListener(new SoundPool.OnLoadCompleteListener() {
            public void onLoadComplete(SoundPool soundPool, int sampleId,int status) {
                spLoaded = true;
            }
        });

        wav = sp.load(this, R.raw.fart, 1);
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        switch (id) {
            case R.id.btn_soundpool:
                if (spLoaded == true ){
                    sp.play(wav, 1, 1, 0, 0, 1);
                }
                break;
            case R.id.btn_mediaplayer:
                mPlayer.start();
                break;
            case R.id.btn_mediaplayer_stop:
                mPlayer.stop();
                break;
            default:
                break;
        }
    }
}
