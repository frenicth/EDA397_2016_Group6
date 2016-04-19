package group6.eda397_2016.chalmers.se.pinder.model;

import java.util.List;

public class Profile {
    private String name;
    private List<Skill> skills;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Skill> getSkills() {
        return skills;
    }

    public void setSkills(List<Skill> skills) {
        this.skills = skills;
    }
}
