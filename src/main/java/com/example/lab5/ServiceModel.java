package com.example.lab5;

public class ServiceModel {
    private String Name;
    private String serviceField;
    private String vCard;
    private String link;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getServiceField() {
        return serviceField;
    }

    public void setServiceField(String serviceField) {
        this.serviceField = serviceField;
    }

    public String getvCard() {
        return vCard;
    }

    public void setvCard(String vCard) {
        this.vCard = vCard;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
