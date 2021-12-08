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
    protected LocalDateTime endDateTime;


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

    /**
     * Method returns a list of start times
     * @return startlist
     */
    public ObservableList<LocalDateTime> getStartList(){
        startDateTime = LocalDateTime.of(getLocalDate(), LocalTime.of(8,0));
        LocalDateTime endDateTimeAppointment = startDateTime.plusDays(30);

        while(startDateTime.isBefore(endDateTimeAppointment)){
            startList.add(startDateTime);
            startDateTime = startDateTime.plusMinutes(15);
        }

        return startList;
    }

    /**
     * method returns list of end times based on selected start time
     * @param startOfAppointment
     * @return endList
     */
    public ObservableList<LocalDateTime> getEndList(LocalDateTime startOfAppointment){
        endDateTime = startOfAppointment.plusMinutes(15);
        LocalDateTime endListAppointment = endDateTime.plusDays(30);

        while(endDateTime.isBefore(endListAppointment)){
            endList.add(endDateTime);
            endDateTime = endDateTime.plusMinutes(15);
        }
        return endList;
    }
}
