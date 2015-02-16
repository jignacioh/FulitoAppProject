package com.studiodevelopers.fulito.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;

/**
 * Created by Ignacio on 06/02/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Equipo implements Serializable{
    private static final long serialVersionUID = 1L;
    @JsonProperty("Name")
    private String nameEquipo;
    //@JsonProperty("NombreLargo")
    // private String nameEquipoLargo;
    @JsonProperty("Imagen")
    private String imagenEquipo;
    //@JsonProperty("Tecnico")
    //private String tecnicoEquipo;
    //@JsonProperty("Lastupdate")
    //private String lastUpdate;
    @JsonProperty("Grupo")
    private String grupo;

    public String getGrupo() {
        return grupo;
    }

    public void setGrupo(String grupo) {
        this.grupo = grupo;
    }

    public String getNameEquipo() {
        return nameEquipo;
    }

    public void setNameEquipo(String nameEquipo) {
        this.nameEquipo = nameEquipo;
    }

    //public String getNameEquipoLargo() {
    //   return nameEquipoLargo;
    //}

    // public void setNameEquipoLargo(String nameEquipoLargo) {
    //    this.nameEquipoLargo = nameEquipoLargo;
    //}

    // public String getTecnicoEquipo() {
    //     return tecnicoEquipo;
    // }

    //public void setTecnicoEquipo(String tecnicoEquipo) {
    //    this.tecnicoEquipo = tecnicoEquipo;
    //}

    // public String getLastUpdate() {
    //     return lastUpdate;
    // }

    // public void setLastUpdate(String lastUpdate) {
    //     this.lastUpdate = lastUpdate;
    // }

    public String getImagenEquipo() {
        return imagenEquipo;
    }

    public void setImagenEquipo(String imagenEquipo) {
        this.imagenEquipo = imagenEquipo;
    }
}
