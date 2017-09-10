package com.lol.lolpro.app.json.Campeones.Decorator;

import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Image;
import com.lol.lolpro.app.json.Campeones.Info;
import com.lol.lolpro.app.json.Campeones.Passive;
import com.lol.lolpro.app.json.Campeones.Recommended;
import com.lol.lolpro.app.json.Campeones.Skin;
import com.lol.lolpro.app.json.Campeones.Spell;
import com.lol.lolpro.app.json.Campeones.Stats;

import java.util.List;
import java.util.Map;

/**
 * Created by YAIZA on 10/09/2017.
 */

public class ChampionDecorator extends Champion {
    private Champion campeon;
    private boolean gratuito;

    public ChampionDecorator(Champion campeon) {
        this.campeon = campeon;
    }

    public boolean isGratuito() {
        return gratuito;
    }

    public void setGratuito(boolean gratuito) {
        this.gratuito = gratuito;
    }

    @Override
    public Integer getId() {
        return campeon.getId();
    }

    @Override
    public void setId(Integer id) {
        campeon.setId(id);
    }

    @Override
    public String getKey() {
        return campeon.getKey();
    }

    @Override
    public void setKey(String key) {
        campeon.setKey(key);
    }

    @Override
    public String getName() {
        return campeon.getName();
    }

    @Override
    public void setName(String name) {
        campeon.setName(name);
    }

    @Override
    public String getTitle() {
        return campeon.getTitle();
    }

    @Override
    public void setTitle(String title) {
        campeon.setTitle(title);
    }

    @Override
    public Image getImage() {
        return campeon.getImage();
    }

    @Override
    public void setImage(Image image) {
        campeon.setImage(image);
    }

    @Override
    public List<Skin> getSkins() {
        return campeon.getSkins();
    }

    @Override
    public void setSkins(List<Skin> skins) {
        campeon.setSkins(skins);
    }

    @Override
    public String getLore() {
        return campeon.getLore();
    }

    @Override
    public void setLore(String lore) {
        campeon.setLore(lore);
    }

    @Override
    public String getBlurb() {
        return campeon.getBlurb();
    }

    @Override
    public void setBlurb(String blurb) {
        campeon.setBlurb(blurb);
    }

    @Override
    public List<String> getAllytips() {
        return campeon.getAllytips();
    }

    @Override
    public void setAllytips(List<String> allytips) {
        campeon.setAllytips(allytips);
    }

    @Override
    public List<String> getEnemytips() {
        return campeon.getEnemytips();
    }

    @Override
    public void setEnemytips(List<String> enemytips) {
        campeon.setEnemytips(enemytips);
    }

    @Override
    public List<String> getTags() {
        return campeon.getTags();
    }

    @Override
    public void setTags(List<String> tags) {
        campeon.setTags(tags);
    }

    @Override
    public String getPartype() {
        return campeon.getPartype();
    }

    @Override
    public void setPartype(String partype) {
        campeon.setPartype(partype);
    }

    @Override
    public Info getInfo() {
        return campeon.getInfo();
    }

    @Override
    public void setInfo(Info info) {
        campeon.setInfo(info);
    }

    @Override
    public Stats getStats() {
        return campeon.getStats();
    }

    @Override
    public void setStats(Stats stats) {
        campeon.setStats(stats);
    }

    @Override
    public List<Spell> getSpells() {
        return campeon.getSpells();
    }

    @Override
    public void setSpells(List<Spell> spells) {
        campeon.setSpells(spells);
    }

    @Override
    public Passive getPassive() {
        return campeon.getPassive();
    }

    @Override
    public void setPassive(Passive passive) {
        campeon.setPassive(passive);
    }

    @Override
    public List<Recommended> getRecommended() {
        return campeon.getRecommended();
    }

    @Override
    public void setRecommended(List<Recommended> recommended) {
        campeon.setRecommended(recommended);
    }

    @Override
    public Map<String, Object> getAdditionalProperties() {
        return campeon.getAdditionalProperties();
    }

    @Override
    public void setAdditionalProperty(String name, Object value) {
        campeon.setAdditionalProperty(name, value);
    }


}
