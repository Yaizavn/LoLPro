package com.lol.lolpro.app.web;

import java.util.regex.Pattern;

/**
 * Created by sergio on 31/05/14.
 */
public class Patrones {

    //TODO añadir alcance...
    private final static String pCampeones = "id\":([0-9]+).*?name\":\"(.+?)\".*?title\":\"(.+?)\",.*?armor\":([0-9]+\\.[0-9]+|[0-9]+).*?attackdamage\":([0-9]+\\.[0-9]+|[0-9]+).*?attackspeedoffset\":(-?0\\.[0-9]+|0).*?hp\":([0-9]+).*?hpregen\":([0-9]+\\.[0-9]+|[0-9]+).*?movespeed\":([0-9]+).*?spellblock\":([0-9]+)";    public final static Pattern PATRON_CAMPEONES = Pattern.compile(pCampeones, Pattern.DOTALL);
}
