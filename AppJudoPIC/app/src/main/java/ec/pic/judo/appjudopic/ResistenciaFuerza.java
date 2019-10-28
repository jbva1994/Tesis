package ec.pic.judo.appjudopic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ec.pic.judo.appjudopic.modelo.Test;
import ec.pic.judo.appjudopic.modelo.VolleySingleton;

public class ResistenciaFuerza extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    TextView barras70, paralelas70, cabos70, barras80, paralelas80, cabos80, barras90, paralelas90, cabos90;
    JsonObjectRequest jsonObjectRequest;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_salir) {
            finish();
        }

        if (id == R.id.action_acerca){
            Intent acerca = new Intent(this, Acerca.class);
            startActivity(acerca);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resistencia_fuerza);

        barras70=findViewById(R.id.barras70);
        paralelas70=findViewById(R.id.paralelas70);
        cabos70=findViewById(R.id.cabos70);
        barras80=findViewById(R.id.barras80);
        paralelas80=findViewById(R.id.paralelas80);
        cabos80=findViewById(R.id.cabos80);
        barras90=findViewById(R.id.barras90);
        paralelas90=findViewById(R.id.paralelas90);
        cabos90=findViewById(R.id.cabos90);

        cargarWebService();
    }

    private void cargarWebService() {

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url= "http://10.119.30.205/judopic/entrenamiento_deportista.php?usuario="+user;
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
            miTest.setBarras(jsonObject.optString("barras"));
            miTest.setParalelas(jsonObject.optString("paralelas"));
            miTest.setCabos(jsonObject.optString("cabos"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


        Double b70 = (Double.parseDouble(miTest.getBarras())*0.7);
        Double p70 = (Double.parseDouble(miTest.getParalelas())*0.7);
        Double c70 = (Double.parseDouble(miTest.getCabos())*0.7);
        barras70.setText("Barras: " + b70.longValue());
        paralelas70.setText("Paralelas: " + p70.longValue());
        cabos70.setText("Cabos: " + c70.longValue());
        Double b80 = (Double.parseDouble(miTest.getBarras())*0.8);
        Double p80 = (Double.parseDouble(miTest.getParalelas())*0.8);
        Double c80 = (Double.parseDouble(miTest.getCabos())*0.8);
        barras80.setText("Barras: " + b80.longValue());
        paralelas80.setText("Paralelas: " + p80.longValue());
        cabos80.setText("Cabos: " + c80.longValue());
        Double b90 = (Double.parseDouble(miTest.getBarras())*0.9);
        Double p90 = (Double.parseDouble(miTest.getParalelas())*0.9);
        Double c90 = (Double.parseDouble(miTest.getCabos())*0.9);
        barras90.setText("Barras: " + b90.longValue());
        paralelas90.setText("Paralelas: " + p90.longValue());
        cabos90.setText("Cabos: " + c90.longValue());
    }

}

