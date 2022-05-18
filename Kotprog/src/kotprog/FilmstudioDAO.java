package kotprog;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FilmstudioDAO implements DAO {
    private Filmstudio createFilmstudio(ResultSet rs) {
        Filmstudio p = new Filmstudio();
        try {
            p.setStudioid(rs.getInt("studioid"));
            p.setNev(rs.getString("nev"));
            p.setAlapitas(rs.getString("alapitas"));
            p.setOscarszam(rs.getInt("oscarszam"));
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        return p;
    }
    public List<Filmstudio> getFilmstudio() {
        String sql = "Select * from filmstudio order by nev";
        List<Filmstudio> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Filmstudio p = createFilmstudio(rs);
                list.add(p);
            }
            rs.close();
            con.close();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
        }
        return list;
    }

    public List<Filmstudio> getFilmstudioForNev(String name) {
        String sql = "Select * from filmstudio where nev like '%" + name + "%'";
        List<Filmstudio> list = new ArrayList<>();
        try {
            Class.forName(DRIVER);
            Connection con = DriverManager.getConnection(DB_URL, USER, PASS);
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            while (rs.next()) {
                Filmstudio p = createFilmstudio(rs);
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