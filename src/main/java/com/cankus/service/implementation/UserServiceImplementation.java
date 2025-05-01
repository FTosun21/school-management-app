package com.cankus.service.implementation;

import com.cankus.dto.UserDto;
import com.cankus.entity.User;
import com.cankus.mapper.UserMapper;
import com.cankus.repository.UserRepository;
import com.cankus.service.AddressService;
import com.cankus.service.CourseService;
import com.cankus.service.LessonService;
import com.cankus.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@Service
public class UserServiceImplementation implements UserService {


    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final AddressService addressService;
    private final CourseService courseService;
    private final LessonService lessonService;

    public UserServiceImplementation(UserRepository userRepository, UserMapper userMapper, AddressService addressService, CourseService courseService, LessonService lessonService) {
        this.userRepository = userRepository;
        this.userMapper = userMapper;
        this.addressService = addressService;
        this.courseService = courseService;
        this.lessonService = lessonService;
    }

    @Override
    public List<UserDto> findAll() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::covertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean isEmailRegistered(String email) {
        return userRepository.findAll()
                .stream()
                .anyMatch(user -> user.getUserName().equals(email));
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
        User userInDB = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User could not be found."));
        return userMapper.covertToDto(userInDB);
    }

    @Override
    public void update(UserDto userDto) {
        User user = userMapper.covertToEntity(userDto);
        userRepository.save(user);
    }

    @Override
    public void delete(Long id) {
        User userInDB = userRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("User could not be found."));
        addressService.delete(id); //  soft delete yapıldı --> AddressRepositorydeki delete method
        userInDB.setUserName(userInDB.getUserName() + " " + userInDB.getId());
        userInDB.setDeleted(true);
        userRepository.save(userInDB);

    }

    @Override
    public List<UserDto> getAllManagers() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().getDescription().equals("Manager"))
                .map(userMapper::covertToDto).collect(Collectors.toList());
    }

    @Override
    public List<UserDto> getAllInstructors() {
        return userRepository.findAll()
                .stream()
                .filter(user -> user.getRole().getDescription().equals("Instructor"))
                .map(userMapper::covertToDto).collect(Collectors.toList());
    }

    @Override
    public boolean canUpdateRole(Long id) {
        UserDto user = findById(id);
        String roleDescription = user.getRole().getDescription().toUpperCase();
        switch (roleDescription) {
            case "ADMIN":
               return !isSoleAdmin(id);
            case "MANAGER":
              return !courseService.hasAssignedCourses(id);
            case "INSTRUCTOR":
                return !lessonService.hasAssignedLessons(id);
            default:
                return true;
        }
    }

    @Override
    public boolean isSoleAdmin(Long id) {
        List<UserDto> admins = findAll()
                .stream()
                .filter(user -> user.getRole().getDescription().equalsIgnoreCase("ADMIN"))
                .collect(Collectors.toList());
        return admins.size() == 1 && admins.get(0).getId().equals(id);
    }


}
