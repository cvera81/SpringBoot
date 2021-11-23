package com.intercorp.Post.mangement.common;

import java.util.Arrays;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

public class Common {

    public static double promedioEdades(int[] array){
        return Arrays.stream(array).average().orElse(Double.NaN);
    }

    public static double desEstandarEdades(int[] array){
        /*
        *  raiz cuadrada (de la sumatoria ((Xi-u)pot2) / N)
        * */
        int suma = 0;
        int n = array.length;
        double prom = promedioEdades(array);
        for (int i = 0; i < n; i++ )
            suma += Math.pow (array[i] - prom, 2);

        return Math.sqrt ( suma / ( double ) n );
    }

    public static Date fechaProbableDeMuerte(){
        LocalDate fechaRandom = creaFechaRandom(2021,2059);
        return Date.from(fechaRandom.atStartOfDay(ZoneId.systemDefault()).toInstant());
    }
    public static int crearRandomEntreRangos(int start, int end) {
        return start + (int) Math.round(Math.random() * (end - start));
    }

    public static LocalDate creaFechaRandom(int startYear, int endYear) {
        int day;
        int month = crearRandomEntreRangos(1, 12);
        if (month == 2) {
            day = crearRandomEntreRangos(1, 28);
        }else{
            day = crearRandomEntreRangos(1, 30);
        }
        int year = crearRandomEntreRangos(startYear, endYear);
        return LocalDate.of(year, month, day);
    }

}
