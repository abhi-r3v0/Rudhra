package com.gracetex.revo.rudhra;

import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.content.pm.ProviderInfo;
import android.content.pm.ServiceInfo;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PermissionScanner extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_permission_scanner);

        if(getSupportActionBar()!= null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        Typeface custom_font3 = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");

        ArrayList<String> VulnPerms = new ArrayList<String>();

        VulnPerms.add("com.android.launcher.permission.WRITE_SETTINGS");
        VulnPerms.add("android.permission.READ_CALL_LOG");
        VulnPerms.add("android.permission.DISABLE_KEYGUARD");
        VulnPerms.add("android.permission.READ_CONTACTS");
        VulnPerms.add("android.permission.WRITE_EXTERNAL_STORAGE");
        VulnPerms.add("android.permission.USE_CREDENTIALS");
        VulnPerms.add("android.permission.MANAGE_ACCOUNTS");
        VulnPerms.add("android.permission.INTERNET");
        VulnPerms.add("android.permission.RECORD_AUDIO");
        VulnPerms.add("android.permission.READ_SMS");
        VulnPerms.add("android.permission.WRITE_SMS");
        VulnPerms.add("com.google.android.googleapps.permission.ACCESS_GOOGLE_PASSWORD");

        int vulnLength = VulnPerms.size();

        final int N = 100; // total number of textviews to add
        String appname = null;

        final TextView[] myTextViews = new TextView[N]; // create an empty array;

                PackageManager p = this.getPackageManager();
        final List<PackageInfo> appinstall = p.getInstalledPackages(PackageManager.GET_PERMISSIONS | PackageManager.GET_RECEIVERS |
                PackageManager.GET_SERVICES | PackageManager.GET_PROVIDERS);

        for (final PackageInfo pInfo : appinstall) {
            if ( (pInfo.applicationInfo.flags & ApplicationInfo.FLAG_SYSTEM) == 0 ) {
                PermissionInfo[] permission = pInfo.permissions;
                String[] reqPermission = pInfo.requestedPermissions;
                ServiceInfo[] services = pInfo.services;
                ProviderInfo[] providers = pInfo.providers;
                int versionCode = pInfo.versionCode;


                ApplicationInfo applicationInfo = null;
                try {
                    applicationInfo = p.getApplicationInfo(pInfo.packageName, 0);
                } catch (final PackageManager.NameNotFoundException e) {
                }
                if( applicationInfo != null) {
                    appname = (String) p.getApplicationLabel(applicationInfo);
                 }

                LinearLayout layout1 = (LinearLayout) (findViewById(R.id.info));
                final TextView permheader = new TextView(this);
                layout1.addView(permheader);
                permheader.setText(appname);
                permheader.setTypeface(custom_font3);
                permheader.setAllCaps(true);
                permheader.setTextSize(18);
                permheader.isClickable();

                permheader.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent i = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                        i.addCategory(Intent.CATEGORY_DEFAULT);
                        i.setData(Uri.parse("package:" + pInfo.packageName));
                        startActivity(i);
                    }
                });
                permheader.setTextColor(getResources().getColor(R.color.white));

                String [] appNames;

               if (reqPermission != null) {
                   for (int i = 0; i < reqPermission.length; i++) {
                       if (VulnPerms.contains(reqPermission[i])) {
                           LinearLayout layout = (LinearLayout) (findViewById(R.id.info));
                           final TextView PermTextView = new TextView(this);
                           layout.addView(PermTextView);
                           PermTextView.setText("   " + reqPermission[i]);
                           PermTextView.setTextSize(12);
                           PermTextView.setTextColor(getResources().getColor(R.color.white));
                       }

                   }

               }
            }

        }
    }
}
