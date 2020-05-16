package org.quickresponse.qr.service;

import org.quickresponse.qr.domain.User;
import org.quickresponse.qr.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
public class UserService {

    @Autowired
    UserRepository userRepository;

    @Transactional
    public Long join(User user) {
        userRepository.join(user);
        return user.getId();
    }
}
