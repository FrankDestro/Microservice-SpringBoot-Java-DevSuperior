package com.devsuperior.hr_user.services;

import com.devsuperior.hr_user.models.entities.User;
import com.devsuperior.hr_user.models.entities.dto.UserDTO;
import com.devsuperior.hr_user.repositories.UserRepository;
import com.devsuperior.hr_user.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Transactional(readOnly = true)
    public UserDTO findUserById(Long id) {
        User user = getById(id);
        return new UserDTO(user);
    }

    @Transactional(readOnly = true)
    public UserDTO findUserByEmail(String email) {
        User user = getByEmail(email);
        return new UserDTO(user);
    }

    private User getById(Long id) {
        Optional<User> result = userRepository.findById(id);
        return result.orElseThrow(() -> new ResourceNotFoundException("id not found"));
    }

    private User getByEmail(String email) {
        Optional<User> result = Optional.ofNullable(userRepository.findByEmail(email));
        return result.orElseThrow(() -> new ResourceNotFoundException("email not found"));
    }

}
