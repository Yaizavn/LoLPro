
package com.lol.lolpro.app.json.Campeones;

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
    "name",
    "description",
    "sanitizedDescription",
    "image"
})
public class Passive implements Parcelable {

    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("sanitizedDescription")
    private String sanitizedDescription;
    @JsonProperty("image")
    private Image image;
    @JsonIgnore
    private Map<String, Object> additionalProperties = new HashMap<String, Object>();

    /**
     * 
     * @return
     *     The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     * 
     * @param name
     *     The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 
     * @return
     *     The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     * 
     * @param description
     *     The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * 
     * @return
     *     The sanitizedDescription
     */
    @JsonProperty("sanitizedDescription")
    public String getSanitizedDescription() {
        return sanitizedDescription;
    }

    /**
     * 
     * @param sanitizedDescription
     *     The sanitizedDescription
     */
    @JsonProperty("sanitizedDescription")
    public void setSanitizedDescription(String sanitizedDescription) {
        this.sanitizedDescription = sanitizedDescription;
    }

    /**
     * 
     * @return
     *     The image
     */
    @JsonProperty("image")
    public Image getImage() {
        return image;
    }

    /**
     * 
     * @param image
     *     The image
     */
    @JsonProperty("image")
    public void setImage(Image image) {
        this.image = image;
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
        dest.writeString(this.name);
        dest.writeString(this.description);
        dest.writeString(this.sanitizedDescription);
        dest.writeParcelable(this.image, flags);
        dest.writeValue(this.additionalProperties);
    }

    public Passive() {
    }

    protected Passive(Parcel in) {
        this.name = in.readString();
        this.description = in.readString();
        this.sanitizedDescription = in.readString();
        this.image = in.readParcelable(Image.class.getClassLoader());
        this.additionalProperties = ((Map<String, Object> ) in.readValue((Map.class.getClassLoader())));
    }

    public static final Parcelable.Creator<Passive> CREATOR = new Parcelable.Creator<Passive>() {
        @Override
        public Passive createFromParcel(Parcel source) {
            return new Passive(source);
        }

        @Override
        public Passive[] newArray(int size) {
            return new Passive[size];
        }
    };
}
