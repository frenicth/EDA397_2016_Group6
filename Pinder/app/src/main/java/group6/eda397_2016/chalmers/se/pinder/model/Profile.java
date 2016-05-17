package group6.eda397_2016.chalmers.se.pinder.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.TrelloInteraction.TrelloAPIConsumer;

public class Profile {
    private String id;
    private String name;
    private List<String> skills;
    private String bio;

    //Constructors
    public Profile () {}

    public Profile(String id, String name) {
        this.id = id;
        this.name = name;
        this.skills = new ArrayList<>();
    }

    public Profile(String id, String name, String bio) {
        this.id = id;
        this.name = name;
        this.skills = new ArrayList<>();
        parseBio(bio);
    }


    //get/set methods
    public String getId()
    {return this.id;}

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public List<String> getSkills()
    {
        return skills;
    }

    public String getBio(){return this.bio;}

    public String getBioForTrello()
    {   String  updatedBio = bio;
        if (!skills.isEmpty()) {
            updatedBio+= "\nSkills: ";
            for (String s : skills) {
                updatedBio += s + ",";
            }
            updatedBio = updatedBio.substring(0, updatedBio.length() - 1);
        }

        return updatedBio;
    }


    //other method

    public void addSkill(String skill) {
        if (!skill.trim().isEmpty()) {
            if (skill.contains(",")) {
                String[] temp = skill.split(",");
                for (int i = 0; i < temp.length; i++) {
                    if (temp[i] != null)
                        skills.add(temp[i].trim());
                }
            } else {
                skills.add(skill.trim());
            }
        }
    }

    private void parseBio (String bio)
    {
        if (!bio.isEmpty())
        {
            bio.trim();
            if (bio.contains("Skills")) {
                try {
                    if (bio.startsWith("Skills"))
                    {
                        String skills = bio.substring((bio.lastIndexOf(":")+1));
                        this.addSkill(skills);
                        this.bio = "";
                    }
                    else
                    {
                        String skills = bio.substring((bio.lastIndexOf(":") + 1));
                        this.addSkill(skills);
                        this.bio = bio.substring(0, bio.indexOf("Skills") - 1);
                    }
                }
                catch (Exception e)
                { Log.e("Profile Creation", "Not expected format for bio in Profile: " + this.name); }
                finally
                {this.bio = bio;}
            }
            else
            {
                this.bio = bio;
            }

        }
        else bio="";
    }






    @Override
    public String toString() {
        String ret = name + " Skills: ";
        for (String skill : skills) {
            ret += skill + " ";
        }
        return ret;
    }
}
