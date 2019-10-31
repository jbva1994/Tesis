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

public class FuerzaMaxima extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener{

    TextView prom70, halon70, sentadilla70, prom80, halon80, sentadilla80, prom90, halon90, sentadilla90;
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
        setContentView(R.layout.activity_fuerza_maxima);

        prom70=findViewById(R.id.prom70);
        halon70=findViewById(R.id.halon70);
        sentadilla70=findViewById(R.id.sentadilla70);
        prom80=findViewById(R.id.prom80);
        halon80=findViewById(R.id.halon80);
        sentadilla80=findViewById(R.id.sentadilla80);
        prom90=findViewById(R.id.prom90);
        halon90=findViewById(R.id.halon90);
        sentadilla90=findViewById(R.id.sentadilla90);

        cargarWebService();
    }

    private void cargarWebService() {

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url= "http://192.168.0.15/judopic/entrenamiento_deportista.php?usuario="+user;
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
            miTest.setProm(jsonObject.optString("prom"));
            miTest.setHalon(jsonObject.optString("halon"));
            miTest.setSentadilla(jsonObject.optString("sentadilla"));

        } catch (JSONException e) {
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
        Double pr70 = (Double.parseDouble(miTest.getProm())*0.7);
        Double h70 = (Double.parseDouble(miTest.getHalon())*0.7);
        Double s70 = (Double.parseDouble(miTest.getSentadilla())*0.7);
        prom70.setText("Prom: " + pr70.longValue());
        halon70.setText("Halon: " + h70.longValue());
        sentadilla70.setText("Sentadilla: " + s70.longValue());
        Double pr80 = (Double.parseDouble(miTest.getProm())*0.8);
        Double h80 = (Double.parseDouble(miTest.getHalon())*0.8);
        Double s80 = (Double.parseDouble(miTest.getSentadilla())*0.8);
        prom80.setText("Prom: " + pr80.longValue());
        halon80.setText("Halon: " + h80.longValue());
        sentadilla80.setText("Sentadilla: " + s80.longValue());
        Double pr90 = (Double.parseDouble(miTest.getProm())*0.9);
        Double h90 = (Double.parseDouble(miTest.getHalon())*0.9);
        Double s90 = (Double.parseDouble(miTest.getSentadilla())*0.9);
        prom90.setText("Prom: " + pr90.longValue());
        halon90.setText("Halon: " + h90.longValue());
        sentadilla90.setText("Sentadilla: " + s90.longValue());
    }

}
