package com.example.todolist.repos;

import com.example.todolist.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepos extends CrudRepository<User, Integer> {
    public Long countById(Integer id);
}
