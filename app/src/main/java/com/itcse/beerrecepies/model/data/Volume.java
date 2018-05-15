
package com.itcse.beerrecepies.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Volume {

    @SerializedName("value")
    @Expose
    public Integer value;
    @SerializedName("unit")
    @Expose
    public String unit;

}
