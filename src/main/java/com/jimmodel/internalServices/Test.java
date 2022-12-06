package com.jimmodel.internalServices;

import java.time.*;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.TimeZone;
import java.util.stream.Collectors;

public class Test {

    public static void main(String[] args){
//        OffsetDateTime odt = OffsetDateTime.parse( "2010-12-27T10:50:44.000-08:00" );
//        Instant instant = Instant.parse("2010-12-27T10:50:44.000-08:00");
//        ZonedDateTime zdt = instant.atZone(ZoneId.of("-8"));
//
//        System.out.println(odt);
//        System.out.println(instant);
//        System.out.println(zdt);
//        LocalDateTime local = LocalDateTime.now();
//        System.out.println(local);
//        System.out.println(local.atZone(ZoneId.of("-8")));
        ArrayList<String> list = new ArrayList<>(List.of("the", "the", "g", "b"));
        System.out.println(list);
        System.out.println(list.stream().map(s -> {
            if(s.startsWith("t")){
                return s;
            }
            return null;
        }).collect(Collectors.toList()));
    }
}
