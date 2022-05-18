package kotprog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SzereplesekDAO implements DAO {
    private Szereplesek createSzereplesek(ResultSet rs) {
        Szereplesek p = new Szereplesek();
        try {
            p.setSzineszid(rs.getInt("szineszid"));
            p.setFilmid(rs.getInt("filmid"));
            p.setSzerep(rs.getString("szerep"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    private Szereplesek createSzereplesek2(ResultSet rs) {
        Szereplesek p = new Szereplesek();
        try {
            p.setSzineszid(rs.getInt("szineszid"));
            p.setSzinesznev(rs.getString("szinesz.nev"));
            p.setFilmid(rs.getInt("filmid"));
            p.setFilmcim(rs.getString("film.cim"));
            p.setSzerep(rs.getString("szerep"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Szereplesek> getSzereplesek() {
        String sql = "Select * from szereplesek order by szerep";
        List<Szereplesek> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Szereplesek p = createSzereplesek(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Szereplesek> getSzereplesekForNev(String name) {
        String sql = "Select Szinesz.szineszid, szinesz.nev, Film.filmid, film.cim, szerep from Szereplesek, Film, Szinesz where Szereplesek.szineszid = Szinesz.szineszid and Szereplesek.filmid = Film.filmid and szerep like '%" + name + "%'";
        List<Szereplesek> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Szereplesek p = createSzereplesek2(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}