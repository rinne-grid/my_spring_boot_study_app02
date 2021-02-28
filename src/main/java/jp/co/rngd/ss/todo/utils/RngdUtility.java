package jp.co.rngd.ss.todo.utils;

import java.time.LocalDateTime;

public class RngdUtility {
    
    /***
     * 
     * @param date
     * @param hour
     * @param minutes
     * @return {LocalDateTime}
     */
    public static LocalDateTime getLocalDateFormat(String date, String hour, String minutes, String splitStr) {
        String[] startYmd = date.split(splitStr);
        int startYY = Integer.parseInt(startYmd[0]);
        int startMM = Integer.parseInt(startYmd[1]);
        int startDD = Integer.parseInt(startYmd[2]);
        int startHH = Integer.parseInt(hour);
        int startMI = Integer.parseInt(minutes);
        LocalDateTime resultDate = LocalDateTime.of(startYY, startMM, startDD, startHH, startMI);
        return resultDate;
    }
}
