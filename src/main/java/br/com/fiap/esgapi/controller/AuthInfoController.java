package br.com.fiap.esgapi.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/api/auth-info")
public class AuthInfoController {
    @GetMapping
    public Map<String, Object> authInfo(Authentication authentication) {
        return Map.of(
                "usuarioAutenticado", authentication.getName(),
                "perfis", authentication.getAuthorities().stream().map(Object::toString).toList()
        );
    }
}
