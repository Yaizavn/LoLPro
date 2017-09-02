package com.lol.lolpro.app.json.Campeones.Decorator;

import android.os.Parcel;
import android.os.Parcelable;

import com.lol.lolpro.app.json.Campeones.Champion;
import com.lol.lolpro.app.json.Campeones.Image;
import com.lol.lolpro.app.json.Campeones.Info;
import com.lol.lolpro.app.json.Campeones.Passive;
import com.lol.lolpro.app.json.Campeones.Recommended;
import com.lol.lolpro.app.json.Campeones.Skin;
import com.lol.lolpro.app.json.Campeones.Spell;
import com.lol.lolpro.app.json.Campeones.Stats;

import java.util.ArrayList;
import java.util.Map;

/**
 * Created by sergioiglesiasvelasco on 3/9/16.
 */

public class SkinDecorator extends Skin implements Parcelable {
    private Skin skin;
    private String ruta;

    public SkinDecorator(Skin skin) {
        this.skin = skin;
    }

    @Override
    public Integer getId() {
        return skin.getId();
    }

    @Override
    public String getName() {
        return skin.getName();
    }

    @Override
    public Integer getNum() {
        return skin.getNum();
    }

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeParcelable(this.skin, flags);
        dest.writeString(this.ruta);
    }

    protected SkinDecorator(Parcel in) {
        this.skin = in.readParcelable(Skin.class.getClassLoader());
        this.ruta = in.readString();
    }

    public static final Parcelable.Creator<SkinDecorator> CREATOR = new Parcelable.Creator<SkinDecorator>() {
        @Override
        public SkinDecorator createFromParcel(Parcel source) {
            return new SkinDecorator(source);
        }

        @Override
        public SkinDecorator[] newArray(int size) {
            return new SkinDecorator[size];
        }
    };
}
