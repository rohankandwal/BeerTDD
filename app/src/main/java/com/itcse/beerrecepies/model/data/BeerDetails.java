
package com.itcse.beerrecepies.model.data;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class BeerDetails {

    @SerializedName("id")
    @Expose
    public Integer id;
    @SerializedName("name")
    @Expose
    public String name;
    @SerializedName("tagline")
    @Expose
    public String tagline;
    @SerializedName("first_brewed")
    @Expose
    public String first_brewed;
    @SerializedName("description")
    @Expose
    public String description;
    @SerializedName("image_url")
    @Expose
    public String image_url;
    @SerializedName("abv")
    @Expose
    public Double abv;
    @SerializedName("ibu")
    @Expose
    public Double ibu;
    @SerializedName("target_fg")
    @Expose
    public Double target_fg;
    @SerializedName("target_og")
    @Expose
    public Double target_og;
    @SerializedName("ebc")
    @Expose
    public Double ebc;
    @SerializedName("srm")
    @Expose
    public Double srm;
    @SerializedName("ph")
    @Expose
    public Double ph;
    @SerializedName("attenuation_level")
    @Expose
    public Double attenuation_level;
    @SerializedName("volume")
    @Expose
    public Volume volume;
    @SerializedName("boil_volume")
    @Expose
    public Boil_volume boil_volume;
    @SerializedName("method")
    @Expose
    public Method method;
    @SerializedName("ingredients")
    @Expose
    public Ingredients ingredients;
    @SerializedName("food_pairing")
    @Expose
    public List<String> food_pairing = null;
    @SerializedName("brewers_tips")
    @Expose
    public String brewers_tips;
    @SerializedName("contributed_by")
    @Expose
    public String contributed_by;

}
