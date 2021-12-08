package DAO;
/**
 * This is the class to handle Date and Time
 * @author Antonio Peza
 * */
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.*;

public class DateTime {

    protected ObservableList<LocalDateTime> startList = FXCollections.observableArrayList();
    protected ObservableList<LocalDateTime> endList = FXCollections.observableArrayList();
    protected LocalDateTime startDateTime;


    /**
     * Function to return time stamp
     *
     * @return timeStamp
     */
    public static java.sql.Timestamp getTimeStamp() {
        ZoneId zoneid = ZoneId.of("UTC");
        LocalDateTime localDateTime = LocalDateTime.now(zoneid);
        java.sql.Timestamp timeStamp = Timestamp.valueOf(localDateTime);
        return timeStamp;
    }

    /**
     * Function to get date
     * @return date
     */
    public static java.sql.Date getDate() {
        java.sql.Date date = java.sql.Date.valueOf(LocalDate.now());
        return date;
    }

    public static LocalDate getLocalDate(){
        return LocalDate.now();
    }

    public LocalDate getStartDate(){
        LocalDate startDate = LocalDate.now();
        return startDate;
    }

    public LocalTime getStartTime(){
        return LocalTime.now();
    }

    public ObservableList<LocalDateTime> getStartList(){
        startDateTime = LocalDateTime.of(getLocalDate(), LocalTime.of(8,0));
        LocalDateTime endDateTime = startDateTime.plusDays(30);

        while(startDateTime.isBefore(endDateTime)){
            startList.add(startDateTime);
            startDateTime = startDateTime.plusMinutes(15);
        }

        return startList;
    }

    public ObservableList<LocalDateTime> getEndList(){
        LocalDateTime endDateTime = startDateTime.plusMinutes(15);

        while(startDateTime.isBefore(startDateTime.plusDays(30))){

            endList.add(endDateTime);
            endDateTime = endDateTime.plusMinutes(15);
        }
        return endList;
    }
}
