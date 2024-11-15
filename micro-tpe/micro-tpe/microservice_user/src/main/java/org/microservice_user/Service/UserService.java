package org.microservice_user.Service;

import org.microservice_user.Dtos.AccountRequestDTO;
import org.microservice_user.Dtos.UserRequestDTO;
import org.microservice_user.Dtos.UserResponseDTO;
import org.microservice_user.Entities.Account;
import org.microservice_user.Entities.User;
import org.microservice_user.Repository.AccountRepository;
import org.microservice_user.Repository.UserRepository;
import org.microservice_user.feignClients.ScooterFeignClient;
import org.microservice_user.feignClients.TripFeignClient;
import org.microservice_user.feignClients.model.PauseDto;
import org.microservice_user.feignClients.model.ScooterDTO;
import org.microservice_user.feignClients.model.TripDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private ScooterFeignClient scooterFeignClient;
    @Autowired
    private TripFeignClient tripFeignClient;

    // CRUD - READ - GET
    public List<UserResponseDTO> findAll() throws Exception {
        try {
            List<User> users = userRepository.findAll();
            List<UserResponseDTO> userResponseDTOS = new ArrayList<>();
            for (User user : users) {
                UserResponseDTO userResponseDTO = new UserResponseDTO(user.getId_user(), user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude(), user.getAccounts());
                userResponseDTOS.add(userResponseDTO);
            }
            return userResponseDTOS;
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public UserResponseDTO findById(Long id) throws Exception {
        try {
            User user = userRepository.findById(id).get();
            return new UserResponseDTO(user.getId_user(), user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    // CRUD - CREATE - POST
    public UserResponseDTO addUser(UserRequestDTO userRequestDTO) throws Exception {
        User user = mapToUser(userRequestDTO);
        try {
            userRepository.save(user);
            return new UserResponseDTO(user.getId_user(), user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude(), user.getAccounts());
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }


    // CRUD - UPDATE - PUT
    public UserResponseDTO updateUser(Long id, UserRequestDTO userRequestDTO) throws Exception {
        User user = mapToUser(userRequestDTO);
        try {
            if (userRepository.existsById(id)) {
                userRepository.updateUser(id, user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude());
                return new UserResponseDTO(id, user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude(), user.getAccounts());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    // CRUD - DELETE
    public UserResponseDTO deleteById(Long id) throws Exception {
        try {
            if (userRepository.existsById(id)) {
                User userDelete = userRepository.findById(id).get();
                userRepository.deleteById(id);
                return new UserResponseDTO(userDelete.getId_user(), userDelete.getName(), userDelete.getLastName(), userDelete.getEmail(), userDelete.getRole(), userDelete.getPhone_number(), userDelete.getLatitude(), userDelete.getLongitude());
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
        return null;
    }

    // Crear Cuenta
    public UserResponseDTO addAccountToUser(Long id_user, AccountRequestDTO accountRequestDTO) throws Exception {
        Account account = mapToAccount(accountRequestDTO);
        Optional<User> optionalUser = userRepository.findById(id_user);
        try {
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                account.addUser(user);
                user.addAccount(account);
                accountRepository.save(account);
                userRepository.save(user);
                return new UserResponseDTO(user.getId_user(), user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude(), user.getAccounts());
            }
        } catch (Exception e) {
            throw new Exception("User or Account not found");
        }
        return null;
    }

    // Vincular Account a User
    public UserResponseDTO linkAccountToUser(Long userId, Long accountId) throws Exception {
        User user = userRepository.findById(userId).orElse(null);
        Account account = accountRepository.findById(accountId).orElse(null);
        try {
            if (user != null && account != null) {
                user.addAccount(account);
                account.addUser(user);
                accountRepository.save(account);
                userRepository.save(user);
                return new UserResponseDTO(user.getId_user(), user.getName(), user.getLastName(), user.getEmail(), user.getPhone_number(), user.getRole(), user.getLatitude(), user.getLongitude(), account);
            }
        } catch (Exception e) {
            throw new Exception("User or Account not found");
        }
        return null;
    }

    private User mapToUser(UserRequestDTO userResponseDTO) throws Exception {
        User user = new User();
        user.setName(userResponseDTO.getName());
        user.setLastName(userResponseDTO.getLastName());
        user.setEmail(userResponseDTO.getEmail());
        user.setPhone_number(userResponseDTO.getPhone_number());
        user.setRole(userResponseDTO.getRole());
        user.setLatitude(userResponseDTO.getLatitude());
        user.setLongitude(userResponseDTO.getLongitude());
        return user;
    }

    private Account mapToAccount(AccountRequestDTO accountDTO) throws Exception {
        Account account = new Account();
        account.setDateHigh(accountDTO.getDateHigh());
        account.setBalance(accountDTO.getBalance());
        return account;
    }

    public TripDTO startTrip(Long id_user, Long id_account) throws Exception {
        User user = userRepository.findById(id_user).get();
        // Obtener scooter más cercano
        ScooterDTO scooter = scooterFeignClient.getScooterNearest(user.getLatitude(), user.getLongitude());
        System.out.println(scooter);
        if (scooter != null) {
            return tripFeignClient.startTrip(id_user, id_account, scooter.getId_scooter());
        }
        return null;
    }

    public TripDTO endTrip(Long id_user, Long id_account) throws Exception {
        User user = userRepository.findById(id_user).get();
        return tripFeignClient.endTrip(user.getId_user(), id_account);
    }

    public PauseDto pauseTrip(Long id_user, Long id_account) throws Exception {
        User user = userRepository.findById(id_user).get();
        return tripFeignClient.pauseTrip(user.getId_user(), id_account);
    }

    public PauseDto unpauseTrip(Long id_user, Long id_account) throws Exception {
        User user = userRepository.findById(id_user).get();
        return tripFeignClient.unpauseTrip(user.getId_user(), id_account);
    }

    public List<ScooterDTO> getScooterNearby(Long id_user, Double radius) {
        User user = userRepository.findById(id_user).get();
        return scooterFeignClient.getScooterNearby(user.getLatitude(), user.getLongitude(), radius);
    }
}
