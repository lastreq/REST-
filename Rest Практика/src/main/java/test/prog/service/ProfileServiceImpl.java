package test.prog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import test.prog.dao.ProfileDao;
import test.prog.exception.ProfileNotFoundException;
import test.prog.model.Profile;

@Primary
@Service
public class ProfileServiceImpl implements ProfileService {

    private final ProfileDao profileDao;

    @Autowired
    public ProfileServiceImpl(ProfileDao profileDao) {
        this.profileDao = profileDao;
    }


    /*  @Override
      public Profile findById(int id) {
          return profileDao.findById(id);
      }*/
    /*@Override
    public Profile listProfileInfo() {
        return  List<ProfileInfo> list = profileDao.listProfileInfo();
    }*/
    @Override
    public Profile getProfile(int id) {
        return profileDao.getProfileById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
    }

    @Override
    public void createProfile(String firstName, String lastName) {
        profileDao.insertProfile(firstName, lastName);
    }

    @Override
    public void createProfile(Profile profile) {
        profileDao.insertProfile(profile.getFirstName(), profile.getLastName());
    }

    @Override
    public void deleteProfile(int id) {
        Profile profile = profileDao.getProfileById(id)
                .orElseThrow(() -> new ProfileNotFoundException(id));
        profileDao.deleteProfileById(profile.getId());
    }
}