package com.gracetex.revo.rudhra;

import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

public class SettingsScanner extends AppCompatActivity {


    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings_scanner);

        final TextView ss = (TextView) findViewById(R.id.tvvs);
        final TextView adbt = (TextView) findViewById(R.id.ADBTView);
        final TextView usv = (TextView) findViewById(R.id.USourcesView);
        final TextView db = (TextView) findViewById(R.id.DevTView);

        final TextView usd = (TextView) findViewById(R.id.textView7);
        final TextView debd = (TextView) findViewById(R.id.textView8);
        final TextView devd = (TextView) findViewById(R.id.textView12);

        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Black.otf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OstrichSans-Bold.otf");
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/trench100free.otf");
        Typeface custom_font5 = Typeface.createFromAsset(getAssets(),  "fonts/More.otf");
        Typeface custom_font6 = Typeface.createFromAsset(getAssets(),  "fonts/MagmaWave_Caps.otf");
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(),  "fonts/SR.otf");

        ss.setTypeface(custom_font3);
        usv.setTypeface(custom_font7);
        adbt.setTypeface(custom_font7);
        db.setTypeface(custom_font7);

        usd.setTypeface(custom_font7);
        debd.setTypeface(custom_font7);
        devd.setTypeface(custom_font7);

        try {
            checkSettings();
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    public void checkSettings() throws Settings.SettingNotFoundException {

        ToggleButton USs = (ToggleButton) (findViewById(R.id.USt));
        if (Settings.Secure.getInt(getContentResolver(), Settings.Secure.INSTALL_NON_MARKET_APPS, 0) == 1) {
            USs.setChecked(true);
            USs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_SECURITY_SETTINGS), 0);
                }
            });
        } else {
            USs.setChecked(false);
            USs.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_SECURITY_SETTINGS), 0);
                }
            });
        }






        ToggleButton ADBes = (ToggleButton) (findViewById(R.id.ADBt));
        if (Settings.Secure.getInt(getContentResolver(), Settings.Global.ADB_ENABLED, 0) == 1) {
            ADBes.setChecked(true);
            ADBes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS), 0);
                }
            });
        } else {
            ADBes.setChecked(false);
            ADBes.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS), 0);
                }
            });
        }






        ToggleButton DevS = (ToggleButton) (findViewById(R.id.DevB));
        if (Settings.Secure.getInt(getContentResolver(), Settings.Global.DEVELOPMENT_SETTINGS_ENABLED, 0) == 1) {
            DevS.setChecked(true);
            DevS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS), 0);
                }
            });
        } else {
            DevS.setChecked(false);
            DevS.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivityForResult(new Intent(Settings.ACTION_APPLICATION_DEVELOPMENT_SETTINGS), 0);
                }
            });
        }
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("SettingsScanner Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }


    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onResume() {
        super.onResume();
        try {
            checkSettings();
        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
