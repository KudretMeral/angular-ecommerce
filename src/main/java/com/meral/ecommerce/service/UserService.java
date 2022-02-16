package com.meral.ecommerce.service;


import com.meral.ecommerce.enums.Role;
import com.meral.ecommerce.exception.model.EmailExistException;
import com.meral.ecommerce.exception.model.UserNotFoundException;
import com.meral.ecommerce.exception.model.UsernameExistException;
import com.meral.ecommerce.model.User;
import com.meral.ecommerce.model.UserPrincipal;
import com.meral.ecommerce.repository.UserRepository;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

@Service
@Transactional
@Qualifier("userDetailsService")
public class UserService implements UserDetailsService {


    private BCryptPasswordEncoder passwordEncoder;

    public UserService(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    private Logger LOGGER = LoggerFactory.getLogger(getClass()); //UserService.class bunun yerine getClass() yazılabilir
    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = userRepository.findUserByUsername(username);
        if (user == null) {
            LOGGER.error("User not found by username:" + username);
            throw new UsernameNotFoundException("User not found by username: " + username);
        } else {
            user.setLastLoginDateDisplay(user.getLastLoginDate());
            user.setLastLoginDate(new Date());
            userRepository.save(user);

            UserPrincipal userPrincipal = new UserPrincipal(user);
            LOGGER.info("Returning found user by usernam: " + username);

            return userPrincipal;
        }

    }

    private String generateUserId()
    {
        return RandomStringUtils.randomNumeric(10);
    }

    private String generatePassword()
    {
        return RandomStringUtils.randomAlphanumeric(10);
    }

    private String encodePassword(String password)
    {
        return passwordEncoder.encode(password);
    }

    private String getTemporaryProfileImageUrl()
    {
    return ServletUriComponentsBuilder.fromCurrentContextPath().path("/user/image/profile/temp").toUriString();
    }


    public User register(String firstName, String lastName, String username, String email) throws UserNotFoundException, UsernameExistException, EmailExistException {

        validateNewUsernameAndEmail(StringUtils.EMPTY, username, email);

        User user = new User();
        user.setUserId(generateUserId());
        String password = "Test123?";//generatePassword();
        String encodedPassword = encodePassword(password);
        user.setFirstName(firstName);
        user.setLastName(lastName);
        user.setUsername(username);
        user.setEmail(email);
        user.setJoinDate(new Date());
        user.setPassword(encodedPassword);
        user.setActive(true);
        user.setNotLocked(true);
        user.setRole(Role.ROLE_USER.name());
        user.setAuthorities(Role.ROLE_USER.getAuthorities());
        user.setProfileImageUrl(getTemporaryProfileImageUrl());
        userRepository.save(user);
        LOGGER.info("New user password: "+password);
        return user;
    }

    private User validateNewUsernameAndEmail(String currentUsername, String newUsername, String newEmail) throws UserNotFoundException, UsernameExistException, EmailExistException {

        User userByNewUserName = findUserByUsername(newUsername);
        User userByNewEmail = findUserByEmail(newEmail);
        if (StringUtils.isNotBlank(currentUsername)) {
            User currentUser = findUserByUsername(currentUsername);
            if (currentUser == null) {
                throw new UserNotFoundException("No user found by username: " + currentUsername);
            }


            if (userByNewUserName != null && !currentUser.getId().equals(userByNewUserName.getId())) {
                throw new UsernameExistException("Username already exists");
            }

            if (userByNewEmail != null && !currentUser.getId().equals(userByNewEmail.getId())) {
                throw new EmailExistException("Email already exists");
            }

            return currentUser;
        } else {

            if (userByNewUserName != null) {
                throw new UsernameExistException("Username already exists");
            }

            if (userByNewEmail != null) {
                throw new EmailExistException("Email already exists");
            }
            return null;
        }

    }

    public List<User> getUsers() {
       return  userRepository.findAll();
    }

    public User findUserByUsername(String username) {
        return userRepository.findUserByUsername(username);
    }


    public User findUserByEmail(String email) {

        return userRepository.findUserByEmail(email);
    }

}
