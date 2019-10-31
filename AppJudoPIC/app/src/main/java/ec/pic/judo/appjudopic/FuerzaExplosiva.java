package ec.pic.judo.appjudopic;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.graphics.DashPathEffect;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class FuerzaExplosiva extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{
    private LineChart mChart2;
    JsonObjectRequest jsonObjectRequest;
    TextView estPecho, estAbdomen, estCunclilla, estTotal;

    Button btnGraficar;

    private Spinner fechas;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza_explosiva);

        cliente= new AsyncHttpClient();
        fechas=(Spinner)findViewById(R.id.spfecha);
        llenarSpinner();

        btnGraficar=(Button)findViewById(R.id.btnGraficar);

        btnGraficar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listarWebService();
            }
        });

        mChart2 = findViewById(R.id.chart2);
        mChart2.animate();
        mChart2.setTouchEnabled(true);
        mChart2.setPinchZoom(true);
        mChart2.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart2);
        mChart2.setMarker(mv);

        estPecho = findViewById(R.id.pecho);
        estAbdomen = findViewById(R.id.abdomen);
        estCunclilla = findViewById(R.id.cunclilla);
        estTotal = findViewById(R.id.totalFE);

        renderData();


    }


    private void llenarSpinner(){

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url= "http://192.168.0.15/judopic/historicos_deportista.php?usuario="+user;

        cliente.post(url, new AsyncHttpResponseHandler() {
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
            fechas.setAdapter(a);
        }catch (Exception e){
            e.printStackTrace();
        }

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

    private void listarWebService() {

        String url= "http://192.168.0.15/judopic/estadisticas_deportista - copia.php";
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    private void cargarWebService() {

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url= "http://192.168.0.15/judopic/estadisticas_deportista.php?usuario="+user;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo Consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }


    public void onResponse(JSONObject response) {
        TestOptimo OmiTest = new TestOptimo();
        Test miTest = new Test();
        JSONArray jsonOpt = response.optJSONArray("testoptimo");
        JSONObject jsonObject1 = null;
        JSONArray json = response.optJSONArray("testpedagogico");
        JSONObject jsonObject = null;

        try {
            jsonObject1 = jsonOpt.getJSONObject(0);
            OmiTest.setOptPecho(jsonObject1.optString("pecho"));
            OmiTest.setOptAbdomen(jsonObject1.optString("abdomen"));
            OmiTest.setOptCunclilla(jsonObject1.optString("cunclilla"));
            jsonObject = json.getJSONObject(0);
            miTest.setPecho(jsonObject.optString("pecho"));
            miTest.setAbdomen(jsonObject.optString("abdomen"));
            miTest.setCunclilla(jsonObject.optString("cunclilla"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Integer optPecho = (Integer.parseInt(OmiTest.getOptPecho()));
        Integer optAbdomen = (Integer.parseInt(OmiTest.getOptAbdomen()));
        Integer optCunclilla = (Integer.parseInt(OmiTest.getOptCunclilla()));

        Integer pecho = (Integer.parseInt(miTest.getPecho()));
        Integer abdomen = (Integer.parseInt(miTest.getAbdomen()));
        Integer cunclilla = (Integer.parseInt(miTest.getCunclilla()));


        ArrayList<Entry> values3 = new ArrayList<Entry>();
        values3.add(new Entry(1, optPecho));
        values3.add(new Entry(2, optAbdomen));
        values3.add(new Entry(3, optCunclilla));

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

        DecimalFormat formato = new DecimalFormat("#.##");
        Float promedioPecho = (100-(((float)pecho*100)/(float)optPecho));
        Float promedioAbdomen = (100-(((float)abdomen*100)/(float)optAbdomen));
        Float promedioCunclilla = (100-(((float)cunclilla*100)/(float)optCunclilla));
        Float promedioTotal = (100-((((float)pecho+(float)abdomen+(float)cunclilla)*100)/((float)optPecho+(float)optAbdomen+(float)optCunclilla)));

        estPecho.setText("Porcentaje diferencial Pecho: " + formato.format(promedioPecho) + "%");
        estAbdomen.setText("Porcentaje diferencial Abdomen: " + formato.format(promedioAbdomen) + "%");
        estCunclilla.setText("Porcentaje diferencial Cunclilla: " + formato.format(promedioCunclilla) + "%");
        estTotal.setText("Porcentaje diferencial Total: " + formato.format(promedioTotal) + "%");

    }

}
