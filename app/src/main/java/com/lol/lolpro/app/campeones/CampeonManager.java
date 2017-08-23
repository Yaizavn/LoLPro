package com.lol.lolpro.app.campeones;

import com.lol.lolpro.app.bbdd.BBDDHelper;
import com.lol.lolpro.app.bbdd.DBManager;
import com.lol.lolpro.app.json.Campeones.Champion;

/**
 * Created by sergioiglesiasvelasco on 6/6/17.
 */

public class CampeonManager {

	public Champion getCampeon(int idCampeon) {

		Champion result;

		DBManager.getInstance().openDatabase(false);
		BBDDHelper dbHelper = DBManager.getInstance().getDatabaseHelper();
		result = dbHelper.obtenerCampeon(idCampeon);
		DBManager.getInstance().closeDatabase(false);

		return result;

	}

}
