
package com.itcse.beerrecepies.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Mash_temp {

    @SerializedName("temp")
    @Expose
    public Temp temp;
    @SerializedName("duration")
    @Expose
    public Integer duration;

}
