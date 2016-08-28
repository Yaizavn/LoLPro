
package com.lol.lolpro.app.json.Objetos;

import java.util.HashMap;
import java.util.Map;
import javax.annotation.Generated;
import com.fasterxml.jackson.annotation.JsonAnyGetter;
import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Generated("org.jsonschema2pojo")

public class Data {

    private Map<String, Item> mItems = new HashMap<String, Item>();

    @JsonAnyGetter
    public Map<String, Item> getMItems() {
        return this.mItems;
    }

    @JsonAnySetter
    public void setMItems(String name, Item value) {
        this.mItems.put(name, value);
    }


}
