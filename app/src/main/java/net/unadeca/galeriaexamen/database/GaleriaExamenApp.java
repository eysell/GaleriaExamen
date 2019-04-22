package net.unadeca.galeriaexamen.database;

import android.app.Application;

import com.raizlabs.android.dbflow.config.FlowManager;

public class GaleriaExamenApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //Inicializamos la base de datos
        FlowManager.init(this);
    }
}
