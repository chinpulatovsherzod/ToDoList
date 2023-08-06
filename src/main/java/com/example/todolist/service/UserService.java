package com.example.todolist.service;


import com.example.todolist.exception.UserNotFoundException;
import com.example.todolist.model.User;
import com.example.todolist.repos.UserRepos;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepos userRepos;

    public List<User> listAll() {
        return (List<User>) userRepos.findAll();
    }


    public void save(User user) {
        userRepos.save(user);

    }

    public User get(Integer id) throws UserNotFoundException {
        Optional<User> result = userRepos.findById(id);
        if (result.isPresent()) {
            return result.get();

        }
        throw new UserNotFoundException("Could not find any users with 10" + id);
    }

    public void delete(Integer id) throws UserNotFoundException {
        Long count = userRepos.countById(id);
        if (count == null || count == 0){
            throw new UserNotFoundException("Could not find any users with 10" + id);

        }
        userRepos.deleteById(id);
    }
}
