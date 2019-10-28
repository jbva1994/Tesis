package ec.pic.judo.appjudopic;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;


public class Login extends AppCompatActivity {

    EditText usuario;
    EditText clave;

    Button btnLogin;
    RadioButton sesion;
    boolean isActivateRadioButton;

    public static final String STRING_PREFERENCES = "preferences.JudoPIC";
    public static final String PREFERENCE_ESTADO_BUTTON_SESION = "estado.button.sesion";

    @Override
    protected void onCreate (Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if(obtenerEstadoButton()){
            Intent intent = new Intent(getApplicationContext(), Principal.class);
            startActivity(intent);
            finish();
        }

        usuario=findViewById(R.id.edtUsuario);
        usuario.getText().toString();
        clave =findViewById(R.id.edtPassword);

        //texto= clave.toString();
        //encriptar=Login.Encriptar(texto);

        btnLogin= (Button)findViewById(R.id.btnLogin);

        sesion=(RadioButton)findViewById(R.id.rbsesion);
        isActivateRadioButton = sesion.isChecked(); //DESACTIVADO

        sesion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isActivateRadioButton){
                    sesion.setChecked(false);
                }
                isActivateRadioButton = sesion.isChecked();

            }
        });



        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validarUsuario( "http://10.119.30.205/judopic/validar_usuario.php");

                SharedPreferences prefer=getSharedPreferences("datos",Context.MODE_PRIVATE);
                SharedPreferences.Editor editor=prefer.edit();
                editor.putString("mail", usuario.getText().toString());
                editor.commit();
            }
        });
    }


    public void validarUsuario(String URL){
        StringRequest stringRequest=new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {

                if(!response.isEmpty()){
                    Intent intent = new Intent(getApplicationContext(), Principal.class);
                    guardarEstadoButton();
                    startActivity(intent);
                    finish();
                    //startActivity(new Intent(Login.this, Principal.class));
                }else{
                    Toast.makeText(Login.this, "Usuario o Contraseña incorrectos", Toast.LENGTH_SHORT).show();
                }


            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse (VolleyError error){
                Toast.makeText(Login.this, "SIN CONEXION A INTERNET", Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> parametros=new HashMap<String, String>();
                    parametros.put("usuario", usuario.getText().toString());
                    parametros.put("clave",clave.getText().toString());

                return parametros;
            }
        };

        RequestQueue requestQueue= Volley.newRequestQueue(this);
        requestQueue.add(stringRequest);

    }

    public void guardarEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,sesion.isChecked()).apply();
    }

    public boolean obtenerEstadoButton(){
        SharedPreferences preferences = getSharedPreferences(STRING_PREFERENCES,MODE_PRIVATE);
        return preferences.getBoolean(PREFERENCE_ESTADO_BUTTON_SESION,false);//Si es que nunca se ha guardado nada en esta key pues retornara false
    }

    public static void cambiarEstadoButon(Context c, boolean b){
        SharedPreferences preferences = c.getSharedPreferences(STRING_PREFERENCES,c.MODE_PRIVATE);
        preferences.edit().putBoolean(PREFERENCE_ESTADO_BUTTON_SESION,b).apply();
    }

    private BroadcastReceiver networkStateReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            ConnectivityManager manager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo ni = manager.getActiveNetworkInfo();
            onNetworkChange(ni);
        }
    };

    @Override
    public void onResume() {
        super.onResume();
        registerReceiver(networkStateReceiver, new IntentFilter(android.net.ConnectivityManager.CONNECTIVITY_ACTION));
    }

    @Override
    public void onPause() {
        unregisterReceiver(networkStateReceiver);
        super.onPause();
    }

    private void onNetworkChange(NetworkInfo networkInfo) {
        if (networkInfo != null) {
            if (networkInfo.getState() == NetworkInfo.State.CONNECTED) {
                Toast.makeText(Login.this, "Bienvenido", Toast.LENGTH_SHORT).show();
            }
            if (networkInfo.getState() == NetworkInfo.State.DISCONNECTED) {
                Toast.makeText(Login.this, "SIN CONEXION A INTERNET", Toast.LENGTH_LONG).show();
            }
        }
    }
}