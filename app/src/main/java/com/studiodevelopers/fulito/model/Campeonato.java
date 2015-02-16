package com.studiodevelopers.fulito.model;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Ignacio on 06/02/2015.
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class Campeonato implements Serializable {
    private static final long serialVersionUID = 1L;
    @JsonProperty("NombreCampeonato")
    private String nameCampeonato;
    @JsonProperty("Imagen")
    private  String imagenCampeonato;
    @JsonProperty("Equipos")
    private List<Equipo> equiposCampeonato;

    public List<Equipo> getEquiposCampeonato() {
        return equiposCampeonato;
    }

    public void setEquiposCampeonato(List<Equipo> equiposCampeonato) {
        this.equiposCampeonato = equiposCampeonato;
    }

    public String getImagenCampeonato() {
        return imagenCampeonato;
    }

    public void setImagenCampeonato(String imagenCampeonato) {
        this.imagenCampeonato = imagenCampeonato;
    }

    public String getNameCampeonato() {
        return nameCampeonato;
    }

    public void setNameCampeonato(String nameCampeonato) {
        this.nameCampeonato = nameCampeonato;
    }


}
