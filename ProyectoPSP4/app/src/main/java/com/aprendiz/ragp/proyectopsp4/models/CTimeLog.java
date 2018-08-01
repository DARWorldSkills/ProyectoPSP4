package com.aprendiz.ragp.proyectopsp4.models;

public class CTimeLog {
    private int id;
    private String phase;
    private String start;
    private int interrupcions;
    private String stop;
    private int delta;
    private String comments;
    private int proyecto;

    public CTimeLog() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public int getInterrupcions() {
        return interrupcions;
    }

    public void setInterrupcions(int interrupcions) {
        this.interrupcions = interrupcions;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public int getDelta() {
        return delta;
    }

    public void setDelta(int delta) {
        this.delta = delta;
    }

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    public int getProyecto() {
        return proyecto;
    }

    public void setProyecto(int proyecto) {
        this.proyecto = proyecto;
    }
}
