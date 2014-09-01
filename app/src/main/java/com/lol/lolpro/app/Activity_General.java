package com.lol.lolpro.app;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.lol.lolpro.app.bbdd.DescargarBBDD;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.campeones.CampeonContenedor;
import com.lol.lolpro.app.campeones.CampeonesGlobal;
import com.lol.lolpro.app.inicio.InicioGlobal;
import com.lol.lolpro.app.objetos.ObjetoContenedor;
import com.lol.lolpro.app.objetos.ObjetoGeneral;
import com.lol.lolpro.app.objetos.ObjetosGlobal;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Utils;

/**
 * Activity principal en el que se colocarán todos los fragments
 */
public class Activity_General extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        CampeonesGlobal.OnHeadlineSelectedListener, InicioGlobal.OnHeadlineSelectedListener,
        ObjetosGlobal.OnHeadlineSelectedListener, ObjetoGeneral.OnHeadlineSelectedListener {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    /**
     * Se encarga de inicializar el activity para que contenga lo necesario para poder dibujar los fragments
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DBManager.initializeInstance(this);
        setContentView(R.layout.activity_general);
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new InicioGlobal())
                    .commit();
        }
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));

       //this.deleteDatabase(this.getResources().getString(R.string.app_name));
        if(!Utils.existsDB(this) && !Utils.hasInternetConnection(this)){
            Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            finish();
        }
        DescargarBBDD progressDialog = new DescargarBBDD(this);
        progressDialog.execute();
    }

    /**
     * Se encarga de la navegación a través de la barra de navegaciónd e su izquierda
     *
     * @param position
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {

        Fragment fragment = null;
        switch (position) {
            case Constants.DRAWER_UNDEFINED:
                break;
            case Constants.DRAWER_INITIAL:
                fragment = new InicioGlobal();
                break;
            case Constants.DRAWER_CHAMPION:
                fragment = new CampeonesGlobal();
                break;
            case Constants.DRAWER_OBJECT:
                fragment = new ObjetosGlobal();
                break;
            default:
                Toast.makeText(this, "Opcion no disponible!", Toast.LENGTH_SHORT).show();
                fragment = new InicioGlobal();
                break;
        }
        if (fragment != null) {
            supportInvalidateOptionsMenu();
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.replace(R.id.container, fragment);
            transaction.addToBackStack(null);
            transaction.commit();
        } else {
            //Si el fragment es nulo mostramos un mensaje de error.
            Log.e("Error ", "Mostrar Fragment " + position);
        }
    }

    /**
     * Se encarga de actualizar el título cuando pulsas otro ítem de la barra de navegación
     *
     * @param number
     */
    public void updateTitle(int number) {
        mTitle = getResources().getStringArray(R.array.titulosMenuIzquierda)[number];
        mNavigationDrawerFragment.getList().setItemChecked(number, true);
        getActionBar().setTitle(mTitle);
    }

    /**
     * Se encarga de crear el menú de opciones
     *
     * @param menu menu que contendrá el menú de opciones
     * @return true si el menú ha podido ser creado, false en caso contrario
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.principal, menu);
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * Acción al pulsar sobre un item de la barra settings
     *
     * @param item item seleccionado
     * @return true si la acción ha podido ser realizada, false en caso contrario
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        if (id == R.id.action_data_refresh || id == R.id.action_image_refresh) {
            if (!Utils.hasInternetConnection(this)){
                Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
            }
            else {
                this.deleteDatabase(this.getResources().getString(R.string.app_name));
                DescargarBBDD progressDialog = new DescargarBBDD(this);
                progressDialog.execute();
                return true;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    /**
     * Se encarga del manejo al seleccionar un campeón en campeones o en inicio
     *
     * @param index Posición del campeón seleccionado
     */
    @Override
    public void onChampionSelected(int index) {
        Bundle args = new Bundle();
        args.putInt("id", index);
        Fragment fragment = new CampeonContenedor();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    /**
     * Se encarga del manejo al seleccionar un objeto en objetos
     *
     * @param index Posición del campeón seleccionado
     */
    @Override
    public void onObjectSelected(int index) {
        Bundle args = new Bundle();
        args.putInt("id", index);
        Fragment fragment = new ObjetoContenedor();
        fragment.setArguments(args);
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.container, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
