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

public class ResistenciaFuerzaEst extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, AdapterView.OnItemSelectedListener{

    private LineChart mChart;
    JsonObjectRequest jsonObjectRequest;
    TextView estBarras, estParalelas, estCabos, estTotal;

    private Spinner registros;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistencia_fuerza_est);

        cliente= new AsyncHttpClient();
        registros=(Spinner)findViewById(R.id.spfecha);
        llenarSpinner();
        registros.setOnItemSelectedListener(this);

        mChart = findViewById(R.id.chart);
        mChart.animate();
        mChart.setTouchEnabled(true);
        mChart.setPinchZoom(true);
        mChart.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart);
        mChart.setMarker(mv);

        estBarras = findViewById(R.id.barras);
        estParalelas = findViewById(R.id.paralelas);
        estCabos = findViewById(R.id.cabos);
        estTotal = findViewById(R.id.totalRF);

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
        mChart.setDragEnabled(true);
        mChart.setScaleEnabled(false);
        XAxis xAxis = mChart.getXAxis();
        xAxis.enableGridDashedLine(10f, 10f, 0f);
        xAxis.setAxisMaximum(4f);
        xAxis.setAxisMinimum(0f);
        xAxis.setDrawLimitLinesBehindData(true);
        List<String> labels = new ArrayList<>(Arrays.asList("","Barras", "Paralelas", "Cabos"));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(labels));
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
        mChart.clear();
        TestOptimo OmiTest = new TestOptimo();
        Test miTest = new Test();
        JSONArray jsonOpt = response.optJSONArray("testoptimo");
        JSONObject jsonObject1 = null;
        JSONArray json = response.optJSONArray("testpedagogico");
        JSONObject jsonObject = null;

        try {
            jsonObject1 = jsonOpt.getJSONObject(0);
            OmiTest.setOptBarras(jsonObject1.optString("barras"));
            OmiTest.setOptParalelas(jsonObject1.optString("paralelas"));
            OmiTest.setOptCabos(jsonObject1.optString("cabos"));
            jsonObject = json.getJSONObject(0);
            miTest.setBarras(jsonObject.optString("barras"));
            miTest.setParalelas(jsonObject.optString("paralelas"));
            miTest.setCabos(jsonObject.optString("cabos"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Integer optBarras = (Integer.parseInt(OmiTest.getOptBarras()));
        Integer optParalelas = (Integer.parseInt(OmiTest.getOptParalelas()));
        Integer optCabos = (Integer.parseInt(OmiTest.getOptCabos()));

        Integer barras = (Integer.parseInt(miTest.getBarras()));
        Integer paralelas = (Integer.parseInt(miTest.getParalelas()));
        Integer cabos = (Integer.parseInt(miTest.getCabos()));


        ArrayList<Entry> values1 = new ArrayList<Entry>();
        values1.add(new Entry(1, optBarras));
        values1.add(new Entry(2, optParalelas));
        values1.add(new Entry(3, optCabos));


        ArrayList<Entry> values2 = new ArrayList<Entry>();
        values2.add(new Entry(1, barras));
        values2.add(new Entry(2, paralelas));
        values2.add(new Entry(3, cabos));


        LineDataSet set1;
        LineDataSet set2;
        if (mChart.getData() != null && mChart.getData().getDataSetCount() > 0) {
            set1 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set1.setValues(values1);
            set2 = (LineDataSet) mChart.getData().getDataSetByIndex(0);
            set2.setValues(values2);
            mChart.getData().notifyDataChanged();
            mChart.notifyDataSetChanged();
        } else {
            set1 = new LineDataSet(values1, "Óptimo");
            set2 = new LineDataSet(values2, "Test Pedagógico");
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

        DecimalFormat formato = new DecimalFormat("#.##");
        Float promedioBarras = (100-(((float)barras*100)/(float)optBarras));
        Float promedioParalelas =(100-(((float)paralelas*100)/(float)optParalelas));
        Float promedioCabos =(100-(((float)cabos*100)/(float)optCabos));
        Float promedioTotal =(100-(((float)(barras+(float)paralelas+(float)cabos)*100)/((float)optBarras+(float)optParalelas+(float)optCabos)));

        estBarras.setText("Porcentaje diferencial Barras: " + formato.format(promedioBarras) + "%");
        estParalelas.setText("Porcentaje diferencial Paralelas: " + formato.format(promedioParalelas) + "%");
        estCabos.setText("Porcentaje diferencial Cabos: " + formato.format(promedioCabos) + "%");
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
