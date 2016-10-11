/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Examen;

import java.lang.reflect.Field;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import mx.uach.videoclub.modelos.Director;
import static mx.uach.videoclub.modelos.genericos.Model.ID;

/**
 *
 * @author edgar
 */
public class Examen {
    
    public static String fieldsToQuery(String[] fields, Boolean noId) {
        String campos = "";
        List<String> fieldsList = Arrays.asList(fields);
        fieldsList = noId
                ? fieldsList.stream()
                .filter(field -> !field.equals(ID))
                .collect(Collectors.toList()) : fieldsList;
        for (String field : fieldsList) {
            campos = String.format("%s, %s", campos, field);
        }
        return campos.substring(1);
    }
    
    public static void main(String[] args) throws IllegalAccessException, NoSuchFieldException {
        Examen dyn = new Examen();
        dyn.getFields(new Director(), "1", "Peinado");
    }

    public Examen() {
    }
    
    /**
     * Convierte de un arreglo de campos a un String para un query.
     *
     * @param obj Es cualquier clase que tenga un equivalente en el modelo E-R.
     * @param args Son los values del insert.
     * @return Una query.
     */
    
    public String getFields(Object obj, String ... args) throws IllegalAccessException, NoSuchFieldException {
        Object objectInstance = new Object();
        String query = "";
        Field table = obj.getClass().getField("TABLA");
        Field fields = obj.getClass().getField("FIELDS");
        String[] fieldsValues = (String[]) fields.get(obj); 
        String tablename = (String) table.get(objectInstance); 
        String[] arrayArgs = args;
        query = String.format("INSERT INTO %s(%s) VALUES (%s)", tablename, 
                fieldsToQuery(fieldsValues, Boolean.FALSE),
                fieldsToQuery(arrayArgs, Boolean.FALSE));
        return query;
    }
}