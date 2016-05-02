package group6.eda397_2016.chalmers.se.pinder.model;

import android.util.Log;

import java.util.ArrayList;
import java.util.List;

public class Profile {
    private String id;
    private String name;
    private List<String> skills;
    private String bio;

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


    public void addSkill(String skill)
    {
        if (skill.contains(","))
        {
            String [] temp = skill.split(",");
            for (int i=0;i<temp.length;i++)
            {
                if (temp[i]!=null)
                skills.add(temp[i].trim());
            }
        }
        else
        {
            skills.add(skill);
        }
    }

    private void parseBio (String bio)
    {
        if (!bio.isEmpty())
        {
            if (bio.contains("Skills")) {
                try {
                    String skills = bio.substring((bio.lastIndexOf(":")+1));
                    this.addSkill(skills);
                    this.bio = bio.substring(0, bio.indexOf("Skills") - 1);
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
    }

    public String getId()
    {return this.id;}
    @Override
    public String toString() {
        String ret = name + " Skills: ";
        for (String skill : skills) {
            ret += skill + " ";
        }
        return ret;
    }
}
