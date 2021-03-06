package com.lol.lolpro.app.bbdd;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.GridView;

import com.lol.lolpro.app.R;
import com.lol.lolpro.app.grids.GridAdapterFreeChamps;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Utils;
import com.lol.lolpro.app.web.APIConnection;

/**
 * Clase que se encarga de la descarga de los datos
 */
public class DescargarBBDD extends AsyncTask<Void, Integer, Void> {

    //ProgressDialog progress;
    Activity activ;
    Context appContext;
    APIConnection api;
    int accion = Constants.DB_DONOTHING;
    dFragment dFragment;
    boolean hayNuevosGratuitos = false, hayNuevaVersion = false;

    /**
     * Constructor
     *
     * @param context Recibe el activity principal
     */
    public DescargarBBDD(Activity context) {
        activ = context;
        appContext = context.getApplicationContext();
       // progress = new ProgressDialog(contexto);
    }

    /**
     * Se encarga del tratamiento necesario antes de comenzar la tarea asíncrona, en este caso de indicarle a la tarea asíncrona si debe crear la base de datos o solo actualizarla
     */
    public void onPreExecute() {
        if (!Utils.existsDB(appContext)) {
            accion = Constants.DB_DOWNLOAD;
        } else {
            accion = Constants.DB_UPDATE;
        }
        api = new APIConnection(appContext);
        //inicializarDialog();
    }

    /**
     * Tarea que se ejecuta de forma asíncrona y se encarga de descargar los datos del api
     *
     * @param voids null
     * @return null
     */
    @Override
    protected Void doInBackground(Void... voids) {
        if (accion == Constants.DB_DOWNLOAD) {
            inicializarBBDD();
        } else if (accion == Constants.DB_UPDATE) {
            hayNuevaVersion = api.hayNuevaVersion();
            if (hayNuevaVersion) {
                actualizarBBDD();
            }
        }
        hayNuevosGratuitos = api.hayNuevosGratuitos();
        this.publishProgress(100, APIConnection.CHAMPION_FREE);
        return null;
    }

    /**
     * Se encarga de actualizar la barra de progreso
     *
     * @param integers integers[0] contiene el tanto por ciento al que se debe actualziar la barra de progreso
     */
    @Override
    protected void onProgressUpdate(Integer... integers) {
        int textID;
        switch (integers[1]){
            case APIConnection.IMAGES_AND_VERSIONS:
                textID = R.string.descargando_num_version;
                break;
            case APIConnection.CHAMPIONS:
                textID = R.string.descargando_campeones;
                break;
            case APIConnection.UPDATE_CHAMPIONS:
                textID = R.string.descargando_act_campeones;
                break;
            case APIConnection.OBJECTS:
                textID = R.string.descargando_objetos;
                break;
            case APIConnection.UPDATE_OBJECTS:
                textID = R.string.descargando_act_objetos;
                break;
            case APIConnection.CHAMPION_FREE:
                textID = R.string.descargando_campeones_gratuitos;
                break;
            default:
                // Generic download message
                textID = R.string.descargando_titulo;
                break;
        }
        dFragment.updateProgress(integers[0], appContext.getString(textID));
    }

    /**
     * Se encarga del tratamiento neceasrio una vez realizada la operación asíncrona
     *
     * @param unused null
     */
    public void onPostExecute(Void unused) {
        if (accion != Constants.DB_DONOTHING) {
            api.closeAPI();
            if (accion == Constants.DB_DOWNLOAD || hayNuevosGratuitos || hayNuevaVersion) {
                refreshUI();
            }
        }
        dFragment.taskFinished();
    }

    /**
     * Se encarga de indicar que tareas serían necesarias en caso de que la base de datos no esté creada
     */
    public void inicializarBBDD() {
        this.publishProgress(0, APIConnection.IMAGES_AND_VERSIONS);
        api.connect2API(APIConnection.IMAGES_AND_VERSIONS);
        this.publishProgress(25, APIConnection.CHAMPIONS);
        api.connect2API(APIConnection.CHAMPIONS);
        this.publishProgress(50, APIConnection.OBJECTS);
        api.connect2API(APIConnection.OBJECTS);
        this.publishProgress(75, APIConnection.CHAMPION_FREE);
    }

    /**
     * Se encarga de indicar que tareas serían necesarias en caso de que la base de datos esté creada y sea neceasrio actualizarla ya que ha habido un cambio de versión
     */
    public void actualizarBBDD() {
        this.publishProgress(0, APIConnection.UPDATE_CHAMPIONS);
        api.connect2API(APIConnection.UPDATE_CHAMPIONS);
        this.publishProgress(33, APIConnection.UPDATE_OBJECTS);
        api.connect2API(APIConnection.UPDATE_OBJECTS);
        this.publishProgress(66, APIConnection.CHAMPION_FREE);
    }

    private void refreshUI() {
        GridView grid = (GridView) activ.findViewById(R.id.gridInicio);
        if (grid != null) {
            GridAdapterFreeChamps gA = (GridAdapterFreeChamps) grid.getAdapter();
            gA.refresh();
        }
    }
    public void setFragment(dFragment df){
        dFragment = df;
    }
}
