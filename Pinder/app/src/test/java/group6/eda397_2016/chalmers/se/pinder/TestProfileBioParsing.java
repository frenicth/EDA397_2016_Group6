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

    private Profile profile;

    private class ProfileTest{
        String name;
        String bio;
        String id;

        public ProfileTest(String id, String name, String bio) {
            this.name = name;
            this.bio = bio;
            this.id = id;
        }

    }

    private List<ProfileTest> profiles = new ArrayList<>();

    {
        profiles.add(new ProfileTest("0","Test0","Skills: C++, Java, C, Android, Javascript, Awesomeness"));
        profiles.add(new ProfileTest("1","Test1","Skills: C, Android, Awesomeness"));
        profiles.add(new ProfileTest("2","Test2","Skills: C++, Java"));
        profiles.add(new ProfileTest("3","Test3","Skills: C++"));
        profiles.add(new ProfileTest("4","Test4","Skills:"));
    }

    @Test
    public void testMultipleSkills() throws Exception {
        profile = new Profile(profiles.get(0).id,profiles.get(0).name,profiles.get(0).bio);
        assertTrue(profile.getSkills().contains("C++"));
        assertTrue(profile.getSkills().contains("Java"));
        assertTrue(profile.getSkills().contains("C"));
        assertTrue(profile.getSkills().contains("Android"));
        assertTrue(profile.getSkills().contains("Javascript"));
        assertTrue(profile.getSkills().contains("Awesomeness"));

        profile = new Profile(profiles.get(1).id,profiles.get(1).name,profiles.get(1).bio);
        assertTrue(profile.getSkills().contains("Awesomeness"));
        assertTrue(profile.getSkills().contains("Android"));
        assertTrue(profile.getSkills().contains("C"));

        profile = new Profile(profiles.get(2).id,profiles.get(2).name,profiles.get(2).bio);
        assertTrue(profile.getSkills().contains("C++"));
        assertTrue(profile.getSkills().contains("Java"));
    }

    @Test
    public void testOneSkill() throws Exception {
        profile = new Profile(profiles.get(3).id,profiles.get(3).name,profiles.get(3).bio);
        assertTrue(profile.getSkills().contains("C++"));
    }

    @Test
    public void testNoSkill() throws Exception {
        profile = new Profile(profiles.get(4).id,profiles.get(4).name,profiles.get(4).bio);
        assertTrue(profile.getSkills().size() == 0);
    }
}