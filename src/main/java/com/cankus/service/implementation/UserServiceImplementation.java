package com.cankus.service.implementation;

import com.cankus.dto.UserDto;
import com.cankus.entity.User;
import com.cankus.mapper.UserMapper;
import com.cankus.repository.UserRepository;
import com.cankus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll().stream()
                .map(userMapper::covertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findAll().stream().anyMatch(user -> user.getUserName().equals(email));
    }

    @Override
    public boolean isPasswordMatched(String password, String confirmPassword) {
        return !password.equals(confirmPassword);
    }

    //Todo security implement edilirken yeniden dön
    @Override
    public void save(UserDto userDto) {
        User user = userMapper.covertToEntity(userDto);
        userRepository.save(user);

    }

    @Override
    public UserDto findById(Long id) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User Role could not be found."));
        return userMapper.covertToDto(userInDB);
    }

    @Override
    public void update(UserDto userDto) {
        User user = userMapper.covertToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User userInDB = userRepository.findById(id).orElseThrow(() -> new NoSuchElementException("User Role could not be found."));
        //Todo karşılık gelen address de silinmeli

        userInDB.setUserName(userInDB.getUserName() + " " + userInDB.getId());

        userInDB.setDeleted(true);

        userRepository.save(userInDB);

    }

}
