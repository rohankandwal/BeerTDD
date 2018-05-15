
package com.itcse.beerrecepies.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Fermentation {

    @SerializedName("temp")
    @Expose
    public Temp temp;

}
