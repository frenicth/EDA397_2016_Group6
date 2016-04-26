package group6.eda397_2016.chalmers.se.pinder.model;

import java.util.List;

public class Task {
    private int id;
    private String name;
    private String description;
    private List<Skill> mandatoryRequirements;
    private List<Skill> recommendedRequirements;
    private List<Profile> assignees;
    private int storyPoints;
    private State state;

    public Task() {
    }

    public Task(int id, String name, String description, int storyPoints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.storyPoints = storyPoints;
    }

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

    public int getStoryPoints() {
        return storyPoints;
    }

    public void setStoryPoints(int storyPoints) {
        this.storyPoints = storyPoints;
    }

    public State getState() {
        return state;
    }

    public void setState(State state) {
        this.state = state;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", mandatoryRequirements=" + mandatoryRequirements +
                ", recommendedRequirements=" + recommendedRequirements +
                ", assignees=" + assignees +
                ", storyPoints=" + storyPoints +
                ", state=" + state +
                '}';
    }
}
