package com.erbli.tourplanner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Data;

@Entity
@Table(name ="Tour")
@Builder
public class Tour {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String tourDescription;
    private String fromm;
    private String too;
    private String transportType;
    private String tourDistance;
    private String estimatedTime;
    private String routeInformation;

    public Tour(int id, String name, String tourDescription, String from, String to, String transportType, String tourDistance, String estimatedTime, String routeInformation) {
        this.id = id;
        this.name = name;
        this.tourDescription = tourDescription;
        this.fromm = from;
        this.too = to;
        this.transportType = transportType;
        this.tourDistance = tourDistance;
        this.estimatedTime = estimatedTime;
        this.routeInformation = routeInformation;
    }

    public Tour() {

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
        return fromm;
    }

    public void setFrom(String from) {
        this.fromm = from;
    }

    public String getTo() {
        return too;
    }

    public void setTo(String to) {
        this.too = to;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public String getTourDistance() {
        return tourDistance;
    }

    public void setTourDistance(String tourDistance) {
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
