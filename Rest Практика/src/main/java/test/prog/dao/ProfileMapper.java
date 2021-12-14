package test.prog.dao;

import test.prog.model.Profile;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class ProfileMapper implements RowMapper<Profile> {

    @Override
    public Profile mapRow(ResultSet rs, int rowNum) throws SQLException {
        Profile profile = new Profile();
        profile.setId(rs.getInt("id"));
        profile.setFirstName(rs.getString("firstname"));
        profile.setLastName(rs.getString("lastname"));
        profile.setAddress(rs.getString("address"));
        profile.setEmail(rs.getString("email"));
        profile.setCountry(rs.getString("country"));
        return profile;
    }
}