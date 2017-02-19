package com.gashe.mimasacorporal.classes;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Comparator;
import java.util.Date;

/**
 * Created by Gashe on 19/2/17.
 */

public class CompareDate implements Comparator<Historial> {


    @Override
    public int compare(Historial date1, Historial date2) {

        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        try {
            Date dat1 = format.parse(date1.getDate());
            Date dat2 = format.parse(date2.getDate());

            return dat2.compareTo(dat1);


        } catch (ParseException e){
            e.printStackTrace();
        }

        return 0;

    }
}