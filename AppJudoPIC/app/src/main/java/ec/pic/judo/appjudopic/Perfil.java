package ec.pic.judo.appjudopic;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
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

import ec.pic.judo.appjudopic.modelo.Persona;
import ec.pic.judo.appjudopic.modelo.VolleySingleton;

public class Perfil extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    ImageView foto;
    TextView cedula, nombre, apellido, fecha, tipo, grado, categoria, sexo, peso;
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
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil);

        foto=(ImageView)findViewById(R.id.foto);
        cedula=findViewById(R.id.cedula);
        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        fecha=findViewById(R.id.fecha);
        tipo=findViewById(R.id.tipo);
        grado=findViewById(R.id.grado);
        categoria=findViewById(R.id.categoria);
        sexo=findViewById(R.id.sexo);
        peso=findViewById(R.id.peso);

        cargarWebService();
    }


    private void cargarWebService() {

        SharedPreferences prefer=getSharedPreferences("datos", Context.MODE_PRIVATE);
        String user=prefer.getString("mail","");

        String url= "http://10.119.30.205/judopic/perfil_deportista.php?usuario="+user;
        jsonObjectRequest = new JsonObjectRequest(Request.Method.GET,url,null, (Response.Listener<JSONObject>) this,this);
        VolleySingleton.getIntanciaVolley(getApplicationContext()).addToRequestQueue(jsonObjectRequest);
    }


    @Override
    public void onErrorResponse(VolleyError error) {
        Toast.makeText(getApplicationContext(),"No se pudo Consultar "+error.toString(), Toast.LENGTH_SHORT).show();
        Log.i("ERROR",error.toString());
    }



    public void onResponse(JSONObject response) {
                Persona miUsuario = new Persona();
                JSONArray json = response.optJSONArray("persona");
                JSONObject jsonObject = null;

                try {
                    jsonObject = json.getJSONObject(0);
                    miUsuario.setCedula(jsonObject.optString("cedula"));
                    miUsuario.setNombre(jsonObject.optString("nombre"));
                    miUsuario.setApellido(jsonObject.optString("apellido"));
                    miUsuario.setFecha(jsonObject.optString("fechaNacimiento"));
                    miUsuario.setTipo(jsonObject.optString("tipo"));
                    miUsuario.setGrado(jsonObject.optString("grado"));
                    miUsuario.setCategoria(jsonObject.optString("categoria"));
                    miUsuario.setSexo(jsonObject.optString("sexo"));
                    miUsuario.setPeso(jsonObject.optString("peso"));
                    miUsuario.setDato(jsonObject.optString("foto"));

                } catch (JSONException e) {
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }

                cedula.setText("Cédula: " + miUsuario.getCedula());
                nombre.setText("Nombre: " + miUsuario.getNombre());
                apellido.setText("Apellido: " + miUsuario.getApellido());
                fecha.setText("Fecha de Nacimiento: " + miUsuario.getFecha());
                tipo.setText("Tipo: " + miUsuario.getTipo());
                grado.setText("Grado: " + miUsuario.getGrado());
                categoria.setText("Categoría: " + miUsuario.getCategoria());
                sexo.setText("Sexo: " + miUsuario.getSexo());
                peso.setText("Peso: " + miUsuario.getPeso());

                if (miUsuario.getFoto() != null) {
                    foto.setImageBitmap(miUsuario.getFoto());
                } else {
                    foto.setImageResource(R.drawable.img_base);
                }
            }


}


