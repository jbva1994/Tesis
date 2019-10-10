package ec.pic.judo.appjudopic;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class Principal extends AppCompatActivity {

    EditText usuario;
    ImageView foto;
    TextView cedula, nombre, apellido, fecha, tipo, grado, categoria, sexo, peso;
    Button btnListar;
    RequestQueue requestQueue;

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.principal);

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
                listarDeportista( "http://192.168.1.16/judopic/listar_deportista.php?usuario="+usuario.getText()+"");
            }
        });
    }

    private void listarDeportista (String URL){
        JsonArrayRequest jsonArrayRequest=new JsonArrayRequest(URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                JSONObject jsonObject = null;
                for (int i = 0; i < response.length(); i++) {
                    try {
                        jsonObject = response.getJSONObject(i);
                        usuario.setText(jsonObject.getString("usuario"));
                        cedula.setText(jsonObject.getString("cedula"));
                        nombre.setText(jsonObject.getString("nombre"));
                        apellido.setText(jsonObject.getString("apellido"));
                        fecha.setText(jsonObject.getString("fechaNacimiento"));
                        tipo.setText(jsonObject.getString("tipo"));
                        grado.setText(jsonObject.getString("grado"));
                        categoria.setText(jsonObject.getString("categoria"));
                        sexo.setText(jsonObject.getString("sexo"));
                        peso.setText(jsonObject.getString("peso"));


                    } catch (JSONException e) {
                        Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                }

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(getApplicationContext(), "Error de conexión", Toast.LENGTH_SHORT).show();
            }
        }
        );
        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(jsonArrayRequest);
    }
}
