package com.erbli.tourplanner.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name ="Tourlogs")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class TourLogs {
    @Id
    @GeneratedValue
    private int id;
    private int tourid;
    private String date;
    private String time;
    private String comment;
    private String difficulty;
    private String totalDistance;
    private String totalTime;
    private String rating;
}
