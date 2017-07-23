package com.gracetex.revo.rudhra;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Unroot extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_unroot);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OstrichSans-Bold.otf");
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(),  "fonts/SR.otf");

        final TextView unroot1 = (TextView) findViewById(R.id.textView20);
        unroot1.setTypeface(custom_font2);
        final TextView unroot2 = (TextView) findViewById(R.id.textView21);
        unroot2.setTypeface(custom_font7);

        Button unroot = (Button)(findViewById(R.id.unrtbtn));
        unroot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "http://www.wikihow.com/Unroot-Android";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });

        Button learn = (Button)(findViewById(R.id.learnbtn));
        learn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = "https://en.wikipedia.org/wiki/Rooting_(Android_OS)";
                Intent i = new Intent(Intent.ACTION_VIEW);
                i.setData(Uri.parse(url));
                startActivity(i);
            }
        });
    }
}
