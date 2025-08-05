package co.edu.udea.dbcontrollers.db_controllers.services.impl;

import co.edu.udea.dbcontrollers.db_controllers.dtos.UserDTO;
import co.edu.udea.dbcontrollers.db_controllers.entities.User;
import co.edu.udea.dbcontrollers.db_controllers.repositories.UserRepository;
import co.edu.udea.dbcontrollers.db_controllers.services.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service // Anotación clave que le dice a Spring que esta es una clase de servicio.
public class UserServiceImpl implements UserService {

    // Inyección de dependencias: Spring nos proporciona una instancia del UserRepository.
    @Autowired
    private UserRepository userRepository;

    // --- Métodos de la Interfaz ---

    @Override
    @Transactional
    public UserDTO save(UserDTO userDTO) {
        User user = convertToEntity(userDTO);
        // Aquí se podría añadir lógica para encriptar la contraseña antes de guardarla.
        // user.setUserPassword(passwordEncoder.encode(user.getUserPassword()));
        User savedUser = userRepository.save(user);
        return convertToDTO(savedUser);
    }

    @Override
    @Transactional(readOnly = true) // Optimizacion: indica a JPA que es una operación de solo lectura.
    public List<UserDTO> findAll() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToDTO) // Usa un método de conversión por cada elemento.
                .collect(Collectors.toList());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<UserDTO> findById(Long id) {
        return userRepository.findById(id)
                .map(this::convertToDTO); // Si encuentra el usuario, lo convierte a DTO.
    }

    @Override
    @Transactional
    public UserDTO update(Long id, UserDTO userDTO) {
        User existingUser = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con id: " + id));

        // Actualizamos los campos de la entidad existente con los datos del DTO.
        existingUser.setUserName(userDTO.getUserName());
        existingUser.setUserEmail(userDTO.getUserEmail());
        existingUser.setUserNumber(userDTO.getUserNumber());
        // Lógica para actualizar contraseña si se proporciona una nueva.

        User updatedUser = userRepository.save(existingUser);
        return convertToDTO(updatedUser);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        // Opcional: verificar si el usuario existe antes de intentar borrar.
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("Usuario no encontrado con id: " + id);
        }
        userRepository.deleteById(id);
    }


    // --- Métodos Privados de Conversión ---

    private UserDTO convertToDTO(User user) {
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUserName(user.getUserName());
        dto.setUserEmail(user.getUserEmail());
        dto.setUserNumber(user.getUserNumber());
        // NUNCA incluimos la contraseña en el DTO de respuesta.
        return dto;
    }

    private User convertToEntity(UserDTO dto) {
        User user = new User();
        // El id no se setea aquí, ya que es para un nuevo usuario.
        user.setUserName(dto.getUserName());
        user.setUserPassword(dto.getUserPassword());
        user.setUserEmail(dto.getUserEmail());
        user.setUserNumber(dto.getUserNumber());
        return user;
    }
}
