package com.shibuyaxpress.asistenciadealumnostecsup.Clases;

/**
 * Created by paulf on 6/17/2017.
 */

public class Horario {
    String aula;
    String curso;
    String horario_clase;
    String id;
    String profesor;

    public Horario() {
    }

    public String getAula() {
        return aula;
    }

    public void setAula(String aula) {
        this.aula = aula;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public String getHorario_clase() {
        return horario_clase;
    }

    public void setHorario_clase(String horario_clase) {
        this.horario_clase = horario_clase;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }
}
