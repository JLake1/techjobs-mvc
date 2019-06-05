package org.launchcode;

public class Listing {

    private String name;
    private String position;
    private String employer;
    private String location;
    private String skill;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }

    public String getEmployer() {
        return employer;
    }

    public void setEmployer(String employer) {
        this.employer = employer;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getSkill() {
        return skill;
    }

    public void setSkill(String skill) {
        this.skill = skill;
    }

    public Listing(String name, String position, String employer, String location, String skill){
        this.name = name;
        this.position = position;
        this.employer = employer;
        this.location = location;
        this.skill = skill;
    }
}
