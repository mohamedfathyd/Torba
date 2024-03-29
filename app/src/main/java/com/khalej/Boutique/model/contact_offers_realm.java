package com.khalej.Boutique.model;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;

public class contact_offers_realm extends RealmObject {
    @SerializedName("id")
    int id;
    @SerializedName("name")
    String name;
    @SerializedName("nameca")
    String nameca;
    @SerializedName("image")
    String Img;


   @SerializedName("details")
   String description;
    @SerializedName("newprice")
    String price;
    @SerializedName("oldprice")
    String oldprice;

    public String getOldprice() {
        return oldprice;
    }

    public void setOldprice(String oldprice) {
        this.oldprice = oldprice;
    }

    public String getNameca() {
        return nameca;
    }

    public void setNameca(String nameca) {
        this.nameca = nameca;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }


    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getname() {
        return name;
    }

    public void setname(String name) {
        this.name = name;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }
}
