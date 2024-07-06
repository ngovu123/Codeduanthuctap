package com.example.baohongtaisantdmu_admin.Model;

public class NotificationData {
    private NotificationDetailsData data;
    private String to;

    public NotificationData() {
    }

    public NotificationData(NotificationDetailsData notificationDetailsData, String to) {
        this.data = notificationDetailsData;
        this.to = to;
    }

    public NotificationDetailsData getNotificationDataBaoHong() {
        return data;
    }

    public void setNotificationDataBaoHong(NotificationDetailsData notificationDetailsData) {
        this.data = notificationDetailsData;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
}
