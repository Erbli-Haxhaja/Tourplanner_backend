package com.erbli.tourplanner.dtos;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TourLogDto {
    private int id;
    private int tourid;
    private String date;
    private String time;
    private String comment;
    private String difficulty;
    private String totalDistance;
    private String totalTime;
    private String rating;

    // Constructors
    public TourLogDto() {
    }

    public TourLogDto(int id, int tourid, String date, String time, String comment, String difficulty, String totalDistance, String totalTime, String rating) {
        this.id = id;
        this.tourid = tourid;
        this.date = date;
        this.time = time;
        this.comment = comment;
        this.difficulty = difficulty;
        this.totalDistance = totalDistance;
        this.totalTime = totalTime;
        this.rating = rating;
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(String totalDistance) {
        this.totalDistance = totalDistance;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }
}
