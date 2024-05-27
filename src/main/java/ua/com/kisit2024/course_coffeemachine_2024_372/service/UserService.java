package ua.com.kisit2024.course_coffeemachine_2024_372.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ua.com.kisit2024.course_coffeemachine_2024_372.entity.Users;
import ua.com.kisit2024.course_coffeemachine_2024_372.repository.UsersRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UsersRepository usersRepository;

    public boolean getLogicByUsername(String username){
        return (!usersRepository.findAllByUsername(username).isEmpty()) ? true : false;
    }

    public Users saveNewUserToDB(Users user){
        return usersRepository.save(user);
    }

    public boolean getLogicByUsernameAndPassword(String username, String password){
        return (!usersRepository.findAllByUsernameAndPassword(username, password).isEmpty());
    }

    public Users getUserByUsername(String username){
        return usersRepository.findAllByUsername(username).get(0);
    }

    public Users getUserById(Long id){
        return usersRepository.findById(id).get();
    }

}
