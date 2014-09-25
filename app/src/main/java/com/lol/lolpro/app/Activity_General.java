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
import com.lol.lolpro.app.bbdd.dFragment;
import com.lol.lolpro.app.campeones.CampeonContenedor;
import com.lol.lolpro.app.campeones.CampeonesGlobal;
import com.lol.lolpro.app.inicio.InicioGlobal;
import com.lol.lolpro.app.objetos.ObjetoContenedor;
import com.lol.lolpro.app.objetos.ObjetoGeneral;
import com.lol.lolpro.app.objetos.ObjetosGlobal;
import com.lol.lolpro.app.utillidades.Champion_callback;
import com.lol.lolpro.app.utillidades.Constants;
import com.lol.lolpro.app.utillidades.Object_callback;
import com.lol.lolpro.app.utillidades.Utils;

/**
 * Activity principal en el que se colocarán todos los fragments
 */
public class Activity_General extends ActionBarActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks,
        Champion_callback, Object_callback{

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;
    private CharSequence mTitle;

    /**
     * Se encarga de inicializar el activity para que contenga lo necesario para poder dibujar los fragments
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //TODO initializeInstance y guardarla para no eliminarla al rotar
        //TODO guardar campeonesgratuitos para eliminar setWriteAheadLoggingEnabled(true) -> optimizar memoria y eliminar concurrencia de threads
        DBManager.initializeInstance(this);
        setContentView(R.layout.activity_general);
        if (savedInstanceState == null) {
            //this.deleteDatabase(this.getResources().getString(R.string.app_name));
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.container, new InicioGlobal())
                    .commit();

            // Descargamos la bbdd o cerramos la app
            if(!Utils.existsDB(this) && !Utils.hasInternetConnection(this)){
                Toast.makeText(this, getResources().getString(R.string.no_internet), Toast.LENGTH_SHORT).show();
                finish();
            }
            else if(Utils.hasInternetConnection(this)){
                dFragment dFrag = new dFragment(new DescargarBBDD(this));
                // And create a task for it to monitor. In this implementation the taskFragment
                // executes the task, but you could change it so that it is started here.
                // And tell it to call onActivityResult() on this fragment.

                // Show the fragment.
                // I'm not sure which of the following two lines is best to use but this one works well.
                dFrag.show(getSupportFragmentManager(), "aa");
            }
        }
        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
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
                Toast.makeText(this, getString(R.string.option_not_available), Toast.LENGTH_SHORT).show();
                Log.e(getString(R.string.error), getString(R.string.fragment_not_found, position));
                break;
        }
        if (fragment != null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                    .addToBackStack(null).commit();
        }
    }

    /**
     * Se encarga de actualizar el título cuando pulsas otro ítem de la barra de navegación
     *
     * @param position
     */
    public void updateTitle(int position) {
        mTitle = getResources().getStringArray(R.array.titulosMenuIzquierda)[position];
        mNavigationDrawerFragment.getList().setItemChecked(position, true);
        getActionBar().setTitle(mTitle);
    }

    /**
     * Se encarga de crear el menú de opciones
     *
     * @param menu menu que contendrá el menú de opciones
     * @return true si el menú ha podido ser creado, false en caso contrario

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.

            if(mNavigationDrawerFragment.getList().getSelectedItemPosition() == Constants.DRAWER_INITIAL) {
                //Show menu based on the actual view
                getMenuInflater().inflate(R.menu.principal, menu);
                return true;
            }
        }
        return super.onCreateOptionsMenu(menu);
    }*/

    /**
     * Se encarga del manejo al seleccionar un campeón en un fragment
     *
     * @param index Posición del campeón seleccionado
     */
    @Override
    public void onChampionSelected(int index) {
        Bundle args = new Bundle();
        args.putInt("id", index);
        Fragment fragment = new CampeonContenedor();
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }

    /**
     * Se encarga del manejo al seleccionar un objeto en un fragment
     *
     * @param index Posición del campeón seleccionado
     */
    @Override
    public void onObjectSelected(int index) {
        Bundle args = new Bundle();
        args.putInt("id", index);
        Fragment fragment = new ObjetoContenedor();
        fragment.setArguments(args);
        getSupportFragmentManager().beginTransaction().replace(R.id.container, fragment)
                .addToBackStack(null).commit();
    }
}
