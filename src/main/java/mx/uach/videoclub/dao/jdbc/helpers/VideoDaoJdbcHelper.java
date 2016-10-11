/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mx.uach.videoclub.dao.jdbc.helpers;

import java.sql.ResultSet;
import java.sql.SQLException;
import mx.uach.videoclub.modelos.Actor;
import mx.uach.videoclub.modelos.Director;

/**
 *
 * @author edgar
 */
public class VideoDaoJdbcHelper {

    public final static Director makeDirector(ResultSet rs) throws SQLException {
        Director obj = new Director(rs.getInt(Director.FIELDS[0]),
                rs.getString(Director.FIELDS[1]));
        return obj;
    }

    public final static Actor makeActor(ResultSet rs) throws SQLException {
       Actor obj = new Actor(rs.getInt(Actor.FIELDS[0]),
                rs.getString(Actor.FIELDS[1]),rs.getString(Actor.FIELDS[2]));
       return obj;
    }
}
