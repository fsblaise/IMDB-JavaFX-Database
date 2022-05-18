package kotprog;

import com.mysql.cj.protocol.Resultset;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class QueryDAO implements DAO {
    private Query createQuery(ResultSet rs) {
        Query p = new Query();
        try {
            p.setMufaj(rs.getString("mufaj"));
            p.setErtekeles(rs.getFloat("AVG(ertekeles)"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    private Query createQuery2(ResultSet rs) {
        Query p = new Query();
        try {
            p.setSzinesznev(rs.getString("szinesz.nev"));
            p.setDarab(rs.getInt("SUM(darab)"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    private Query createQuery3(ResultSet rs) {
        Query p = new Query();
        try {
            p.setSzinesznev(rs.getString("szinesz.nev"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Query> getQuery() {
        List<Query> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String sql = "SELECT mufaj, AVG(ertekeles) " +
                    "FROM mufajok, film " +
                    "WHERE mufajok.filmid = film.filmid " +
                    "GROUP BY mufaj " +
                    "ORDER BY AVG(ertekeles) DESC;";
            ResultSet rs = stmt.executeQuery(sql);
            Query p = null;
            while (rs.next()) {
                p = createQuery(rs);
                list.add(p);
            }
            assert p != null;
            p.setNev("1. lekérdezés");

            sql = "SELECT nev, SUM(darab) " +
                    "FROM szinesz, dijak " +
                    "WHERE szinesz.szineszid = dijak.szineszid " +
                    "GROUP BY nev " +
                    "ORDER BY SUM(darab) DESC;";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = createQuery2(rs);
                list.add(p);
            }
            assert p != null;
            p.setNev("2. lekérdezés");

            sql = "SELECT szinesz.nev " +
                    "FROM szinesz,szereplesek,film " +
                    "WHERE szinesz.szineszid = szereplesek.szineszid and szereplesek.filmid = film.filmid and szereplesek.tipus = 'főszereplő' and film.ertekeles = ( " +
                    "SELECT max(ertekeles) " +
                    "FROM film " +
                    "WHERE megjelenes BETWEEN '2017-01-01' and '2017-12-31');";
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
                p = createQuery3(rs);
                list.add(p);
            }
            assert p != null;
            p.setNev("3. lekérdezés");
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Query> getQueryForNev(String name) {
        List<Query> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            String sql;
            ResultSet rs;
            if(Objects.equals(name, "1. lekérdezés")){
                sql = "SELECT mufaj, AVG(ertekeles) " +
                        "FROM mufajok, film " +
                        "WHERE mufajok.filmid = film.filmid " +
                        "GROUP BY mufaj " +
                        "ORDER BY AVG(ertekeles) DESC;";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Query p = createQuery(rs);
                    list.add(p);
                }
                rs.close();
            }
            else if (Objects.equals(name, "2. lekérdezés")){
                sql = "SELECT nev, SUM(darab) " +
                        "FROM szinesz, dijak " +
                        "WHERE szinesz.szineszid = dijak.szineszid " +
                        "GROUP BY nev " +
                        "ORDER BY SUM(darab) DESC;";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Query p = createQuery2(rs);
                    list.add(p);
                }
                rs.close();
            }
            else if (Objects.equals(name, "3. lekérdezés")){
                sql = "SELECT szinesz.nev " +
                        "FROM szinesz,szereplesek,film " +
                        "WHERE szinesz.szineszid = szereplesek.szineszid and szereplesek.filmid = film.filmid and szereplesek.tipus = 'főszereplő' and film.ertekeles = ( " +
                        "SELECT max(ertekeles) " +
                        "FROM film " +
                        "WHERE megjelenes BETWEEN '2017-01-01' and '2017-12-31');";
                rs = stmt.executeQuery(sql);
                while (rs.next()) {
                    Query p = createQuery3(rs);
                    list.add(p);
                }
                rs.close();
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }
}