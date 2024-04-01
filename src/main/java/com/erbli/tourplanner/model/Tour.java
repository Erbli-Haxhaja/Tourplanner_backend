package com.erbli.tourplanner.model;


public class Tour {
    private String name;
    private String tourDescription;
    private String from;
    private String to;
    private String transportType;
    private double tourDistance;
    private String estimatedTime;
    private String routeInformation;

    public Tour(String name, String tourDescription, String from, String to, String transportType, double tourDistance, String estimatedTime, String routeInformation) {
        this.name = name;
        this.tourDescription = tourDescription;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.tourDistance = tourDistance;
        this.estimatedTime = estimatedTime;
        this.routeInformation = routeInformation;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTourDescription() {
        return tourDescription;
    }

    public void setTourDescription(String tourDescription) {
        this.tourDescription = tourDescription;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public double getTourDistance() {
        return tourDistance;
    }

    public void setTourDistance(double tourDistance) {
        this.tourDistance = tourDistance;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public String getRouteInformation() {
        return routeInformation;
    }

    public void setRouteInformation(String routeInformation) {
        this.routeInformation = routeInformation;
    }
}
