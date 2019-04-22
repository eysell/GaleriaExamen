package net.unadeca.galeriaexamen.activities;

import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import com.raizlabs.android.dbflow.sql.language.Delete;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.galeriaexamen.R;
import net.unadeca.galeriaexamen.database.models.Imagenes;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView lista;
    private CoordinatorLayout view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        lista = findViewById(R.id.lista);
        lista.setLayoutManager(new LinearLayoutManager(this));
        view = findViewById(R.id.coordinador);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                testBaseDatos();
                establecerAdaptador();
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        establecerAdaptador();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
 //imagenes que se agregan se vana acargar aquí
    private void testBaseDatos() {
        Delete.table(Imagenes.class);
        Imagenes imagen;
        for (int a = 0; a < 10; a++) {
            imagen = new Imagenes();
            imagen.imagen = "http://2.bp.blogspot.com/-HxOYNqlhQRg/UAdiMM08gsI/AAAAAAABAl0/rCdoRharrvA/s1600/noche-de-luna-en-el-mar-crucero- cruise-mar-abierto-en-la-oscuridad.jpg";
            imagen.titulo = "Atardecer en el paraíso" + (a + 1);
            imagen.descripcion = "Esta foto fue tomada durante el atardecer en altamar" + (a + 1);
            imagen.save();
        }
    }

    //Establecemos el adaptador de la lista
    private void establecerAdaptador(){
        lista.setAdapter(new CustomAdapterRecycler(getImagenes(), this, view));
    }

    private List<Imagenes> getImagenes() {
        return SQLite.select().from(Imagenes.class).queryList();
    }
}
