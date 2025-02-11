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
    @Autowired
    private MenuRepository menuRepository;
    @Autowired
    private ImageRespository imageRespository;

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

        ImageEntity image = ImageEntity.builder()
                .url("https://eda.yandex/images/3559865/ee31782ce8a630a015b6b94fd07b3351-648x312.jpg")
                .restaurant(rostics)
                .build();

        imageRespository.save(image);

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

        MenuEntity menu = MenuEntity.builder()
                .restaurant(rostics)
                .build();

        menuRepository.save(menu);

        waiters.add(waiter);
        rostics.setWaiters(waiters);
        rostics.setTables(tables);
        rostics.setManager(manager);
        rostics.setMenu(menu);
        rostics.setImg(List.of(image));

        restaurantRepository.save(rostics);

        RestaurantEntity restaurant1 = RestaurantEntity.builder()
                        .name("Бургер кинг")
                        .rating(BigDecimal.valueOf(4.4))
                .build();
        restaurantRepository.save(restaurant1);

        ImageEntity image1 = ImageEntity.builder()
                .url("https://eda.yandex/images/2353725/455defacc92cd9493e8dc3ff94ad9601-648x312.jpg")
                .restaurant(restaurant1)
                .build();
        imageRespository.save(image1);
        restaurant1.setImg(List.of(image1));
        restaurantRepository.save(restaurant1);

    }
}
