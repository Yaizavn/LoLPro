package com.lol.lolpro.app.web;

import java.util.regex.Pattern;

/**
 * Created by sergio on 31/05/14.
 */
public class Patrones {

    private final static String PCHAMPION = "id\":([0-9]+).*?name\":\"(.+?)\".*?title\":\"(.+?)\".*?full\":\"(.+?)\".*?skins\":\\[(.+?)\\].*?lore\":\"(.+?)\".*?partype\":\"(.+?)\".*?armor\":([0-9]+\\.[0-9]+|[0-9]+).*?armorperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?attackdamage\":([0-9]+\\.[0-9]+|[0-9]+).*?attackdamageperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?attackrange\":([0-9]+\\.[0-9]+|[0-9]+).*?attackspeedoffset\":(-?0\\.[0-9]+|0).*?attackspeedperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?crit\":([0-9]+\\.[0-9]+|[0-9]+).*?critperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?hp\":([0-9]+\\.[0-9]+|[0-9]+).*?hpperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?hpregen\":([0-9]+\\.[0-9]+|[0-9]+).*?hpregenperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?movespeed\":([0-9]+\\.[0-9]+|[0-9]+).*?mp\":([0-9]+\\.[0-9]+|[0-9]+).*?mpperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?mpregen\":([0-9]+\\.[0-9]+|[0-9]+).*?mpregenperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?spellblock\":([0-9]+\\.[0-9]+|[0-9]+).*?spellblockperlevel\":([0-9]+\\.[0-9]+|[0-9]+).*?spells\":\\[(.+?)\\],\"passive\":\\{(.+?)\\}";
    public final static Pattern PATTERN_CHAMPION = Pattern.compile(PCHAMPION, Pattern.DOTALL);
    private final static String PPATHVERSIONS = "item\":\"(.*?)\".*?champion\":\"(.*?)\".*?cdn\":\"(.*?)\"";
    public final static Pattern PATTERN_PATH_AND_VERSIONS = Pattern.compile(PPATHVERSIONS, Pattern.DOTALL);
    private final static String PITEMS = "\"[0-9]+\":\\{\"id\":([0-9]+).*?name\":\"(.+?)\".*?base\":([0-9]+).*?total\":([0-9]+).*?purchasable\":(true|false).*?description\":\"(.+?)\".*?full\":\"(.*?)\"";
    public final static Pattern PATTERN_ITEMS = Pattern.compile(PITEMS, Pattern.DOTALL);
    private final static String PCHAMPIONFREE = "\"id\":([0-9]+)";
    public final static Pattern PATTERN_CHAMPION_FREE = Pattern.compile(PCHAMPIONFREE, Pattern.DOTALL);
}
