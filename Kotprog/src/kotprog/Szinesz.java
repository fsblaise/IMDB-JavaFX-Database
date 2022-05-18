package kotprog;

import java.util.Date;
public class Szinesz {
    private int szineszid;
    private String nev;
    private Date szuldatum;
    private int eletkor;
    private Date elhunyt;

    public Szinesz() {
    }

    public int getSzineszid() {
        return szineszid;
    }

    public void setSzineszid(int szineszid) {
        this.szineszid = szineszid;
    }

    public String getNev() {
        return nev;
    }

    public void setNev(String nev) {
        this.nev = nev;
    }

    public Date getSzuldatum() {
        return szuldatum;
    }

    public void setSzuldatum(Date szuldatum) {
        this.szuldatum = szuldatum;
    }

    public int getEletkor() {
        return eletkor;
    }

    public void setEletkor(int eletkor) {
        this.eletkor = eletkor;
    }

    public Date getElhunyt() {
        return elhunyt;
    }

    public void setElhunyt(Date elhunyt) {
        this.elhunyt = elhunyt;
    }
}