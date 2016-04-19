package group6.eda397_2016.chalmers.se.pinder.model;

import java.util.List;

public class Task {
    private String name;
    private String description;
    private List<Skill> mandatoryRequirements;
    private List<Skill> recommendedRequirements;
    private List<Profile> assignees;
    private int velocity;
    private State state;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Skill> getMandatoryRequirements() {
        return mandatoryRequirements;
    }

    public void setMandatoryRequirements(List<Skill> mandatoryRequirements) {
        this.mandatoryRequirements = mandatoryRequirements;
    }

    public List<Skill> getRecommendedRequirements() {
        return recommendedRequirements;
    }

    public void setRecommendedRequirements(List<Skill> recommendedRequirements) {
        this.recommendedRequirements = recommendedRequirements;
    }

    public List<Profile> getAssignees() {
        return assignees;
    }

    public void setAssignees(List<Profile> assignees) {
        this.assignees = assignees;
    }

    public int getVelocity() {
        return velocity;
    }

    public void setVelocity(int velocity) {
        this.velocity = velocity;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }
}
