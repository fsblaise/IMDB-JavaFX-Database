package kotprog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DijakDAO implements DAO {
    private Dijak createDijak(ResultSet rs) {
        Dijak p = new Dijak();
        try {
            p.setSzineszid(rs.getInt("szineszid"));
            p.setDij(rs.getString("dij"));
            p.setDarab(rs.getInt("darab"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    private Dijak createDijak2(ResultSet rs) {
        Dijak p = new Dijak();
        try {
            p.setSzineszid(rs.getInt("szineszid"));
            p.setSzinesznev(rs.getString("szinesz.nev"));
            p.setDij(rs.getString("dij"));
            p.setDarab(rs.getInt("darab"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Dijak> getDijak() {
        String sql = "Select * from dijak group by dij order by dij";
        List<Dijak> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Dijak p = createDijak(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Dijak> getDijakForNev(String name) {
        String sql = "Select szinesz.szineszid, szinesz.nev, dij, darab from szinesz, dijak where szinesz.szineszid = dijak.szineszid and dij like '%" + name + "%'";
        List<Dijak> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Dijak p = createDijak2(rs);
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