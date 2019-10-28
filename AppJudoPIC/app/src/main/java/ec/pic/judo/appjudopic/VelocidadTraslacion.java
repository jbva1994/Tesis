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

public class VelocidadTraslacion extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    private LineChart mChart5;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidad_traslacion);

        mChart5 = findViewById(R.id.chart5);
        mChart5.animate();
        mChart5.setTouchEnabled(true);
        mChart5.setPinchZoom(true);
        mChart5.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart5);
        mChart5.setMarker(mv);

        renderData();
    }
    public void renderData() {

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
        List<String> labels = new ArrayList<>(Arrays.asList("","Pique 30m", "Pique 50m", "Pique 100m"));
        xAxis5.setValueFormatter(new IndexAxisValueFormatter(labels));
        mChart5.animateXY(2000,2000);
        YAxis leftAxis5 = mChart5.getAxisLeft();
        leftAxis5.removeAllLimitLines();
        leftAxis5.setAxisMaximum(25f);
        leftAxis5.setAxisMinimum(0f);
        leftAxis5.enableGridDashedLine(10f, 10f, 0f);
        leftAxis5.setDrawZeroLine(false);
        leftAxis5.setDrawLimitLinesBehindData(false);
        mChart5.getAxisRight().setEnabled(false);

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
            miTest.setPique_30m(jsonObject.optString("pique_30m"));
            miTest.setPique_50m(jsonObject.optString("pique_50m"));
            miTest.setPique_100m(jsonObject.optString("pique_100m"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Float pique_30m = (Float.parseFloat(miTest.getPique_30m()));
        Float pique_50m = (Float.parseFloat(miTest.getPique_50m()));
        Float pique_100m = (Float.parseFloat(miTest.getPique_100m()));


        ArrayList<Entry> values9 = new ArrayList<Entry>();
        values9.add(new Entry(1, 8));
        values9.add(new Entry(2, 11));
        values9.add(new Entry(3, 20));

        ArrayList<Entry> values10 = new ArrayList<Entry>();
        values10.add(new Entry(1, pique_30m));
        values10.add(new Entry(2, pique_50m));
        values10.add(new Entry(3, pique_100m));

        LineDataSet set9;
        LineDataSet set10;
        if (mChart5.getData() != null && mChart5.getData().getDataSetCount() > 0) {
            set9 = (LineDataSet) mChart5.getData().getDataSetByIndex(0);
            set9.setValues(values9);
            set10 = (LineDataSet) mChart5.getData().getDataSetByIndex(0);
            set10.setValues(values10);
            mChart5.getData().notifyDataChanged();
            mChart5.notifyDataSetChanged();
        } else {
            set9 = new LineDataSet(values9, "Óptimo");
            set10 = new LineDataSet(values10, "Test Pedagógico");
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
