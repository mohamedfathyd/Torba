package com.khalej.Boutique.model;
import com.google.gson.annotations.SerializedName;

import io.realm.RealmObject;


public class contact_images_realm  extends RealmObject {
    @SerializedName("id")
    int id;
    @SerializedName("category_id")
    String name;
    @SerializedName("image")
    String Img;

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
