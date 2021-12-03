package controller;

public class ReportsController {

    public void reportsByTypeAndMonth(){
        // SELECT COUNT(*) FROM (SELECT DISTINCT Type FROM appointments) as report1;
        //SELECT * from appointments
        //WHERE EXTRACT(MONTH from Start)
    }

    public void reportsByContact(){
        //SELECT * from appointments join contacts on contacts.Contact_ID = appointments.Contact_ID
    }
}
