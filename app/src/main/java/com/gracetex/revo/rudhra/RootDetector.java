package com.gracetex.revo.rudhra;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;

public class RootDetector extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_root_detector);

        Typeface custom_font2 = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");
        Typeface custom_font7 = Typeface.createFromAsset(getAssets(),  "fonts/SR.otf");

        final TextView tvroot1 = (TextView) findViewById(R.id.textView3);
        tvroot1.setTypeface(custom_font2);
        final TextView tvroot2 = (TextView) findViewById(R.id.roottv);
        tvroot2.setTypeface(custom_font7);

        ToggleButton rootb = (ToggleButton) (findViewById(R.id.Roottb));
        ToggleButton hooktb = (ToggleButton) (findViewById(R.id.hooktb));

        Button rtools = (Button) (findViewById(R.id.broottools));
        Button unroot = (Button)(findViewById(R.id.bunroot));
        TextView roottv = (TextView) (findViewById(R.id.roottv));

        TextView hooktv = (TextView) (findViewById(R.id.Syshook));
        hooktv.setTypeface(custom_font2);
        TextView syshooktv = (TextView) (findViewById(R.id.tvSyshook));
        syshooktv.setTypeface(custom_font7);


        String yesdesc = "Your device has root access. Rooted devices allow lot of customizations but as well pose serious security issues by allowing apps to access lot of resources from the phone. You can choose to unroot the device or install security-enhancing tools. Here is a list : ";
        String nodesc = "No root access detected. Your device is a little more safer!";

        String hookyes = "Your device has been hooked. This means that user applications could act as privileged applications and gain access to system resources and settings.";
        String hookno = "No system hook found. Your device is fully under your control.";

        boolean isHooked = FindHook();

        if (isHooked == true){

            hooktb.setChecked(true);
            syshooktv.setText(hookyes);
        }

        else{
            hooktb.setChecked(false);
            syshooktv.setText(hookno);
        }

        boolean isRooted = FindRoot();

        if (isRooted == true) {


            rootb.setChecked(true);
            roottv.setText(yesdesc);
            rtools.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent i = new Intent(RootDetector.this, RootTools.class);
                    RootDetector.this.startActivity(i);
                }
            });

            unroot.setOnClickListener(new View.OnClickListener()
            {
                public void onClick(View v)
                {
                    Intent i = new Intent(RootDetector.this, Unroot.class);
                    RootDetector.this.startActivity(i);
                    finish();
                }

            });

            rtools.setVisibility(View.VISIBLE);
            unroot.setVisibility(View.VISIBLE);

        } else {
            rootb.setChecked(false);
            roottv.setText(nodesc);
        }
    }

    public boolean FindRoot() {
        return TestKeysChecker() || SUBinFinder() || SURuntimeCheck();
    }

    public boolean TestKeysChecker() {
        String keys = Build.TAGS;
        if (keys != null && keys.contains("test-keys")) {

            Toast.makeText(this, " Test-keys found ", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(this, "Test-keys not found", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    //Check within the paths for the existance of su binary
    public boolean SUBinFinder() {
        String[] paths = {"/system/app/Superuser.apk",
                "/system/priv-app/Superuser.apk",
                "/system/priv-app/superuser.apk",
                "/system/app/superuser.apk",
                "/sbin/su",
                "/system/bin/su",
                "/system/xbin/su",
                "/data/local/xbin/su",
                "/data/local/bin/su",
                "/system/sd/xbin/su",
                "/system/bin/failsafe/su",
                "/data/local/su",
                "/su/bin/su"
        };

        int dircount = 0;



        for (String path : paths) {

            if (new File(path).exists()) {
                dircount++;
            }
        }

        if (dircount > 0) {

            Toast.makeText(this, " SU binary found ", Toast.LENGTH_SHORT).show();
            return true;

        } else {

            Toast.makeText(this, "SU binary not found", Toast.LENGTH_SHORT).show();
            return false;
        }
    }


    //Executing the su binary
    public boolean SURuntimeCheck() {
        Process process = null;
        try {
            process = Runtime.getRuntime().exec(new String[]{"/system/xbin/which", "su"});
            BufferedReader in = new BufferedReader(new InputStreamReader(process.getInputStream()));
            if (in.readLine() != null) {
                Toast.makeText(this, " SU execution successfull ! ", Toast.LENGTH_SHORT).show();
                return true;
            }
            Toast.makeText(this, "SU execution failed", Toast.LENGTH_SHORT).show();


        } catch (Throwable t) {

        } finally {
            if (process != null) process.destroy();
        }
        return false;
    }



    /*
    Finding system hooks.
    Two processes used.
     */

    public boolean FindHook() {return HookJarFinder();}


    public boolean HookJarFinder(){
        String hook = "/data/data/de.robv.android.xposed.installer/bin/XposedBridge.jar";
        if (new File(hook).exists()){
            Toast.makeText(this, "Xposed Bridge found. System has been hooked!", Toast.LENGTH_SHORT).show();
            return true;
        }
        else {
            Toast.makeText(this, "Xposed Bridge not found. System not hooked.", Toast.LENGTH_SHORT).show();
            return false;
        }
    }
}

