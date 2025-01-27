package com.example.OnlineVehicleReservation.model;

import java.util.Date;

public class Notification {
    private int notificationId;
    private int userId;
    private String message;
    private String status;
    private Date createdAt;

    public Notification(int notificationId, int userId, String message, String status, Date createdAt) {
        this.notificationId = notificationId;
        this.userId = userId;
        this.message = message;
        this.status = status;
        this.createdAt = createdAt;
    }

    public Notification() {


    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }
}
