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
@Table(name ="Tour")
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class Tour {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String tourDescription;
    private String fromm;
    private String too;
    private String transportType;
    private String tourDistance;
    private String estimatedTime;
    private String routeinformation;
}
