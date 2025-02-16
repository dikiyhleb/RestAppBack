package dev.barapp.service;

import dev.barapp.entities.TableEntity;
import dev.barapp.repositories.TableRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.crossstore.ChangeSetPersister;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TableService {
    private final TableRepository tableRepository;

    public boolean isNonReserved(TableEntity checkTable) throws ChangeSetPersister.NotFoundException {
        TableEntity table = tableRepository.findById(checkTable.getId()).orElseThrow(ChangeSetPersister.NotFoundException::new);

        return switch (table.getStatus()) {
            case RESERVED -> false;
            case FREE -> true;
            default -> throw new ChangeSetPersister.NotFoundException();
        };
    }
}
