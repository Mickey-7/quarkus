package org.acme.util;

import org.acme.model.UserModel;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.transaction.Transactional;
import java.util.Optional;

@ApplicationScoped
public class RegeneratePassword {
    @Inject
    PasswordGenerator passwordGenerator;

    @Transactional
    public void regenerate(String username){
        final Optional<UserModel> user = UserModel.findUserByUsername(username);
        user.map( u-> {

            String newPassword = passwordGenerator.generate();
            u.setPassword(newPassword);
            return u;
        });
    }
}
