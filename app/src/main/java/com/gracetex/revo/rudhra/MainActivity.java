package com.gracetex.revo.rudhra;

import android.app.ActivityManager;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public GoogleApiClient client;

    public static float readCPUUsage() {
        try {
            RandomAccessFile reader = new RandomAccessFile("/proc/stat", "r");
            String[] toks = reader.readLine().split(" ");
            long idle1 = Long.parseLong(toks[5]);
            long cpu1 = ((((Long.parseLong(toks[2]) + Long.parseLong(toks[3])) + Long.parseLong(toks[4])) + Long.parseLong(toks[6])) + Long.parseLong(toks[7])) + Long.parseLong(toks[8]);
            try {
                Thread.sleep(360);
            } catch (Exception e) {
            }
            reader.seek(0);
            String load = reader.readLine();
            reader.close();
            toks = load.split(" ");
            long cpu2 = ((((Long.parseLong(toks[2]) + Long.parseLong(toks[3])) + Long.parseLong(toks[4])) + Long.parseLong(toks[6])) + Long.parseLong(toks[7])) + Long.parseLong(toks[8]);
            DecimalFormat decimalFormat = new DecimalFormat("#.0");
            return Float.parseFloat(decimalFormat.format(((float) (cpu2 - cpu1)) / ((float) ((cpu2 + Long.parseLong(toks[5])) - (cpu1 + idle1)))));
        } catch (IOException ex) {
            ex.printStackTrace();
            return 0.0f;
        }
    }

    private float restrictPercentage(float percentage) {
        if (percentage > 100)
            return 100;
        else if (percentage < 0)
            return 0;
        else return percentage;
    }


    public static float getAppUsedMemorySize() {

        float freeSize = 0L;
        float totalSize = 0L;
        float usedSize = -1L;
        try {

            Runtime info = Runtime.getRuntime();
            freeSize = info.freeMemory();
            totalSize = info.totalMemory();
            usedSize = totalSize - freeSize;
        } catch (Exception e) {
            e.printStackTrace();
        }
        DecimalFormat decimalFormat = new DecimalFormat("#.000");
        return Float.parseFloat(decimalFormat.format(usedSize/1024));
    }

    public int [] currentMem() {
        ActivityManager.MemoryInfo mi = new ActivityManager.MemoryInfo();
        ActivityManager activityManager = (ActivityManager) getSystemService(ACTIVITY_SERVICE);
        activityManager.getMemoryInfo(mi);
        int totalMegs = (int) (mi.totalMem / 0x100000L);
        int availableMegs = (int) (mi.availMem / 0x100000L);
        int usedMegs = totalMegs - availableMegs;
        int totalGigs = totalMegs;
        int [] mem = {totalMegs, usedMegs};
        double percentAvail = mi.availMem / (double) mi.totalMem;
        return mem;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);
        Typeface custom_font1 = Typeface.createFromAsset(getAssets(),  "fonts/SourceSansPro-Black.otf");
        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OstrichSans-Bold.otf");
        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");
        Typeface custom_font4 = Typeface.createFromAsset(getAssets(),  "fonts/trench100free.otf");
        Typeface custom_font5 = Typeface.createFromAsset(getAssets(),  "fonts/More.otf");
        Typeface custom_font6 = Typeface.createFromAsset(getAssets(),  "fonts/MagmaWave_Caps.otf");
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(),  "fonts/SR.otf");
        Typeface custom_font8 = Typeface.createFromAsset(getAssets(),  "fonts/bod.otf");

        //Other Components
        final TextView cpuload = (TextView) findViewById(R.id.textView10);
        cpuload.setTypeface(custom_font1);
        final TextView ramusage = (TextView) findViewById(R.id.textView14);
        ramusage.setTypeface(custom_font1);
        final TextView cpu = (TextView)(findViewById(R.id.cputv));
        cpu.setTypeface(custom_font2);
        final TextView ram = (TextView)(findViewById(R.id.ramtv));
        ram.setTypeface(custom_font2);
        final TextView tvw = (TextView) findViewById(R.id.tvwel);
        tvw.setTypeface(custom_font3);


        final android.os.Handler handler = new android.os.Handler();

        final Runnable r = new Runnable()
        {
            public void run()
            {
                    cpu.setText(" "+restrictPercentage(readCPUUsage()*100) +" %");
                    ram.setText(" "+currentMem()[1] +" MB / "+currentMem()[0]+" MB");
                    handler.postDelayed(this,800);


                }


        };

        handler.postDelayed(r, 800);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        /*switch (id){
            case R.id.nav_send:
                startActivity(new Intent(this, AboutUs.class));
                break;
        }return true;
        */
        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_permissions) {
        } else {
            if (id == R.id.nav_settings)
                startActivity(new Intent(this, SettingsScanner.class));

             else if (id == R.id.nav_manage) {
                startActivity(new Intent(this, RootDetector.class));

            } else if (id == R.id.nav_graph) {
                startActivity(new Intent(this, GraphView.class));

            }else {
                if (id == R.id.nav_aboutus) {
                    Intent i = new Intent(this,AboutUs.class);
                    startActivity(i);
                }
            }
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
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
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
