package dev.barapp.controllers;

import dev.barapp.repositories.WaiterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/waiter")
@RequiredArgsConstructor
public class WaiterController {
    private final WaiterRepository waiterRepository;
}
