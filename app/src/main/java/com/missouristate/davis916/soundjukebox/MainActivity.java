package com.missouristate.davis916.soundjukebox;

import android.app.Activity;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;
import android.widget.MediaController;

/*
 * Laura Davis CIS 262-902
 * 24 March 2018
 * This app plays sounds when a button is pressed.
 * The first four sounds are pre-loaded and the
 * fifth sound is played by the MediaPlayer.
 * The buttons are configured with an OnClickListener.
 */

@SuppressWarnings("deprecation")
public class MainActivity extends Activity {

    private ImageButton bellClangBtn;
    private ImageButton funkyGongBtn;
    private ImageButton spookyCryBtn;
    private ImageButton randomHaBtn;
    private ImageButton drumSoloBtn;

    private SoundPool soundPool;
    private SparseIntArray soundMap;
    private MediaPlayer mMediaPlayer;
    private MediaController mMediaController;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        configureSounds();
        initializeJukeBoxBtns();
    }//end onCreate()

    private void configureSounds(){
        //Configure the sounds used in the jukebox
        //Pre-load the first four sounds
        soundPool = new SoundPool(1, AudioManager.STREAM_MUSIC, 0);
        soundMap = new SparseIntArray(4);
        soundMap.put(1, soundPool.load(this, R.raw.bell_clang, 1));
        soundMap.put(2, soundPool.load(this, R.raw.funky_gong, 1));
        soundMap.put(3, soundPool.load(this, R.raw.spooky_cry, 1));
        soundMap.put(4, soundPool.load(this, R.raw.random_ha, 1));

        //Fifth sound will be played in media player
        mMediaPlayer = MediaPlayer.create(this, R.raw.drum);
        mMediaController = new MediaController(this);
        mMediaController.setEnabled(true);
    }//end configureSounds()

    private void initializeJukeBoxBtns(){
        //Set references to the sound effect buttons on the layout
        bellClangBtn = (ImageButton) findViewById(R.id.imageButton);
        funkyGongBtn = (ImageButton) findViewById(R.id.imageButton2);
        spookyCryBtn = (ImageButton) findViewById(R.id.imageButton3);
        randomHaBtn = (ImageButton) findViewById(R.id.imageButton4);
        drumSoloBtn = (ImageButton) findViewById(R.id.imageButton5);

        //Register listener events for the buttons on the layout
        bellClangBtn.setOnClickListener(playSoundEffect);
        funkyGongBtn.setOnClickListener(playSoundEffect);
        spookyCryBtn.setOnClickListener(playSoundEffect);
        randomHaBtn.setOnClickListener(playSoundEffect);
        drumSoloBtn.setOnClickListener(playSoundEffect);
    }//end initializeJukeboxBtns

    private View.OnClickListener playSoundEffect = new View.OnClickListener(){
        public void onClick(View btn){
            //Identify the sound to be played
            String soundName = (String) btn.getContentDescription();

            //Play the sound
            if(soundName.contentEquals("Bell clang")){
                soundPool.play(1,1,1,1,0,1.0f);
            }
            else if(soundName.contentEquals("Funky gong")){
                soundPool.play(2,1,1,1,0,1.0f);
            }
            else if(soundName.contentEquals("Spooky cry")){
                soundPool.play(3,1,1,1,0,1.0f);
            }
            else if(soundName.contentEquals("Random ha")){
                soundPool.play(4,1,1,1,0,1.0f);
            }

            else if(soundName.contentEquals("Drum solo")){
                mMediaController.show();
                mMediaPlayer.start();
            }
        }
    };//end playSoundEffect

    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        //Inflate the menu
        getMenuInflater().inflate(R.menu.my, menu);
        return true;
    }//end createOptionsMenu

    @Override
    public boolean onOptionsItemSelected(MenuItem item){
        //Handle action bar item clicks here. The action bar will
        //automatically handle clicks on the Home/Up button,
        //as long as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings){
            return true;
        }
        return super.onOptionsItemSelected(item);
    }//end onOptionsItemSelected

}//end MainActivity
