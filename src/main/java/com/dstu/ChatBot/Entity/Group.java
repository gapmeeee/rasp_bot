package com.dstu.ChatBot.Entity;

public class Group {
    private Long groupId;
    private String name;
    private Integer kurs;
    private String facult;
    private String yearName;

    public Group(Long groupId, String name, Integer kurs, String facult, String yearName, Long facultId) {
        this.name = name;
        this.groupId = groupId;
        this.kurs = kurs;
        this.facult = facult;
        this.yearName = yearName;

    }

    private Group(){}


    public String getYearName() {
        return yearName;
    }

    public void setYearName(String yearName) {
        this.yearName = yearName;
    }

    public String getFacult() {
        return facult;
    }

    public void setFacult(String facult) {
        this.facult = facult;
    }


    public Long getGroupId() {
        return groupId;
    }

    public void setGroupId(Long groupId) {
        this.groupId = groupId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
