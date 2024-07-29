package com.example.railwayapplines.model.dto;



public class RailwayLineDto {

    private Long id;
    private int number;
    private String route;
    private Double length;
    private String description;

    public RailwayLineDto() {
    }

    public RailwayLineDto(Long id, int number, String route, Double length, String description) {
        this.id = id;
        this.number = number;
        this.route = route;
        this.length = length;
        this.description = description;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public Double getLength() {
        return length;
    }

    public void setLength(Double length) {
        this.length = length;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
