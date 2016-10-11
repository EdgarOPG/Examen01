/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.videoclub.conexiones;

/**
 *
 * @author edgar
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conexion a base deda tos MYSQL que utuliza un singleton
 * @author Luis Antonio Ramirez Martinez
 */
public class Conexion {

    private final static String USUARIO = "root";
    private final static String PASSWORD = "1234";
    private final static String CONEXION = 
            "jdbc:mysql://localhost:3306/mydb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
    private static Conexion INSTANCE;
    private Connection con;

    private Conexion() {
        this.initConection();
    }

    private void initConection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(CONEXION, USUARIO, PASSWORD);
            System.out.println("Connected");
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Conexion.class.getName())
                  .log(Level.SEVERE, null, ex);
        }
    }

    public static Conexion getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new Conexion();
        }
        return INSTANCE;
    }

    public Connection getCon() {
        return con;
    }

}