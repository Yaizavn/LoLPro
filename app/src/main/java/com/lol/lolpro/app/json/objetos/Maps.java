
package com.lol.lolpro.app.json.Objetos;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")
@JsonPropertyOrder({
    "1",
    "12",
    "8",
    "10"
})
public class Maps implements Parcelable {

    @JsonProperty("1")
    private Boolean _1;
    @JsonProperty("12")
    private Boolean _12;
    @JsonProperty("8")
    private Boolean _8;
    @JsonProperty("10")
    private Boolean _10;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The _1
     */
    @JsonProperty("1")
    public Boolean get1() {
        return _1;
    }

    /**
     * 
     * @param _1
     *     The 1
     */
    @JsonProperty("1")
    public void set1(Boolean _1) {
        this._1 = _1;
    }

    /**
     * 
     * @return
     *     The _12
     */
    @JsonProperty("12")
    public Boolean get12() {
        return _12;
    }

    /**
     * 
     * @param _12
     *     The 12
     */
    @JsonProperty("12")
    public void set12(Boolean _12) {
        this._12 = _12;
    }

    /**
     * 
     * @return
     *     The _8
     */
    @JsonProperty("8")
    public Boolean get8() {
        return _8;
    }

    /**
     * 
     * @param _8
     *     The 8
     */
    @JsonProperty("8")
    public void set8(Boolean _8) {
        this._8 = _8;
    }

    /**
     * 
     * @return
     *     The _10
     */
    @JsonProperty("10")
    public Boolean get10() {
        return _10;
    }

    /**
     * 
     * @param _10
     *     The 10
     */
    @JsonProperty("10")
    public void set10(Boolean _10) {
        this._10 = _10;
    }

    @JsonAnyGetter
    public Map<String, Object> getAdditionalProperties() {
        return this.additionalProperties;
    }

    @JsonAnySetter
    public void setAdditionalProperty(String name, Object value) {
        this.additionalProperties.put(name, value);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(this._1);
        dest.writeValue(this._12);
        dest.writeValue(this._8);
        dest.writeValue(this._10);
        dest.writeValue(this.additionalProperties);
    }

    public Maps() {
    }

    protected Maps(Parcel in) {
        this._1 = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this._12 = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this._8 = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this._10 = (Boolean) in.readValue(Boolean.class.getClassLoader());
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public static final Parcelable.Creator<Maps> CREATOR = new Parcelable.Creator<Maps>() {
        @Override
        public Maps createFromParcel(Parcel source) {
            return new Maps(source);
        }

        @Override
        public Maps[] newArray(int size) {
            return new Maps[size];
        }
    };
}
