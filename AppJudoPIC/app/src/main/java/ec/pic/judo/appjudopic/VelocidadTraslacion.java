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

public class VelocidadTraslacion extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener, AdapterView.OnItemSelectedListener{

    private LineChart mChart5;
    JsonObjectRequest jsonObjectRequest;
    TextView estPique30m, estPique50m, estPique100m, estTotal;

    private Spinner registros;
    private AsyncHttpClient cliente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_velocidad_traslacion);

        cliente= new AsyncHttpClient();
        registros=(Spinner)findViewById(R.id.spfecha);
        llenarSpinner();
        registros.setOnItemSelectedListener(this);

        mChart5 = findViewById(R.id.chart5);
        mChart5.animate();
        mChart5.setTouchEnabled(true);
        mChart5.setPinchZoom(true);
        mChart5.getDescription().setEnabled(false);

        view mv = new view(getApplicationContext(), R.layout.activity_view);
        mv.setChartView(mChart5);
        mChart5.setMarker(mv);

        estPique30m = findViewById(R.id.pique30m);
        estPique50m = findViewById(R.id.pique50m);
        estPique100m = findViewById(R.id.pique100m);
        estTotal = findViewById(R.id.totalVT);

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
        mChart5.clear();
        TestOptimo OmiTest = new TestOptimo();
        Test miTest = new Test();
        JSONArray jsonOpt = response.optJSONArray("testoptimo");
        JSONObject jsonObject1 = null;
        JSONArray json = response.optJSONArray("testpedagogico");
        JSONObject jsonObject = null;

        try {
            jsonObject1 = jsonOpt.getJSONObject(0);
            OmiTest.setOptPique_30m(jsonObject1.optString("pique_30m"));
            OmiTest.setOptPique_50m(jsonObject1.optString("pique_50m"));
            OmiTest.setOptPique_100m(jsonObject1.optString("pique_100m"));
            jsonObject = json.getJSONObject(0);
            miTest.setPique_30m(jsonObject.optString("pique_30m"));
            miTest.setPique_50m(jsonObject.optString("pique_50m"));
            miTest.setPique_100m(jsonObject.optString("pique_100m"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

        Float optPique30 = (Float.parseFloat(OmiTest.getOptPique_30m()));
        Float optPique50 = (Float.parseFloat(OmiTest.getOptPique_50m()));
        Float optPique100 = (Float.parseFloat(OmiTest.getOptPique_100m()));

        Float pique_30m = (Float.parseFloat(miTest.getPique_30m()));
        Float pique_50m = (Float.parseFloat(miTest.getPique_50m()));
        Float pique_100m = (Float.parseFloat(miTest.getPique_100m()));


        ArrayList<Entry> values9 = new ArrayList<Entry>();
        values9.add(new Entry(1, optPique30));
        values9.add(new Entry(2, optPique50));
        values9.add(new Entry(3, optPique100));

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
            set10 = new LineDataSet(values10, "Test Pedagógico");
            set9 = new LineDataSet(values9, "Óptimo");
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
            dataSets.add(set10);
            dataSets.add(set9);
            LineData data = new LineData(dataSets);
            mChart5.setData(data);
        }


        DecimalFormat formato = new DecimalFormat("#.##");
        Float promedioPique30m =(((pique_30m*100)/optPique30)-100);
        Float promedioPique50m =(((pique_50m*100)/optPique50)-100);
        Float promedioPique100m =(((pique_100m*100)/optPique100)-100);
        Float promedioTotal =((((pique_30m+pique_50m+pique_100m)*100)/(optPique30+optPique50+optPique100))-100);

        estPique30m.setText("Porcentaje diferencial Pique 30m: " + formato.format(promedioPique30m) + "%");
        estPique50m.setText("Porcentaje diferencial Pique 50m: " + formato.format(promedioPique50m) + "%");
        estPique100m.setText("Porcentaje diferencial Pique 100m: " + formato.format(promedioPique100m) + "%");
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
