package test.prog.service;

import test.prog.model.Profile;

public interface ProfileService {

    /*Profile findById(int id);*/
    Profile getProfile(int id);

    void createProfile(String firstName, String lastName);
    void createProfile(Profile profile);


    void deleteProfile(int id);
}