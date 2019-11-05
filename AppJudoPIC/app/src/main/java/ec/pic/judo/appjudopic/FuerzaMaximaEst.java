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

public class FuerzaMaximaEst extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, AdapterView.OnItemSelectedListener{

    private LineChart mChart3;
    JsonObjectRequest jsonObjectRequest;
    TextView estProm, estHalon, estSentadilla, estTotal;

    private Spinner registros;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fuerza_maxima_est);

        cliente= new AsyncHttpClient();
        registros=(Spinner)findViewById(R.id.spfecha);
        llenarSpinner();
        registros.setOnItemSelectedListener(this);

        mChart3 = findViewById(R.id.chart3);
        mChart3.animate();
        mChart3.setTouchEnabled(true);
        mChart3.setPinchZoom(true);
        mChart3.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart3);
        mChart3.setMarker(mv);

        estProm = findViewById(R.id.prom);
        estHalon = findViewById(R.id.halon);
        estSentadilla = findViewById(R.id.sentadilla);
        estTotal = findViewById(R.id.totalFM);

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
        List<String> labels = new ArrayList<>(Arrays.asList("","Prom", "Halon", "Sentadilla"));
        xAxis3.setValueFormatter(new IndexAxisValueFormatter(labels));
        mChart3.animateXY(2000,2000);
        YAxis leftAxis3 = mChart3.getAxisLeft();
        leftAxis3.removeAllLimitLines();
        leftAxis3.setAxisMaximum(160f);
        leftAxis3.setAxisMinimum(0f);
        leftAxis3.enableGridDashedLine(10f, 10f, 0f);
        leftAxis3.setDrawZeroLine(false);
        leftAxis3.setDrawLimitLinesBehindData(false);
        mChart3.getAxisRight().setEnabled(false);

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
        mChart3.clear();
        TestOptimo  OmiTest = new TestOptimo();
        Test miTest = new Test();
        JSONArray jsonOpt = response.optJSONArray("testoptimo");
        JSONObject jsonObject1 = null;
        JSONArray json = response.optJSONArray("testpedagogico");
        JSONObject jsonObject = null;

        try {
            jsonObject1 = jsonOpt.getJSONObject(0);
            OmiTest.setOptProm(jsonObject1.optString("prom"));
            OmiTest.setOptHalon(jsonObject1.optString("halon"));
            OmiTest.setOptSentadilla(jsonObject1.optString("sentadilla"));
            jsonObject = json.getJSONObject(0);
            miTest.setProm(jsonObject.optString("prom"));
            miTest.setHalon(jsonObject.optString("halon"));
            miTest.setSentadilla(jsonObject.optString("sentadilla"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Float optProm = (Float.parseFloat(OmiTest.getOptProm()));
        Float optHalon = (Float.parseFloat(OmiTest.getOptHalon()));
        Float optSentadilla = (Float.parseFloat(OmiTest.getOptSentadilla()));

        Float prom = (Float.parseFloat(miTest.getProm()));
        Float halon = (Float.parseFloat(miTest.getHalon()));
        Float sentadilla = (Float.parseFloat(miTest.getSentadilla()));


        ArrayList<Entry> values5 = new ArrayList<Entry>();
        values5.add(new Entry(1, optProm));
        values5.add(new Entry(2, optHalon));
        values5.add(new Entry(3, optSentadilla));

        ArrayList<Entry> values6 = new ArrayList<Entry>();
        values6.add(new Entry(1, prom));
        values6.add(new Entry(2, halon));
        values6.add(new Entry(3, sentadilla));

        LineDataSet set5;
        LineDataSet set6;
        if (mChart3.getData() != null && mChart3.getData().getDataSetCount() > 0) {
            set5 = (LineDataSet) mChart3.getData().getDataSetByIndex(0);
            set5.setValues(values5);
            set6 = (LineDataSet) mChart3.getData().getDataSetByIndex(0);
            set6.setValues(values6);
            mChart3.getData().notifyDataChanged();
            mChart3.notifyDataSetChanged();
        } else {
            set5 = new LineDataSet(values5, "Óptimo");
            set6 = new LineDataSet(values6, "Test Pedagógico");
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

        DecimalFormat formato = new DecimalFormat("#.##");
        Float promedioProm =(100-((prom*100)/optProm));
        Float promedioHalon =(100-((halon*100)/optHalon));
        Float promedioSentadilla =(100-((sentadilla*100)/optSentadilla));
        Float promedioTotal =(100-(((prom+halon+sentadilla)*100)/(optProm+optHalon+optSentadilla)));

        estProm.setText("Porcentaje diferencial Prom: " + formato.format(promedioProm) + "%");
        estHalon.setText("Porcentaje diferencial Halon: " + formato.format(promedioHalon) + "%");
        estSentadilla.setText("Porcentaje diferencial Sentadilla: " + formato.format(promedioSentadilla) + "%");
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
