package ec.pic.judo.appjudopic;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

public class Principal extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_cerrarsesion) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public void perfil (View view){
        Intent perfil = new Intent(this, Perfil.class);
        startActivity(perfil);
    }

    public void estadisticas (View view){
        Intent estadisticas = new Intent(this, Estadisticas.class);
        startActivity(estadisticas);
    }

    public void entrenamiento (View view){
        Intent entrenamiento = new Intent(this, Entrenamiento.class);
        startActivity(entrenamiento);
    }
}
