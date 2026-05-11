package com.gestionestudiantesmedicina.entities;

public class What {
    private String w;
    private String hola;
    private int sf;

    public What(String w, String hola, int sf) {
        this.w = w;
        this.hola = hola;
        this.sf = sf;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getHola() {
        return hola;
    }

    public void setHola(String hola) {
        this.hola = hola;
    }

    public int getSf() {
        return sf;
    }
    
    public void setSf(int sf) {
        this.sf = sf;
    }
    
}
