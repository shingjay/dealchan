package com.dealchan.backend.service;

import com.dealchan.backend.domain.User;
import com.dealchan.backend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by IntelliJ IDEA.
 * User: anbiniyar
 * Date: 2/1/12
 * Time: 7:29 PM
 * To change this template use File | Settings | File Templates.
 */
@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void seed() {

        User user = new User();
        user.setUsername("fuckyou");
        userRepository.save(user);

    }
}
