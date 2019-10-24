package ec.pic.judo.appjudopic;

import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import java.util.ArrayList;

public class Estadisticas extends AppCompatActivity {

    private LineChart mChart;
    private LineChart mChart2;
    private LineChart mChart3;
    private LineChart mChart4;
    private LineChart mChart5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estadisticas);
        mChart = findViewById(R.id.chart);
        mChart.animate();
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        mChart.getDescription().setEnabled(false);

        mChart2 = findViewById(R.id.chart2);
        mChart2.animate();
        mChart2.setTouchEnabled(true);
        mChart2.setPinchZoom(true);
        mChart2.getDescription().setEnabled(false);

        mChart3 = findViewById(R.id.chart3);
        mChart3.animate();
        mChart3.setTouchEnabled(true);
        mChart3.setPinchZoom(true);
        mChart3.getDescription().setEnabled(false);

        mChart4 = findViewById(R.id.chart4);
        mChart4.animate();
        mChart4.setTouchEnabled(true);
        mChart4.setPinchZoom(true);
        mChart4.getDescription().setEnabled(false);

        mChart5 = findViewById(R.id.chart5);
        mChart5.animate();
        mChart5.setTouchEnabled(true);
        mChart5.setPinchZoom(true);
        mChart5.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);
        mv.setChartView(mChart2);
        mChart2.setMarker(mv);
        mv.setChartView(mChart3);
        mChart3.setMarker(mv);
        mv.setChartView(mChart4);
        mChart4.setMarker(mv);
        mv.setChartView(mChart5);
        mChart5.setMarker(mv);
        renderData();
    }

    //String [] label = new String[]{"Barras","Paralelas","Cabos"};

    public void renderData() {
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(4f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawLimitLinesBehindData(true);
        //xAxis.setValueFormatter(new MyValueFormatter(label));
        xAxis.setGranularity(1f);
        //xAxis.setPosition(XAxis.XAxisPosition.BOTH_SIDED);
        mChart.animateXY(2000,2000);
        YAxis leftAxis = mChart.getAxisLeft();
        //leftAxis.removeAllLimitLines();
        //leftAxis.addLimitLine(llXAxis);
        leftAxis.setAxisMaximum(55f);
        leftAxis.setAxisMinimum(0f);
        leftAxis.enableGridDashedLine(10f, 10f, 0f);
        leftAxis.setDrawZeroLine(false);
        leftAxis.setDrawLimitLinesBehindData(false);
        mChart.getAxisRight().setEnabled(false);


        LimitLine llXAxis2 = new LimitLine(1f, "Index 4");
        llXAxis2.setLineWidth(4f);
        llXAxis2.enableDashedLine(10f, 10f, 0f);
        llXAxis2.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        llXAxis2.setTextSize(15f);
        XAxis xAxis2 = mChart2.getXAxis();
        xAxis2.enableGridDashedLine(10f, 10f, 0f);
        xAxis2.setAxisMaximum(4f);
        xAxis2.setAxisMinimum(0f);
        xAxis2.setDrawLimitLinesBehindData(true);
        xAxis2.setGranularity(1f);
        mChart2.animateXY(2000,2000);
        YAxis leftAxis2 = mChart2.getAxisLeft();
        leftAxis2.removeAllLimitLines();
        leftAxis2.setAxisMaximum(75f);
        leftAxis2.setAxisMinimum(0f);
        leftAxis2.enableGridDashedLine(10f, 10f, 0f);
        leftAxis2.setDrawZeroLine(false);
        leftAxis2.setDrawLimitLinesBehindData(false);
        mChart2.getAxisRight().setEnabled(false);


        LimitLine llXAxis3 = new LimitLine(1f, "Index 4");
        llXAxis3.setLineWidth(4f);
        llXAxis3.enableDashedLine(10f, 10f, 0f);
        llXAxis3.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        llXAxis3.setTextSize(15f);
        XAxis xAxis3 = mChart3.getXAxis();
        xAxis3.enableGridDashedLine(10f, 10f, 0f);
        xAxis3.setAxisMaximum(4f);
        xAxis3.setAxisMinimum(0f);
        xAxis3.setDrawLimitLinesBehindData(true);
        xAxis3.setGranularity(1f);
        mChart3.animateXY(2000,2000);
        YAxis leftAxis3 = mChart3.getAxisLeft();
        leftAxis3.removeAllLimitLines();
        leftAxis3.setAxisMaximum(160f);
        leftAxis3.setAxisMinimum(0f);
        leftAxis3.enableGridDashedLine(10f, 10f, 0f);
        leftAxis3.setDrawZeroLine(false);
        leftAxis3.setDrawLimitLinesBehindData(false);
        mChart3.getAxisRight().setEnabled(false);


        LimitLine llXAxis4 = new LimitLine(1f, "Index 4");
        llXAxis4.setLineWidth(4f);
        llXAxis4.enableDashedLine(10f, 10f, 0f);
        llXAxis4.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        llXAxis4.setTextSize(15f);
        XAxis xAxis4 = mChart4.getXAxis();
        xAxis4.enableGridDashedLine(10f, 10f, 0f);
        xAxis4.setAxisMaximum(4f);
        xAxis4.setAxisMinimum(0f);
        xAxis4.setDrawLimitLinesBehindData(true);
        xAxis4.setGranularity(1f);
        mChart4.animateXY(2000,2000);
        YAxis leftAxis4 = mChart4.getAxisLeft();
        leftAxis4.removeAllLimitLines();
        leftAxis4.setAxisMaximum(60f);
        leftAxis4.setAxisMinimum(0f);
        leftAxis4.enableGridDashedLine(10f, 10f, 0f);
        leftAxis4.setDrawZeroLine(false);
        leftAxis4.setDrawLimitLinesBehindData(false);
        mChart4.getAxisRight().setEnabled(false);


        LimitLine llXAxis5 = new LimitLine(1f, "Index 4");
        llXAxis5.setLineWidth(4f);
        llXAxis5.enableDashedLine(10f, 10f, 0f);
        llXAxis5.setLabelPosition(LimitLine.LimitLabelPosition.RIGHT_TOP);
        llXAxis5.setTextSize(15f);
        XAxis xAxis5 = mChart5.getXAxis();
        xAxis5.enableGridDashedLine(10f, 10f, 0f);
        xAxis5.setAxisMaximum(4f);
        xAxis5.setAxisMinimum(0f);
        xAxis5.setDrawLimitLinesBehindData(true);
        xAxis5.setGranularity(1f);
        mChart5.animateXY(2000,2000);
        YAxis leftAxis5 = mChart5.getAxisLeft();
        leftAxis5.removeAllLimitLines();
        leftAxis5.setAxisMaximum(20f);
        leftAxis5.setAxisMinimum(0f);
        leftAxis5.enableGridDashedLine(10f, 10f, 0f);
        leftAxis5.setDrawZeroLine(false);
        leftAxis5.setDrawLimitLinesBehindData(false);
        mChart5.getAxisRight().setEnabled(false);

        setData();

    }

    private ArrayList<Entry> datavalues1(){
        ArrayList<Entry> values1 = new ArrayList<Entry>();
        values1.add(new Entry(1, 35));
        values1.add(new Entry(2, 40));
        values1.add(new Entry(3, 5));
        return values1;
    }

    private  ArrayList<Entry> datavalues2(){
        ArrayList<Entry> values2 = new ArrayList<Entry>();
        values2.add(new Entry(1, 25));
        values2.add(new Entry(2, 20));
        values2.add(new Entry(3, 2));
        return values2;
    }

    private void setData() {
        LineDataSet set1;
        LineDataSet set2;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(datavalues1());
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2.setValues(datavalues2());
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(datavalues1(), "Optimo");
            set2 = new LineDataSet(datavalues2(), "Test Padagogico");
            set1.setDrawIcons(false);
            set1.enableDashedLine(10f, 5f, 0f);
            set1.enableDashedHighlightLine(10f, 5f, 0f);
            set1.setColor(Color.RED);
            set1.setCircleColor(Color.DKGRAY);
            set1.setLineWidth(2f);
            set1.setCircleRadius(4f);
            set1.setDrawCircleHole(false);
            set1.setValueTextSize(12f);
            set1.setDrawFilled(true);
            set1.setFormLineWidth(2f);
            set1.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set1.setFormSize(15.f);

            set2.setDrawIcons(false);
            set2.enableDashedLine(10f, 5f, 0f);
            set2.enableDashedHighlightLine(10f, 5f, 0f);
            set2.setColor(Color.YELLOW);
            set2.setCircleColor(Color.DKGRAY);
            set2.setLineWidth(2f);
            set2.setCircleRadius(4f);
            set2.setDrawCircleHole(false);
            set2.setValueTextSize(12f);
            set2.setDrawFilled(true);
            set2.setFormLineWidth(2f);
            set2.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set2.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set1.setFillDrawable(drawable);
                Drawable drawable2 = ContextCompat.getDrawable(this, R.drawable.fade_yellow);
                set2.setFillDrawable(drawable2);
            } else {
                set1.setFillColor(Color.RED);
                set2.setFillColor(Color.YELLOW);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set1);
            dataSets.add(set2);
            LineData data = new LineData(dataSets);
            mChart.setData(data);
        }


        LineDataSet set3;
        LineDataSet set4;
        if (mChart2.getData() != null && mChart2.getData().getDataSetCount() > 0) {
            set3 = (LineDataSet) mChart2.getData().getDataSetByIndex(0);
            set3.setValues(datavalues1());
            set4 = (LineDataSet) mChart2.getData().getDataSetByIndex(0);
            set4.setValues(datavalues2());
            mChart2.getData().notifyDataChanged();
            mChart2.notifyDataSetChanged();
        } else {
            set3 = new LineDataSet(datavalues1(), "Optimo");
            set4 = new LineDataSet(datavalues2(), "Test Padagogico");
            set3.setDrawIcons(false);
            set3.enableDashedLine(10f, 5f, 0f);
            set3.enableDashedHighlightLine(10f, 5f, 0f);
            set3.setColor(Color.RED);
            set3.setCircleColor(Color.DKGRAY);
            set3.setLineWidth(2f);
            set3.setCircleRadius(4f);
            set3.setDrawCircleHole(false);
            set3.setValueTextSize(12f);
            set3.setDrawFilled(true);
            set3.setFormLineWidth(2f);
            set3.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set3.setFormSize(15.f);

            set4.setDrawIcons(false);
            set4.enableDashedLine(10f, 5f, 0f);
            set4.enableDashedHighlightLine(10f, 5f, 0f);
            set4.setColor(Color.GRAY);
            set4.setCircleColor(Color.DKGRAY);
            set4.setLineWidth(2f);
            set4.setCircleRadius(4f);
            set4.setDrawCircleHole(false);
            set4.setValueTextSize(12f);
            set4.setDrawFilled(true);
            set4.setFormLineWidth(2f);
            set4.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set4.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable3 = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set3.setFillDrawable(drawable3);
                Drawable drawable4 = ContextCompat.getDrawable(this, R.drawable.fade_dark);
                set4.setFillDrawable(drawable4);
            } else {
                set3.setFillColor(Color.RED);
                set4.setFillColor(Color.GRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set3);
            dataSets.add(set4);
            LineData data = new LineData(dataSets);
            mChart2.setData(data);
        }


        LineDataSet set5;
        LineDataSet set6;
        if (mChart3.getData() != null && mChart3.getData().getDataSetCount() > 0) {
            set5 = (LineDataSet) mChart3.getData().getDataSetByIndex(0);
            set5.setValues(datavalues1());
            set6 = (LineDataSet) mChart3.getData().getDataSetByIndex(0);
            set6.setValues(datavalues2());
            mChart3.getData().notifyDataChanged();
            mChart3.notifyDataSetChanged();
        } else {
            set5 = new LineDataSet(datavalues1(), "Optimo");
            set6 = new LineDataSet(datavalues2(), "Test Padagogico");
            set5.setDrawIcons(false);
            set5.enableDashedLine(10f, 5f, 0f);
            set5.enableDashedHighlightLine(10f, 5f, 0f);
            set5.setColor(Color.RED);
            set5.setCircleColor(Color.DKGRAY);
            set5.setLineWidth(2f);
            set5.setCircleRadius(4f);
            set5.setDrawCircleHole(false);
            set5.setValueTextSize(12f);
            set5.setDrawFilled(true);
            set5.setFormLineWidth(2f);
            set5.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set5.setFormSize(15.f);

            set6.setDrawIcons(false);
            set6.enableDashedLine(10f, 5f, 0f);
            set6.enableDashedHighlightLine(10f, 5f, 0f);
            set6.setColor(Color.WHITE);
            set6.setCircleColor(Color.DKGRAY);
            set6.setLineWidth(2f);
            set6.setCircleRadius(4f);
            set6.setDrawCircleHole(false);
            set6.setValueTextSize(12f);
            set6.setDrawFilled(true);
            set6.setFormLineWidth(2f);
            set6.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set6.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable5 = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set5.setFillDrawable(drawable5);
                Drawable drawable6 = ContextCompat.getDrawable(this, R.drawable.fade_pink);
                set6.setFillDrawable(drawable6);
            } else {
                set5.setFillColor(Color.RED);
                set6.setFillColor(Color.YELLOW);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set5);
            dataSets.add(set6);
            LineData data = new LineData(dataSets);
            mChart3.setData(data);
        }


        LineDataSet set7;
        LineDataSet set8;
        if (mChart4.getData() != null && mChart4.getData().getDataSetCount() > 0) {
            set7 = (LineDataSet) mChart4.getData().getDataSetByIndex(0);
            set7.setValues(datavalues1());
            set8 = (LineDataSet) mChart4.getData().getDataSetByIndex(0);
            set8.setValues(datavalues2());
            mChart4.getData().notifyDataChanged();
            mChart4.notifyDataSetChanged();
        } else {
            set7 = new LineDataSet(datavalues1(), "Optimo");
            set8 = new LineDataSet(datavalues2(), "Test Padagogico");
            set7.setDrawIcons(false);
            set7.enableDashedLine(10f, 5f, 0f);
            set7.enableDashedHighlightLine(10f, 5f, 0f);
            set7.setColor(Color.RED);
            set7.setCircleColor(Color.DKGRAY);
            set7.setLineWidth(2f);
            set7.setCircleRadius(4f);
            set7.setDrawCircleHole(false);
            set7.setValueTextSize(12f);
            set7.setDrawFilled(true);
            set7.setFormLineWidth(2f);
            set7.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set7.setFormSize(15.f);

            set8.setDrawIcons(false);
            set8.enableDashedLine(10f, 5f, 0f);
            set8.enableDashedHighlightLine(10f, 5f, 0f);
            set8.setColor(Color.GRAY);
            set8.setCircleColor(Color.DKGRAY);
            set8.setLineWidth(2f);
            set8.setCircleRadius(4f);
            set8.setDrawCircleHole(false);
            set8.setValueTextSize(12f);
            set8.setDrawFilled(true);
            set8.setFormLineWidth(2f);
            set8.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set8.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable7 = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set7.setFillDrawable(drawable7);
                Drawable drawable8 = ContextCompat.getDrawable(this, R.drawable.fade_dark);
                set8.setFillDrawable(drawable8);
            } else {
                set7.setFillColor(Color.RED);
                set8.setFillColor(Color.GRAY);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set7);
            dataSets.add(set8);
            LineData data = new LineData(dataSets);
            mChart4.setData(data);
        }


        LineDataSet set9;
        LineDataSet set10;
        if (mChart5.getData() != null && mChart5.getData().getDataSetCount() > 0) {
            set9 = (LineDataSet) mChart5.getData().getDataSetByIndex(0);
            set9.setValues(datavalues1());
            set10 = (LineDataSet) mChart5.getData().getDataSetByIndex(0);
            set10.setValues(datavalues2());
            mChart5.getData().notifyDataChanged();
            mChart5.notifyDataSetChanged();
        } else {
            set9 = new LineDataSet(datavalues1(), "Optimo");
            set10 = new LineDataSet(datavalues2(), "Test Padagogico");
            set9.setDrawIcons(false);
            set9.enableDashedLine(10f, 5f, 0f);
            set9.enableDashedHighlightLine(10f, 5f, 0f);
            set9.setColor(Color.RED);
            set9.setCircleColor(Color.DKGRAY);
            set9.setLineWidth(2f);
            set9.setCircleRadius(4f);
            set9.setDrawCircleHole(false);
            set9.setValueTextSize(12f);
            set9.setDrawFilled(true);
            set9.setFormLineWidth(2f);
            set9.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set9.setFormSize(15.f);

            set10.setDrawIcons(false);
            set10.enableDashedLine(10f, 5f, 0f);
            set10.enableDashedHighlightLine(10f, 5f, 0f);
            set10.setColor(Color.YELLOW);
            set10.setCircleColor(Color.DKGRAY);
            set10.setLineWidth(2f);
            set10.setCircleRadius(4f);
            set10.setDrawCircleHole(false);
            set10.setValueTextSize(12f);
            set10.setDrawFilled(true);
            set10.setFormLineWidth(2f);
            set10.setFormLineDashEffect(new DashPathEffect(new float[]{10f, 5f}, 0f));
            set10.setFormSize(15.f);

            if (Utils.getSDKInt() >= 18) {
                Drawable drawable9 = ContextCompat.getDrawable(this, R.drawable.fade_red);
                set9.setFillDrawable(drawable9);
                Drawable drawable10 = ContextCompat.getDrawable(this, R.drawable.fade_yellow);
                set10.setFillDrawable(drawable10);
            } else {
                set9.setFillColor(Color.RED);
                set10.setFillColor(Color.YELLOW);
            }
            ArrayList<ILineDataSet> dataSets = new ArrayList<>();
            dataSets.add(set9);
            dataSets.add(set10);
            LineData data = new LineData(dataSets);
            mChart5.setData(data);
        }


    }
}
