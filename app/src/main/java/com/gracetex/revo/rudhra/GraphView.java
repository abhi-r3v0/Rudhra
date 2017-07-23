package com.gracetex.revo.rudhra;

import android.app.ActivityManager;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ColorTemplate;

/**
 * Created by PSNAppZ on 7/23/17.
 */

public class GraphView extends AppCompatActivity{
    private LineChart mChart;
    private LineChart rChart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.graph_layout);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/OdinBold.otf");
        final TextView cpuLoad = (TextView) findViewById(R.id.textView);
        final TextView ramUsage = (TextView) findViewById(R.id.textView2);
        cpuLoad.setTypeface(custom_font);
        cpuLoad.setTextColor(Color.WHITE);
        ramUsage.setTypeface(custom_font);
        ramUsage.setTextColor(Color.WHITE);

        //REAL TIME CHART SETTINGS
        mChart = (LineChart) findViewById(R.id.cpu_chart);
        rChart = (LineChart) findViewById(R.id.ram_chart);

        LineData data = new LineData();
        data.setValueTextColor(Color.WHITE);

        LineData data2 = new LineData();
        data2.setValueTextColor(Color.WHITE);

        // add empty data
        mChart.setData(data);
        mChart.getLegend().setEnabled(false);
        mChart.getDescription().setText("CPU Load");
        mChart.getDescription().setTextColor(Color.WHITE);
        mChart.animateXY(1000, 1000); // animate horizontal and vertical 3000 milliseconds

        // add empty data
        rChart.setData(data2);
        rChart.getLegend().setEnabled(false);
        rChart.getDescription().setText("RAM Usage Graph");
        rChart.getDescription().setTextColor(Color.WHITE);
        rChart.animateXY(1000, 1000); // animate horizontal and vertical 3000 milliseconds

        XAxis xl1 = rChart.getXAxis();
        xl1.setTextColor(Color.WHITE);
        xl1.setDrawGridLines(false);
        xl1.setAvoidFirstLastClipping(true);
        xl1.setEnabled(false);

        YAxis leftAxis1 = rChart.getAxisLeft();
        leftAxis1.setTextColor(Color.WHITE);
        leftAxis1.setAxisMaximum(currentMem()[0]);
        leftAxis1.setAxisMinimum(0f);
        leftAxis1.setDrawGridLines(true);

        YAxis rightAxis1 = rChart.getAxisRight();
        rightAxis1.setEnabled(false);

        XAxis xl = mChart.getXAxis();
        xl.setTextColor(Color.WHITE);
        xl.setDrawGridLines(false);
        xl.setAvoidFirstLastClipping(true);
        xl.setEnabled(false);

        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.setTextColor(Color.WHITE);
        leftAxis.setAxisMaximum(100f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.setDrawGridLines(true);

        YAxis rightAxis = mChart.getAxisRight();
        rightAxis.setEnabled(false);


        final android.os.Handler handler = new android.os.Handler();

        final Runnable r = new Runnable()
        {
            public void run()
            {
                LineData data = mChart.getData();
                LineData data1 = rChart.getData();
                handler.postDelayed(this,800);


                if (data != null) {

                    ILineDataSet set = data.getDataSetByIndex(0);

                    // set.addEntry(...); // can be called as well

                    if (set == null) {
                        set = createSet();
                        data.addDataSet(set);
                    }

                    data.addEntry(new Entry(set.getEntryCount(), restrictPercentage(MainActivity.readCPUUsage()*100)), 0);
                    data.notifyDataChanged();

                    // let the chart know it's data has changed
                    mChart.notifyDataSetChanged();

                    // limit the number of visible entries
                    mChart.setVisibleXRangeMaximum(5);
                    // mChart.setVisibleYRange(30, AxisDependency.LEFT);

                    // move to the latest entry
                    mChart.moveViewToX(data.getEntryCount());

                    // this automatically refreshes the chart (calls invalidate())
                    // mChart.moveViewTo(data.getXValCount()-7, 55f,
                    // AxisDependency.LEFT);
                }

                if (data1 != null) {

                    ILineDataSet set1 = data1.getDataSetByIndex(0);

                    if (set1 == null) {
                        set1 = createSet1();
                        data1.addDataSet(set1);
                    }

                    data1.addEntry(new Entry(set1.getEntryCount(),currentMem()[1]), 0);
                    data1.notifyDataChanged();

                    // let the chart know it's data has changed
                    rChart.notifyDataSetChanged();

                    // limit the number of visible entries
                    rChart.setVisibleXRangeMaximum(5);
                    // mChart.setVisibleYRange(30, AxisDependency.LEFT);

                    // move to the latest entry
                    rChart.moveViewToX(data1.getEntryCount());
                }
            }
        };

        handler.postDelayed(r, 800);
    }
    private LineDataSet createSet() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setMode(LineDataSet.Mode.CUBIC_BEZIER);
        set.setColor(Color.parseColor("#c25e00"));
        set.setDrawValues(false);
        set.setDrawCircles(false);
        set.setFillColor(Color.parseColor("#ffbd45"));
        set.setDrawFilled(true);
        set.setFillAlpha(255);
        return set;
    }

    private LineDataSet createSet1() {

        LineDataSet set = new LineDataSet(null, "Dynamic Data");
        set.setAxisDependency(YAxis.AxisDependency.LEFT);
        set.setColor(Color.RED);
        set.setCircleColor(Color.WHITE);
        set.setCircleRadius(4f);
        set.setFillAlpha(65);
        set.setFillColor(ColorTemplate.getHoloBlue());
        set.setHighLightColor(Color.rgb(244, 117, 117));
        set.setValueTextColor(Color.WHITE);
        set.setValueTextSize(9f);
        set.setDrawValues(false);
        return set;
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

    private float restrictPercentage(float percentage) {
        if (percentage > 100)
            return 100;
        else if (percentage < 0)
            return 0;
        else return percentage;
    }
}
