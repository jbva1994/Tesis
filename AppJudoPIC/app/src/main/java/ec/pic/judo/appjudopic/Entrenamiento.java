package ec.pic.judo.appjudopic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Entrenamiento extends AppCompatActivity  {

    Button btnResistencia, btnFuerza;

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
        setContentView(R.layout.activity_entrenamiento);

        btnResistencia=(Button)findViewById(R.id.btnResistencia);
        btnFuerza=(Button)findViewById(R.id.btnFuerza);
    }

    public void Resistencia (View view){
        Intent res = new Intent(this, ResistenciaFuerza.class);
        startActivity(res);
    }

    public void Fuerza (View view){
        Intent fuerza = new Intent(this, FuerzaMaxima.class);
        startActivity(fuerza);
    }


}
