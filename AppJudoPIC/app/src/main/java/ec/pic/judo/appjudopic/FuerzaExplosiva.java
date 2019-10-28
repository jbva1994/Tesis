package ec.pic.judo.appjudopic;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.Utils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.pic.judo.appjudopic.modelo.Test;
import ec.pic.judo.appjudopic.modelo.VolleySingleton;

public class FuerzaExplosiva extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private LineChart mChart2;
    JsonObjectRequest jsonObjectRequest;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza_explosiva);

        mChart2 = findViewById(R.id.chart2);
        mChart2.animate();
        mChart2.setTouchEnabled(true);
        mChart2.setPinchZoom(true);
        mChart2.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart2);
        mChart2.setMarker(mv);

        renderData();
    }

    public void renderData() {
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
        List<String> labels = new ArrayList<>(Arrays.asList("","Pecho", "Abdomen", "Cunclilla"));
        xAxis2.setValueFormatter(new IndexAxisValueFormatter(labels));
        mChart2.animateXY(2000,2000);
        YAxis leftAxis2 = mChart2.getAxisLeft();
        leftAxis2.removeAllLimitLines();
        leftAxis2.setAxisMaximum(75f);
        leftAxis2.setAxisMinimum(0f);
        leftAxis2.enableGridDashedLine(10f, 10f, 0f);
        leftAxis2.setDrawZeroLine(false);
        leftAxis2.setDrawLimitLinesBehindData(false);
        mChart2.getAxisRight().setEnabled(false);

        cargarWebService();
    }

    private void cargarWebService() {

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url= "http://10.119.30.205/judopic/estadisticas_deportista.php?usuario="+user;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo Consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }


    public void onResponse(JSONObject response) {
        Test miTest = new Test();
        JSONArray json = response.optJSONArray("testpedagogico");
        JSONObject jsonObject = null;

        try {
            jsonObject = json.getJSONObject(0);
            miTest.setPecho(jsonObject.optString("pecho"));
            miTest.setAbdomen(jsonObject.optString("abdomen"));
            miTest.setCunclilla(jsonObject.optString("cunclilla"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Integer pecho = (Integer.parseInt(miTest.getPecho()));
        Integer abdomen = (Integer.parseInt(miTest.getAbdomen()));
        Integer cunclilla = (Integer.parseInt(miTest.getCunclilla()));


        ArrayList<Entry> values3 = new ArrayList<Entry>();
        values3.add(new Entry(1, 25));
        values3.add(new Entry(2, 25));
        values3.add(new Entry(3, 25));

        ArrayList<Entry> values4 = new ArrayList<Entry>();
        values4.add(new Entry(1, pecho));
        values4.add(new Entry(2, abdomen));
        values4.add(new Entry(3, cunclilla));

        LineDataSet set3;
        LineDataSet set4;
        if (mChart2.getData() != null && mChart2.getData().getDataSetCount() > 0) {
            set3 = (LineDataSet) mChart2.getData().getDataSetByIndex(0);
            set3.setValues(values3);
            set4 = (LineDataSet) mChart2.getData().getDataSetByIndex(0);
            set4.setValues(values4);
            mChart2.getData().notifyDataChanged();
            mChart2.notifyDataSetChanged();
        } else {
            set3 = new LineDataSet(values3, "Óptimo");
            set4 = new LineDataSet(values4, "Test Pedagógico");
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

    }

}
