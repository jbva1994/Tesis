package ec.pic.judo.appjudopic;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ec.pic.judo.appjudopic.modelo.Persona;
import ec.pic.judo.appjudopic.modelo.VolleySingleton;

public class Perfil extends AppCompatActivity implements Response.Listener<JSONObject>, Response.ErrorListener {

    EditText usuario;
    ImageView foto;
    TextView cedula, nombre, apellido, fecha, tipo, grado, categoria, sexo, peso;
    Button btnListar;
    RequestQueue requestQueue;
    JsonObjectRequest jsonObjectRequest;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);

        foto=(ImageView)findViewById(R.id.foto);
        usuario=findViewById(R.id.usuario);
        cedula=findViewById(R.id.cedula);
        nombre=findViewById(R.id.nombre);
        apellido=findViewById(R.id.apellido);
        fecha=findViewById(R.id.fecha);
        tipo=findViewById(R.id.tipo);
        grado=findViewById(R.id.grado);
        categoria=findViewById(R.id.categoria);
        sexo=findViewById(R.id.sexo);
        peso=findViewById(R.id.peso);
        btnListar=(Button)findViewById(R.id.btnListar);


        btnListar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cargarWebService();
            }
        });
    }

    private void cargarWebService() {

        String url= "http://192.168.1.16/judopic/perfil_deportista.php?usuario="+usuario.getText().toString();
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

                cedula.setText("Cedula :" + miUsuario.getCedula());
                nombre.setText("Nombre :" + miUsuario.getNombre());
                apellido.setText("Apellido :" + miUsuario.getApellido());
                fecha.setText("Fecha de Nacimiento :" + miUsuario.getFecha());
                tipo.setText("Tipo :" + miUsuario.getTipo());
                grado.setText("Grado :" + miUsuario.getGrado());
                categoria.setText("Categoria :" + miUsuario.getCategoria());
                sexo.setText("Sexo :" + miUsuario.getSexo());
                peso.setText("Peso :" + miUsuario.getPeso());

                if (miUsuario.getFoto() != null) {
                    foto.setImageBitmap(miUsuario.getFoto());
                } else {
                    foto.setImageResource(R.drawable.img_base);
                }
            }


}


