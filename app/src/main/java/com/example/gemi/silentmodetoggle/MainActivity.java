package com.example.gemi.silentmodetoggle;

import com.example.gemi.silentmodetoggle.util.RingerHelper;
import android.app.Activity;
//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.media.AudioManager;
import android.widget.ImageView;

public class MainActivity extends Activity {
    public AudioManager audioManager;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        audioManager = (AudioManager)getSystemService(AUDIO_SERVICE);
        setContentView(R.layout.activity_main);

        FrameLayout contentView =(FrameLayout)findViewById(R.id.content);

        contentView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RingerHelper.performToggle(audioManager);
                updateUI();
            }
        });
    }




    /**
     * Updates the UI image to show an image representing silent or
     * normal, as appropriate
     */

    private void updateUI() {
        // Find the view named phone_icon in our layout.  We know it's
        // an ImageView in the layout, so downcast it to an ImageView.
        ImageView imageView = (ImageView) findViewById(R.id.phone_icon);

        // Set phoneImage to the ID of image that represents ringer on
        // or off.  These are found in res/drawable-xxhdpi
        int phoneImage = RingerHelper.isPhoneSilent(audioManager)
                ? R.drawable.ringer_off
                : R.drawable.ringer_on;

        // Set the imageView to the image in phoneImage
        imageView.setImageResource(phoneImage);
    }

    /**
     * Every time the activity is resumed, make sure to update the
     * buttons to reflect the current state of the system (since the
     * user may have changed the phone's silent state while we were in
     * the background).
     *
     * Visit http://d.android.com/reference/android/app/Activity.html
     * for more information about the Android Activity lifecycle.
     */
    @Override
    protected void onResume() {
        super.onResume();

        // Update our UI in case anything has changed.
        updateUI();
    }
}