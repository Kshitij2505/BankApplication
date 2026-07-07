package com.verinite.Atm_Application.controller;

import com.verinite.Atm_Application.dto.RoleRequest;
import com.verinite.Atm_Application.entity.Role;
import com.verinite.Atm_Application.repository.RoleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/roles")
@RequiredArgsConstructor
public class RoleController {

    private final RoleRepository roleRepository;

    @PostMapping
    public ResponseEntity<?> createRole(@RequestBody RoleRequest request) {

        Role role = Role.builder()
                .roleName(request.getRoleName())
                .build();

        return ResponseEntity.ok(roleRepository.save(role));
    }
}