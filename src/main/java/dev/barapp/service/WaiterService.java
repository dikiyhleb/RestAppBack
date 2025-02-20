package dev.barapp.service;

import dev.barapp.DTOs.manager.ManagerRegisterWaiterDTO;
import dev.barapp.DTOs.manager.ManagerUpdateWaiterDTO;
import dev.barapp.DTOs.manager.ManagerWaiterDTO;
import dev.barapp.entities.CredentialEntity;
import dev.barapp.entities.RestaurantEntity;
import dev.barapp.entities.WaiterEntity;
import dev.barapp.entities.enums.Role;
import dev.barapp.exceptions.UserAlreadyExistsException;
import dev.barapp.mappers.WaiterMapper;
import dev.barapp.repositories.CredentialRepository;
import dev.barapp.repositories.RestaurantRepository;
import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WaiterService {
    private final WaiterRepository waiterRepository;
    private final WaiterMapper waiterMapper;
    private final CredentialRepository credentialRepository;
    private final RestaurantRepository restaurantRepository;

    public WaiterEntity findAvailableWaiterByRestId(long restId) {
        List<WaiterEntity> waiters = waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);

        return waiters.get(0);
    }

    public List<ManagerWaiterDTO> findWaitersByRestaurantId(long restId) throws ChangeSetPersister.NotFoundException {
        List<WaiterEntity> waiters = waiterRepository.findWaiterEntitiesByRestaurantEntity_Id(restId);

        if (waiters.isEmpty()) {
            throw new ChangeSetPersister.NotFoundException();
        }

        return waiterMapper.toManagerWaitersDTO(waiters);
    }

    public ManagerRegisterWaiterDTO registerWaiter(ManagerRegisterWaiterDTO dto, long restId) throws ChangeSetPersister.NotFoundException {
        if(!credentialRepository.existsByEmail(dto.getEmail())) {
            RestaurantEntity restaurant = restaurantRepository.findById(restId).orElseThrow(ChangeSetPersister.NotFoundException::new);


            CredentialEntity credential = CredentialEntity.builder()
                    .role(Role.WAITER)
                    .email(dto.getEmail())
                    .password(dto.getPassword())
                    .build();

            WaiterEntity waiter = WaiterEntity.builder()
                    .name(dto.getName())
                    .restaurant(restaurant)
                    .credentialEntity(credential)
                    .build();

            credentialRepository.save(credential);
            waiterRepository.save(waiter);

            return ManagerRegisterWaiterDTO.builder()
                    .email(dto.getEmail())
                    .name(dto.getName())
                    .password(dto.getPassword())
                    .build();
        }
        else{
            throw new UserAlreadyExistsException("Email already exists!");
        }
    }

    public ManagerWaiterDTO updateWaiter(ManagerUpdateWaiterDTO dto) throws ChangeSetPersister.NotFoundException {
        if(credentialRepository.existsByEmail(dto.email())){
            WaiterEntity existWaiter = waiterRepository.findById(dto.id())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            CredentialEntity existCredential = credentialRepository.findCredentialEntityByEmail(dto.email())
                    .orElseThrow(ChangeSetPersister.NotFoundException::new);
            if(!Objects.equals(existWaiter.getCredentialEntity().getId(), existCredential.getId())){
                throw new UserAlreadyExistsException("Email already used another user!");
            }
        }
        WaiterEntity waiter = waiterRepository.findById(dto.id()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        CredentialEntity credential = waiter.getCredentialEntity();

        credential.setEmail(dto.email());
        credential.setPassword(dto.password());

        credentialRepository.save(credential);

        waiter.setName(dto.name());

        waiterRepository.save(waiter);

        return waiterMapper.toManagerWaiterDTO(waiter);
    }

    public WaiterEntity findWaiterById(long id) throws ChangeSetPersister.NotFoundException {
        return waiterRepository.findById(id).orElseThrow(ChangeSetPersister.NotFoundException::new);
    }
}
