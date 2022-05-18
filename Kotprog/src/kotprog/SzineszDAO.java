package kotprog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SzineszDAO implements DAO {
    private Szinesz createSzinesz(ResultSet rs) {
        Szinesz p = new Szinesz();
        try {
            p.setSzineszid(rs.getInt("szineszid"));
            p.setNev(rs.getString("nev"));
            p.setSzuldatum(rs.getDate("szuldatum"));
            p.setEletkor(rs.getInt("eletkor"));
            p.setElhunyt(rs.getDate("elhunyt"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Szinesz> getSzinesz() {
        String sql = "Select * from szinesz order by nev";
        List<Szinesz> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Szinesz p = createSzinesz(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Szinesz> getSzineszForNev(String name) {
        String sql = "Select * from szinesz where nev like '%" + name + "%'";
        List<Szinesz> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Szinesz p = createSzinesz(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public void insertSzinesz(String[] values){
        String sql = "Insert into szinesz values (" + values[0] + ", '" + values[1] + "', '" + values[2] + "', " + values[3] + ")";
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void removeSzinesz(String id){
        String sql = "DELETE FROM szinesz WHERE szineszid = " + id;
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            stmt.executeUpdate(sql);
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }

    public void editSzinesz(String id, String[] values){
        try {
            int i = 0;
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            if (!Objects.equals(values[0], "")){
                String sql = "UPDATE szinesz SET szineszid = " + values[0] + " WHERE szineszid = " + id;
                stmt.executeUpdate(sql);
            }
            if (!Objects.equals(values[0], "")){
                String sql = "UPDATE szinesz SET nev = '" + values[1] + "' WHERE szineszid = " + id;
                stmt.executeUpdate(sql);
            }
            if (!Objects.equals(values[0], "")){
                String sql = "UPDATE szinesz SET szuldatum = '" + values[2] + "' WHERE szineszid = " + id;
                stmt.executeUpdate(sql);
            }
            if (!Objects.equals(values[0], "")){
                String sql = "UPDATE szinesz SET eletkor = " + values[3] + " WHERE szineszid = " + id;
                stmt.executeUpdate(sql);
            }
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
    }
}