package model;

import java.time.LocalDateTime;

public class Appointment {

    private int appointmentID;
    private String appointmentTitle;
    private String appointmentDesc;
    private String appointmentLoc;
    private String appointmentType;
    private LocalDateTime appointmentStart;
    private LocalDateTime appointmentEnd;
    private LocalDateTime appointmentCreateDate;
    private String appointmentCreatedBy;
    private LocalDateTime appointmentLastUpdate;
    private String appointmentUpdatedBy;
    private int appointmentCustId;
    private int appointmentUserId;
    private int appointmentContactId;

    public Appointment(int appointmentID, String appointmentTitle, String appointmentDesc, String appointmentLoc, String appointmentType, LocalDateTime appointmentStart, LocalDateTime appointmentEnd, LocalDateTime appointmentCreateDate, String appointmentCreatedBy, LocalDateTime appointmentLastUpdate, String appointmentUpdatedBy, int appointmentCustId, int appointmentUserId, int appointmentContactId) {
        this.appointmentID = appointmentID;
        this.appointmentTitle = appointmentTitle;
        this.appointmentDesc = appointmentDesc;
        this.appointmentLoc = appointmentLoc;
        this.appointmentType = appointmentType;
        this.appointmentStart = appointmentStart;
        this.appointmentEnd = appointmentEnd;
        this.appointmentCreateDate = appointmentCreateDate;
        this.appointmentCreatedBy = appointmentCreatedBy;
        this.appointmentLastUpdate = appointmentLastUpdate;
        this.appointmentUpdatedBy = appointmentUpdatedBy;
        this.appointmentCustId = appointmentCustId;
        this.appointmentUserId = appointmentUserId;
        this.appointmentContactId = appointmentContactId;
    }

    public int getAppointmentID() {
        return appointmentID;
    }

    public void setAppointmentID(int appointmentID) {
        this.appointmentID = appointmentID;
    }

    public String getAppointmentTitle() {
        return appointmentTitle;
    }

    public void setAppointmentTitle(String appointmentTitle) {
        this.appointmentTitle = appointmentTitle;
    }

    public String getAppointmentDesc() {
        return appointmentDesc;
    }

    public void setAppointmentDesc(String appointmentDesc) {
        this.appointmentDesc = appointmentDesc;
    }

    public String getAppointmentLoc() {
        return appointmentLoc;
    }

    public void setAppointmentLoc(String appointmentLoc) {
        this.appointmentLoc = appointmentLoc;
    }

    public String getAppointmentType() {
        return appointmentType;
    }

    public void setAppointmentType(String appointmentType) {
        this.appointmentType = appointmentType;
    }

    public LocalDateTime getAppointmentStart() {
        return appointmentStart;
    }

    public void setAppointmentStart(LocalDateTime appointmentStart) {
        this.appointmentStart = appointmentStart;
    }

    public LocalDateTime getAppointmentEnd() {
        return appointmentEnd;
    }

    public void setAppointmentEnd(LocalDateTime appointmentEnd) {
        this.appointmentEnd = appointmentEnd;
    }

    public LocalDateTime getAppointmentCreateDate() {
        return appointmentCreateDate;
    }

    public void setAppointmentCreateDate(LocalDateTime appointmentCreateDate) {
        this.appointmentCreateDate = appointmentCreateDate;
    }

    public String getAppointmentCreatedBy() {
        return appointmentCreatedBy;
    }

    public void setAppointmentCreatedBy(String appointmentCreatedBy) {
        this.appointmentCreatedBy = appointmentCreatedBy;
    }

    public LocalDateTime getAppointmentLastUpdate() {
        return appointmentLastUpdate;
    }

    public void setAppointmentLastUpdate(LocalDateTime appointmentLastUpdate) {
        this.appointmentLastUpdate = appointmentLastUpdate;
    }

    public String getAppointmentUpdatedBy() {
        return appointmentUpdatedBy;
    }

    public void setAppointmentUpdatedBy(String appointmentUpdatedBy) {
        this.appointmentUpdatedBy = appointmentUpdatedBy;
    }

    public int getAppointmentCustId() {
        return appointmentCustId;
    }

    public void setAppointmentCustId(int appointmentCustId) {
        this.appointmentCustId = appointmentCustId;
    }

    public int getAppointmentUserId() {
        return appointmentUserId;
    }

    public void setAppointmentUserId(int appointmentUserId) {
        this.appointmentUserId = appointmentUserId;
    }

    public int getAppointmentContactId() {
        return appointmentContactId;
    }

    public void setAppointmentContactId(int appointmentContactId) {
        this.appointmentContactId = appointmentContactId;
    }
}

