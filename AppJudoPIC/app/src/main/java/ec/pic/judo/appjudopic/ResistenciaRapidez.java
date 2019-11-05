package ec.pic.judo.appjudopic;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
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
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ec.pic.judo.appjudopic.modelo.Test;
import ec.pic.judo.appjudopic.modelo.TestOptimo;
import ec.pic.judo.appjudopic.modelo.VolleySingleton;

public class ResistenciaRapidez extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, AdapterView.OnItemSelectedListener{

    private LineChart mChart4;
    JsonObjectRequest jsonObjectRequest;
    TextView estUshikomi, estNagekomi30s, estNagekomi60s, estTotal;

    private Spinner registros;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistencia_rapidez);

        cliente= new AsyncHttpClient();
        registros=(Spinner)findViewById(R.id.spfecha);
        llenarSpinner();
        registros.setOnItemSelectedListener(this);

        mChart4 = findViewById(R.id.chart4);
        mChart4.animate();
        mChart4.setTouchEnabled(true);
        mChart4.setPinchZoom(true);
        mChart4.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart4);
        mChart4.setMarker(mv);

        estUshikomi = findViewById(R.id.ushikomi);
        estNagekomi60s = findViewById(R.id.nagekomi60s);
        estNagekomi30s = findViewById(R.id.nagekomi30s);
        estTotal = findViewById(R.id.totalRR);

        //renderData();
    }

    private void llenarSpinner(){

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url2= "http://192.168.1.23/judopic/historicos_deportista.php?usuario="+user;

        cliente.post(url2, new AsyncHttpResponseHandler() {
            @Override
            public void onSuccess(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody) {
                if(statusCode == 200){
                    cargarSpinner(new String(responseBody));
                }
            }

            @Override
            public void onFailure(int statusCode, cz.msebera.android.httpclient.Header[] headers, byte[] responseBody, Throwable error) {

            }
        });
    }

    private void cargarSpinner(String respuesta){
        ArrayList<Test> lista= new ArrayList<Test>();
        try{
            JSONArray jsonArreglo = new JSONArray(respuesta);
            for(int i=0; i<jsonArreglo.length();i++){
                Test t= new Test();
                t.setRegistro(jsonArreglo.getJSONObject(i).getString("registro"));
                lista.add(t);
            }
            ArrayAdapter<Test> a = new ArrayAdapter<Test>(this, android.R.layout.simple_dropdown_item_1line, lista);
            registros.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    public void renderData() {

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
        List<String> labels = new ArrayList<>(Arrays.asList("","Ushikomi", "Nagekomi60s", "Nagekomi30s"));
        xAxis4.setValueFormatter(new IndexAxisValueFormatter(labels));
        mChart4.animateXY(2000,2000);
        YAxis leftAxis4 = mChart4.getAxisLeft();
        leftAxis4.removeAllLimitLines();
        leftAxis4.setAxisMaximum(60f);
        leftAxis4.setAxisMinimum(0f);
        leftAxis4.enableGridDashedLine(10f, 10f, 0f);
        leftAxis4.setDrawZeroLine(false);
        leftAxis4.setDrawLimitLinesBehindData(false);
        mChart4.getAxisRight().setEnabled(false);

        //cargarWebService();
    }

    private void cargarWebService() {

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");
        String seleccion = registros.getSelectedItem().toString();

        String url= "http://192.168.1.23/judopic/estadisticas_deportista2.php?usuario="+user+"&registro="+seleccion;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);

    }

    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo Consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }


    public void onResponse(JSONObject response) {
        mChart4.clear();
        TestOptimo OmiTest = new TestOptimo();
        Test miTest = new Test();
        JSONArray jsonOpt = response.optJSONArray("testoptimo");
        JSONObject jsonObject1 = null;
        JSONArray json = response.optJSONArray("testpedagogico");
        JSONObject jsonObject = null;

        try {
            jsonObject1 = jsonOpt.getJSONObject(0);
            OmiTest.setOptUshikomi(jsonObject1.optString("ushikomi"));
            OmiTest.setOptNagekomi_60s(jsonObject1.optString("nagekomi_60s"));
            OmiTest.setOptNagekomi_30s(jsonObject1.optString("nagekomi_30s"));
            jsonObject = json.getJSONObject(0);
            miTest.setUshikomi(jsonObject.optString("ushikomi"));
            miTest.setNagekomi_60s(jsonObject.optString("nagekomi_60s"));
            miTest.setNagekomi_30s(jsonObject.optString("nagekomi_30s"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Integer optUshikomi = (Integer.parseInt(OmiTest.getOptUshikomi()));
        Integer optNagekomi_60s = (Integer.parseInt(OmiTest.getOptNagekomi_60s()));
        Integer optNagekomi_30s = (Integer.parseInt(OmiTest.getOptNagekomi_30s()));

        Integer ushikomi = (Integer.parseInt(miTest.getUshikomi()));
        Integer nagekomi_60s = (Integer.parseInt(miTest.getNagekomi_60s()));
        Integer nagekomi_30s = (Integer.parseInt(miTest.getNagekomi_30s()));


        ArrayList<Entry> values7 = new ArrayList<Entry>();
        values7.add(new Entry(1, optUshikomi));
        values7.add(new Entry(2, optNagekomi_60s));
        values7.add(new Entry(3, optNagekomi_30s));

        ArrayList<Entry> values8 = new ArrayList<Entry>();
        values8.add(new Entry(1, ushikomi));
        values8.add(new Entry(2, nagekomi_60s));
        values8.add(new Entry(3, nagekomi_30s));

        LineDataSet set7;
        LineDataSet set8;
        if (mChart4.getData() != null && mChart4.getData().getDataSetCount() > 0) {
            set7 = (LineDataSet) mChart4.getData().getDataSetByIndex(0);
            set7.setValues(values7);
            set8 = (LineDataSet) mChart4.getData().getDataSetByIndex(0);
            set8.setValues(values8);
            mChart4.getData().notifyDataChanged();
            mChart4.notifyDataSetChanged();
        } else {
            set7 = new LineDataSet(values7, "Óptimo");
            set8 = new LineDataSet(values8, "Test Pedagógico");
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

        DecimalFormat formato = new DecimalFormat("#.##");
        Float promedioUshikomi =(100-(((float)ushikomi*100)/(float)optUshikomi));
        Float promedioNagekomi60s =(100-((float)(nagekomi_60s*100)/(float)optNagekomi_60s));
        Float promedioNagekomi30s =(100-((float)(nagekomi_30s*100)/(float)optNagekomi_30s));
        Float promedioTotal =(100-((((float)ushikomi+(float)nagekomi_60s+(float)nagekomi_30s)*100)/((float)optUshikomi+(float)optNagekomi_60s+(float)nagekomi_30s)));

        estUshikomi.setText("Porcentaje diferencial Ushikomi: " + formato.format(promedioUshikomi) + "%");
        estNagekomi60s.setText("Porcentaje diferencial Nagekomi 60s: " + formato.format(promedioNagekomi60s) + "%");
        estNagekomi30s.setText("Porcentaje diferencial Nagekomi 30s: " + formato.format(promedioNagekomi30s) + "%");
        estTotal.setText("Porcentaje diferencial Total: " + formato.format(promedioTotal) + "%");

    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        renderData();
        cargarWebService();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
