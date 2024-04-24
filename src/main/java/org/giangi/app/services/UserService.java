package org.giangi.app.services;

import org.giangi.app.domain.User;
import org.giangi.app.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> listAll() {
        return (List<User>) userRepository.findAll();
    }

    public void save(User user) {
        userRepository.save(user);
    }

    public User get(Long id) {
        return userRepository.findById(id).get();
    }

    public boolean existById(Long id){ return userRepository.existsById(id); }

    public void delete(Long id) {
        userRepository.deleteById(id);
    }

}
