package net.unadeca.galeriaexamen.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.raizlabs.android.dbflow.sql.language.SQLite;

import net.unadeca.galeriaexamen.R;
import net.unadeca.galeriaexamen.database.models.Imagenes;
import net.unadeca.galeriaexamen.database.models.Imagenes_Table;

public class DetalleActivity extends AppCompatActivity {

    private static CoordinatorLayout coordinatorLayout;
    private ViewPager viewPager;
    private ImageView imagen;
    private static Imagenes mostrar;//variable para el coordinador que esta en detalleImagen

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.imagendetalles);

        coordinatorLayout = findViewById(R.id.coordinador);
        viewPager = findViewById(R.id.viewpager);
        imagen = findViewById(R.id.imagen);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setDisplayHomeAsUpEnabled(true);

        //lllamamos esta parte en el adaptador
        if(getIntent().hasExtra("imagen")){
            mostrar = SQLite.select().from(Imagenes.class).where(Imagenes_Table.id.eq(getIntent().getExtras().getLong("imagen"))).querySingle();
            Glide.with(this).load(mostrar.imagen).error( Glide.with(this)
                    .load(R.mipmap.ic_launcher)).centerCrop().into(imagen);
        }

        inicializar();

    }
    //agregar el formulario al viewPager
    private void inicializar(){
        net.unadeca.galeriaexamen.subclases.Adapter adaptador = new net.unadeca.galeriaexamen.subclases.Adapter(getSupportFragmentManager());
        adaptador.agregarFragmento(new detalleFragment());
        viewPager.setAdapter(adaptador);
    }
    public static class detalleFragment extends Fragment {
        public TextView titulo;
        public TextView descripcion;


        //Inicializamos la vista
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.detalles, container, false);

            //Para referenciarlos en la lista
            titulo = rootView.findViewById(R.id.titulo);
            descripcion = rootView.findViewById(R.id.descripcion);
            return rootView;

        }


    }

}
