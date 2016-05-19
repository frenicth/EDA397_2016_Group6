package group6.eda397_2016.chalmers.se.pinder.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Task {
    private String id;
    private String name;
    private String description;
    private List<String> requiredSkills;
    private List<Profile> assignedMembers;
    private int storyPoints;
    //private State state;
    //private List<Skill> recommendedRequirements;

    public Task() {
    }

    //TODO : refine string parsing, lower case for tags and handle some errors.
    public Task(String id, String nameandpoints, String desc) {
        this.id = id;
        this.assignedMembers = new ArrayList<>();
        this.requiredSkills = new ArrayList<>();
        nameandpoints.trim();
        if (nameandpoints.startsWith("(")) {
            try {
                this.name = nameandpoints.substring(4);
                String temp = nameandpoints.substring(1, 2);
                this.storyPoints = Integer.parseInt(temp);
            } catch (Exception e) {
                System.out.println(e.getMessage());
                this.name = nameandpoints;
                this.storyPoints = 0;
            }
        } else {
            this.name = nameandpoints;
        }

        if (!desc.isEmpty()) {
            if (desc.contains("Required Skills")) {
                try {
                    String skills = desc.substring(desc.lastIndexOf(":") + 1);
                    addRequiredSkill(skills);
                    System.out.println(requiredSkills);
                    this.description = desc.substring(0, desc.indexOf("Required"));
                } catch (Exception e) {
                    Log.e("Task Creation", "Not expected format for desc in Task: " + name);
                }
            } else this.description = desc;
        }

    }

    public Task(String id, String name, String description, int storyPoints) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.storyPoints = storyPoints;
        this.assignedMembers = new ArrayList<>();
        this.requiredSkills = new ArrayList<>();

    }

    public void addRequiredSkill(String skill) {
        if (!skill.trim().isEmpty()) {
            if (skill.contains(",")) {
                String[] temp = skill.split(",");
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i] != null)
                        requiredSkills.add(temp[i].trim());
                }
            } else {
                requiredSkills.add(skill.trim());
            }
        }
    }
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public List<String> getRequiredSkills() {
        return requiredSkills;
    }

    public List<Profile> getAssignees() {
        return assignedMembers;
    }

    public boolean assignMember(Profile profile) {
        if (this.assignedMembers.size() < 2) {
            assignedMembers.add(profile);
            return true;
        } else return false;

    }


    public void removeMember(String profile){

            for (Profile p : assignedMembers) {
                if (p.getName().toString().equals(profile)){
                    assignedMembers.remove(p);
                }
            }

    }

    public String getAssignedMembersNames() {
        String members = "";
        if (!assignedMembers.isEmpty()) {
            for (Profile p : assignedMembers) {
                members += p.getId() + ",";
            }
            members = members.substring(0, members.length() - 1);
        }
        return members;
    }
    public List<Profile> getAssignedMembers()
    {
        return this.assignedMembers;
    }

    public int getStoryPoints() {
        return storyPoints;
    }

    public String getId() {
        return this.id;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", Required Skills=" + requiredSkills +
                ", assigned members=" + assignedMembers +
                ", storyPoints=" + storyPoints +
                '}';
    }
}
