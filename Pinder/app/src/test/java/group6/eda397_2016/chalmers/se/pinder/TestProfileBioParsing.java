package group6.eda397_2016.chalmers.se.pinder;

import android.util.Log;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import group6.eda397_2016.chalmers.se.pinder.model.Profile;

import static org.junit.Assert.*;

/**
 * To work on unit tests, switch the Test Artifact in the Build Variants view.
 */
public class TestProfileBioParsing {
    private List<ProfileTest> profiles = new ArrayList<>();

    private class ProfileTest{
        String name;
        String bio;
        String id;

        public ProfileTest(String id, String name, String bio) {
            this.name = name;
            this.bio = bio;
            this.id = id;
        }
        Profile createProfile(){
            return new Profile(id,name,bio);
        }
    }

    {
        profiles.add(new ProfileTest("0","Test0","Skills: C++, Java, C, Android, Javascript, Awesomeness"));
        profiles.add(new ProfileTest("1","Test1","Skills: C, Android, Awesomeness"));
        profiles.add(new ProfileTest("2","Test2","Skills: C++, Java"));
        profiles.add(new ProfileTest("3","Test3","Skills: C++"));
        profiles.add(new ProfileTest("4","Test4","Skills:"));
    }

    @Test
    public void testMultipleSkills() throws Exception {
        Profile profile = profiles.get(0).createProfile();
        assertTrue(profile.getSkills().contains("C++"));
        assertTrue(profile.getSkills().contains("Java"));
        assertTrue(profile.getSkills().contains("C"));
        assertTrue(profile.getSkills().contains("Android"));
        assertTrue(profile.getSkills().contains("Javascript"));
        assertTrue(profile.getSkills().contains("Awesomeness"));

        profile = profiles.get(1).createProfile();
        assertTrue(profile.getSkills().contains("Awesomeness"));
        assertTrue(profile.getSkills().contains("Android"));
        assertTrue(profile.getSkills().contains("C"));

        profile = profiles.get(2).createProfile();
        assertTrue(profile.getSkills().contains("C++"));
        assertTrue(profile.getSkills().contains("Java"));
    }

    @Test
    public void testOneSkill() throws Exception {
        Profile profile = profiles.get(3).createProfile();
        assertTrue(profile.getSkills().contains("C++"));
    }

    @Test
    public void testNoSkill() throws Exception {
        Profile profile = profiles.get(4).createProfile();
        assertTrue(profile.getSkills().size() == 0);
    }
}