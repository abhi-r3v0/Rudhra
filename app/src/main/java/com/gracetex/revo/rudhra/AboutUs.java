package com.gracetex.revo.rudhra;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by r3v0 on 22/11/16.
 */

public class AboutUs extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);

        final TextView us = (TextView) findViewById(R.id.tvus);
        final TextView ek = (TextView) findViewById(R.id.tvekathva);
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");
        Typeface custom_font6 = Typeface.createFromAsset(getAssets(),  "fonts/MagmaWave_Caps.otf");
        us.setTypeface(custom_font3);
        ek.setTypeface(custom_font3);

        Button git = (Button)(findViewById(R.id.prjctbtn));
        git.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://github.com/abhi-r3v0/Rudhra";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

}