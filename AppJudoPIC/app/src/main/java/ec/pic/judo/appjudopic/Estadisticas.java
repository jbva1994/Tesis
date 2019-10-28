package ec.pic.judo.appjudopic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;

public class Estadisticas extends AppCompatActivity {

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
        setContentView(R.layout.activity_estadisticas);
    }

    public void restFueza (View view){
        Intent estadistica = new Intent(this, ResistenciaFuerzaEst.class);
        startActivity(estadistica);
    }

    public void furzaExpl (View view){
        Intent estadistica = new Intent(this, FuerzaExplosiva.class);
        startActivity(estadistica);
    }

    public void fuezaMax (View view){
        Intent estadistica = new Intent(this, FuerzaMaximaEst.class);
        startActivity(estadistica);
    }

    public void restRap (View view){
        Intent estadistica = new Intent(this, ResistenciaRapidez.class);
        startActivity(estadistica);
    }

    public void velocidadTras (View view){
        Intent estadistica = new Intent(this, VelocidadTraslacion.class);
        startActivity(estadistica);
    }
}
