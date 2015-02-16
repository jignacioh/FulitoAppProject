package com.studiodevelopers.fulito.model;

/**
 * Created by Ignacio on 13/02/2015.
 */
public class GenericItem {
    private String tittle;
    private String subtittle;
    private int image;

    public GenericItem(String tittle, int image) {
        this.tittle = tittle;
        this.image = image;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getSubtittle() {
        return subtittle;
    }

    public void setSubtittle(String subtittle) {
        this.subtittle = subtittle;
    }

    public int getImage() {
        return image;
    }

    public void setImage(int image) {
        this.image = image;
    }
}
