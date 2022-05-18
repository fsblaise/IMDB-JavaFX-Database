package kotprog;

import java.util.Date;

public class Film {
    private int filmid;
    private String cim;
    private Date megjelenes;
    private float ertekeles;
    private int hossz;
    private int studioid;
    private double koltseg;
    private int oscarszam;

    public Film() {
    }

    public int getFilmid() {
        return filmid;
    }

    public void setFilmid(int filmid) {
        this.filmid = filmid;
    }

    public String getCim() {
        return cim;
    }

    public void setCim(String cim) {
        this.cim = cim;
    }

    public Date getMegjelenes() {
        return megjelenes;
    }

    public void setMegjelenes(Date megjelenes) {
        this.megjelenes = megjelenes;
    }

    public float getErtekeles() {
        return ertekeles;
    }

    public void setErtekeles(float ertekeles) {
        this.ertekeles = ertekeles;
    }

    public int getHossz() {
        return hossz;
    }

    public void setHossz(int hossz) {
        this.hossz = hossz;
    }

    public int getStudioid() {
        return studioid;
    }

    public void setStudioid(int studioid) {
        this.studioid = studioid;
    }

    public double getKoltseg() {
        return koltseg;
    }

    public void setKoltseg(double koltseg) {
        this.koltseg = koltseg;
    }

    public int getOscarszam() {return oscarszam;};

    public void setOscarszam(int oscarszam) {
        this.oscarszam = oscarszam;
    }
}
