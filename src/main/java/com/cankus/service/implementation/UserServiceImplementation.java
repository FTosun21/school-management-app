package com.cankus.service.implementation;

import com.cankus.dto.UserDto;
import com.cankus.entity.User;
import com.cankus.mapper.UserMapper;
import com.cankus.repository.UserRepository;
import com.cankus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
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
    //lambda expression method refrains  çalış


}
/*
//
//    @Override
//    public List<UserDto> getAllUsers() {
//        return userRepository.findAll().stream()
//                .filter(user -> !Boolean.TRUE.equals(user.getIsDeleted())) // Soft delete filtrelemesi
//                .map(userMapper::convertToDto)
//                .collect(Collectors.toList());
//    }
//
//    @Override
//    public UserDto createUser(UserDto userDto) {
//        // Parola kontrolü
//        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
//            throw new IllegalArgumentException("Password and Confirm Password do not match.");
//        }
//
//        User user = userMapper.convertToEntity(userDto);
//        User savedUser = userRepository.save(user);
//        return userMapper.convertToDto(savedUser);
//    }
//
//    @Override
//    public UserDto getUserById(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//        return userMapper.convertToDto(user);
//    }
//
//    @Override
//    public UserDto updateUser(Long id, UserDto userDto) {
//        User existing = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//
//        User updatedUser = userMapper.convertToEntity(userDto);
//        updatedUser.setId(id); // Mevcut id'yi koru
//        updatedUser.setInsertDateTime(existing.getInsertDateTime());
//        updatedUser.setInsertUserId(existing.getInsertUserId());
//
//        User saved = userRepository.save(updatedUser);
//        return userMapper.convertToDto(saved);
//    }
//
//    @Override
//    public void deleteUser(Long id) {
//        User user = userRepository.findById(id)
//                .orElseThrow(() -> new RuntimeException("User not found with id: " + id));
//        user.setIsDeleted(true); // Soft delete
//        userRepository.save(user);
//    }
 */