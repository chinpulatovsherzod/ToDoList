package com.example.todolist;


import com.example.todolist.model.User;
import com.example.todolist.repos.UserRepos;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.Optional;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Rollback(false)
public class UserReposTest {
    @Autowired
    private UserRepos userRepos;

    @Test
    public void testAddNew(){
        User user = new User();
        user.setEmail("chinpulatovsherzod@gmail.com");
        user.setPassword("Sher_0122");
        user.setFirstname("Sherzod");
        user.setLastname("Chinpulatov");

        User saveUser = userRepos.save(user);

        Assertions.assertThat(saveUser).isNotNull();
        Assertions.assertThat(saveUser.getId()).isGreaterThan(0);
    }

    @Test
    public void testListAll(){
        Iterable<User> users = userRepos.findAll();
        Assertions.assertThat(users).hasSizeGreaterThan(0);

        for (User user: users) {
            System.out.println(user);
        }
    }

    @Test
    public void testUpdate(){
        Integer userId = 1;
        Optional<User> optionalUser = userRepos.findById(userId);
        User user = optionalUser.get();
        user.setPassword("hello3000");
        userRepos.save(user);

        User updateUser = userRepos.findById(userId).get();
        Assertions.assertThat(updateUser.getPassword()).isEqualTo("hello3000");
    }

    @Test
    public void testGet() {
        Integer userId = 2;
        Optional<User> optionalUser = userRepos.findById(userId);
        Assertions.assertThat(optionalUser).isPresent();
        System.out.println(optionalUser.get());
    }

    @Test
    public void testDelete(){
        Integer userId = 2;
        userRepos.deleteById(userId);

        Optional<User> optionalUser = userRepos.findById(userId);
        Assertions.assertThat(optionalUser).isNotPresent();
    }
}
