package group6.eda397_2016.chalmers.se.pinder.model;

import java.util.List;

public class Profile {
    private int id;
    private String name;
    private List<Skill> skills;

    public Profile(int id, String name, List<Skill> skills) {
        this.id = id;
        this.name = name;
        this.skills = skills;
    }

    public Profile() {
    }

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

    @Override
    public String toString() {
        String ret = name + " Skills: ";
        for (Skill skill : skills) {
            ret += skill.name() + " ";
        }
        return ret;
    }
}
