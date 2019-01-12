package me.arun.arunrxjavaexploring.RoomDB.typeConverters;

import android.arch.persistence.room.TypeConverter;

import java.util.Date;

public class Converters
{
    @TypeConverter
    public static Date fromTimestamp(Long value)
    {
        return value == null ? null : new Date(value);
    }

    @TypeConverter
    public static Long dateToTimestamp(Date date)
    {
        return date == null ? null : date.getTime();
    }
//
//    @TypeConverter
//    public CountryLangs storedStringToLanguages(String value) {
//        List<String> langs = Arrays.asList(value.split("\\s*,\\s*"));
//        return new CountryLangs(langs);
//    }
//
//    @TypeConverter
//    public String languagesToStoredString(CountryLangs cl) {
//        String value = "";
//
//        for (String lang :cl.getCountryLangs())
//            value += lang + ",";
//
//        return value;
//    }
}
