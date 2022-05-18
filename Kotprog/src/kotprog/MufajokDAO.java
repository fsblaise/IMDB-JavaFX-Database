package kotprog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MufajokDAO implements DAO {
    private Mufajok createMufajok(ResultSet rs) {
        Mufajok p = new Mufajok();
        try {
            p.setFilmid(rs.getInt("filmid"));
            p.setMufaj(rs.getString("mufaj"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    private Mufajok createMufajok2(ResultSet rs) {
        Mufajok p = new Mufajok();
        try {
            p.setFilmid(rs.getInt("filmid"));
            p.setFilmcim(rs.getString("film.cim"));
            p.setMufaj(rs.getString("mufaj"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Mufajok> getMufajok() {
        String sql = "Select * from mufajok group by mufaj order by mufaj";
        List<Mufajok> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Mufajok p = createMufajok(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Mufajok> getMufajokForNev(String name) {
        String sql = "Select film.filmid, film.cim, mufaj from mufajok, film where film.filmid = mufajok.filmid and mufaj like '%" + name + "%'";
        List<Mufajok> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Mufajok p = createMufajok2(rs);
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