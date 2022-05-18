package kotprog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmDAO implements DAO {
    private Film createFilm(ResultSet rs) {
        Film p = new Film();
        try {
            p.setFilmid(rs.getInt("Filmid"));
            p.setCim(rs.getString("cim"));
            p.setMegjelenes(rs.getDate("megjelenes"));
            p.setErtekeles(rs.getFloat("ertekeles"));
            p.setHossz(rs.getInt("hossz"));
            p.setStudioid(rs.getInt("studioid"));
            p.setKoltseg(rs.getInt("koltseg"));
            p.setOscarszam(rs.getInt("oscarszam"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Film> getFilm() {
        String sql = "Select * from film order by cim";
        List<Film> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Film p = createFilm(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Film> getFilmForNev(String name) {
        String sql = "Select * from film where cim like '%" + name + "%'";
        List<Film> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Film p = createFilm(rs);
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