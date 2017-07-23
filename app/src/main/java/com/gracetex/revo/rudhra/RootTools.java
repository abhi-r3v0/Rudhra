package com.gracetex.revo.rudhra;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class RootTools extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_tools);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(),  "fonts/SR.otf");

        final TextView tvtools = (TextView) findViewById(R.id.textView5);
        tvtools.setTypeface(custom_font2);

        final TextView tvtool1 = (TextView) findViewById(R.id.textView17);
        tvtool1.setTypeface(custom_font7);
        final TextView tvtool2 = (TextView) findViewById(R.id.textView16);
        tvtool2.setTypeface(custom_font7);
        final TextView tvtool3 = (TextView) findViewById(R.id.textView18);
        tvtool3.setTypeface(custom_font7);
        final TextView tvtool4 = (TextView) findViewById(R.id.textView19);
        tvtool4.setTypeface(custom_font7);
        final TextView tvtool5 = (TextView) findViewById(R.id.textView15);
        tvtool5.setTypeface(custom_font7);
        final TextView tvtool6 = (TextView) findViewById(R.id.textView11);
        tvtool6.setTypeface(custom_font7);

        Button adblock = (Button)(findViewById(R.id.adblockbtn));
        adblock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://adblockplus.org/android-install";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }

        });

        Button greenify = (Button) (findViewById(R.id.greenifybtn));
        greenify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String url = "https://play.google.com/store/apps/details?id=com.oasisfeng.greenify";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button blocker = (Button) (findViewById(R.id.blockerbtn));
        blocker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://play.google.com/store/apps/details?id=com.netqin.mm";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

    }

}
