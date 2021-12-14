package test.prog.dao;

import test.prog.model.Profile;

import java.util.Optional;

public interface ProfileDao {
    /* List<ProfileInfo> listProfileInfo();*/
    /*public Profile findById(int id);*/
    Optional<Profile> getProfileById(int id);

    void insertProfile(String firstName, String secondName);

    void deleteProfileById(int id);

}