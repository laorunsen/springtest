package com.example.demo;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private UserDTO convertToDTO(UserEntity user) {
        return new UserDTO(user.getId(), user.getName(), user.getAge(), user.getEmail());
    }
    public Optional<UserDTO> getUserById(Long id) {
        return userRepository.findById(id).map(this::convertToDTO);
    }

    public Optional<UserDTO> getUserByName(String name) {
        return userRepository.findByName(name).map(this::convertToDTO);
    }

    public List<UserDTO> getAllUser() {
        return userRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public UserDTO createUser(UserDTO dto) {
        UserEntity user = new UserEntity(dto.name(), dto.age(), dto.email());
        UserEntity save = userRepository.save(user);
        return convertToDTO(save);
    }

    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }
}
