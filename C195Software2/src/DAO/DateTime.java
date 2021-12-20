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

/**
 * Class to handle the dates and time for all the objects that require it
 */
public class DateTime {

    protected ObservableList<LocalTime> startList = FXCollections.observableArrayList();
    protected ObservableList<LocalDateTime> endList = FXCollections.observableArrayList();
//    protected LocalDateTime startDateTime;
//    protected LocalDateTime endDateTime;
    private LocalTime startTime;
    private LocalTime endTime;


    /**
     * Function to return time stamp
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

    /**
     * Method to get the local date
     * @return localdate.now()
     */
    public static LocalDate getLocalDate(){
        return LocalDate.now();
    }

    /**
     * Method to return the startdate
     * @return LocalDate startDate
     */
    public LocalDate getStartDate(){
        LocalDate startDate = LocalDate.now();
        return startDate;
    }

    /**
     * Method to return the local time
     * @return localtime.now()
     */
    public LocalTime getStartTime(){
        return LocalTime.now();
    }

    /**
     * Method returns a list of start times
     * @return startlist
     */
    public ObservableList<LocalTime> getStartList() {
        startTime = LocalTime.of(0, 0);
        endTime = LocalTime.of(23, 45);

        try {
            while(startTime.isBefore(endTime)) {
                startList.add(startTime);
                startTime = startTime.plusMinutes(15);
            }
        } catch (OutOfMemoryError e) {
            System.out.println("Failed here");
        }

        return startList;
    }
}
