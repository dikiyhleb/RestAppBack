package dev.barapp;

import dev.barapp.entities.*;
import dev.barapp.entities.enums.Role;
import dev.barapp.entities.enums.TableStatus;
import dev.barapp.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Component
public class Initializer {

    @Autowired
    private CredentialRepository credentialRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private WaiterRepository waiterRepository;
    @Autowired
    private ManagerRepository managerRepository;
    @Autowired
    private RestaurantRepository restaurantRepository;

    public void initializeUsers() {
        userRepository.save(UserEntity.builder()
                .name("Ivan Ivanov")
                .credentialEntity(credentialRepository.save(CredentialEntity.builder()
                        .role(Role.USER)
                        .email("ivanivanov@example.com")
                        .password("1234")
                        .build()))
                .build());
    }

    public void initializeRestaurants() {
        List<WaiterEntity> waiters = new ArrayList<>();

        List<TableEntity> tables = new ArrayList<>();

        RestaurantEntity rostics = RestaurantEntity.builder()
                .name("Rostic's")
                .img("https://eda.yandex/images/3709189/b8ccc4b8b96de76f32cea0cd7d83650b-648x312.jpeg")
                .rating(BigDecimal.valueOf(5))
                .build();
        restaurantRepository.save(rostics);

        for (int i = 0; i < 10; i++) {
            tables.add(TableEntity.builder()
                            .numOfTable(i + 1)
                            .capacity(i % 3 == 0 ? i : i % 3)
                            .status(TableStatus.FREE)
                            .restaurant(rostics)
                    .build());
        }



        WaiterEntity waiter = WaiterEntity.builder()
                .name("Oksana Strukova")
                .credentialEntity(credentialRepository.save(CredentialEntity.builder()
                        .role(Role.WAITER)
                        .email("waiter@example.com")
                        .password("test")
                        .build()))
                .restaurant(rostics)
                .build();

        waiterRepository.save(waiter);

        ManagerEntity manager = ManagerEntity.builder()
                .name("Petr Petrov")
                .credentialEntity(credentialRepository.save(CredentialEntity.builder()
                        .role(Role.MANAGER)
                        .email("manager@example.com")
                        .password("1234")
                        .build()))
                .restaurant(rostics)
                .build();

        managerRepository.save(manager);

        waiters.add(waiter);
        rostics.setWaiters(waiters);
        rostics.setTables(tables);
        rostics.setManager(manager);

        restaurantRepository.save(rostics);

        restaurantRepository.save(RestaurantEntity.builder()
                        .name("Бургер кинг")
                        .img("https://eda.yandex/images/2353725/455defacc92cd9493e8dc3ff94ad9601-648x312.jpg")
                        .rating(BigDecimal.valueOf(4.4))
                .build());
        restaurantRepository.save(RestaurantEntity.builder()
                        .name("SICILIA")
                        .img("https://eda.yandex/images/3490335/3ec0c979b59f410b885a7c5f77a2668c-648x312.jpg")
                        .rating(BigDecimal.valueOf(4.7))
                .build());
        restaurantRepository.save(RestaurantEntity.builder()
                        .name("Мистер суши")
                        .img("https://eda.yandex/images/14739469/15bae80b1cb04aa28b2321de7305f06d-648x312.jpg")
                        .rating(BigDecimal.valueOf(4.6))
                .build());
        restaurantRepository.save(RestaurantEntity.builder()
                        .name("Epic Pizza")
                        .img("https://eda.yandex/images/14835768/9910e4db927348f9994ebf47b4b4fd13-648x312.jpg")
                        .rating(BigDecimal.valueOf(4.8))
                .build());
    }
}
