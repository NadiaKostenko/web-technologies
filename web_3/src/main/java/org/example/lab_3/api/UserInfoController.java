package org.example.lab_3.api;

import io.jsonwebtoken.Claims;
import org.example.lab_3.jwt.JwtDecoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
public class UserInfoController {

    private final JwtDecoder jwtDecoder;

    @Autowired
    public UserInfoController (JwtDecoder jwtDecoder) {
            this.jwtDecoder = jwtDecoder;
    }

    @GetMapping("/userinfo")
    public ResponseEntity<?> getUserInfo(@RequestHeader("Authorization") String authHeader) {
        try {
            String token = authHeader.replace("Bearer ", "");
            System.out.println("Got token: " + token);
            Claims claims = jwtDecoder.decodeToken (token);

            System.out.println("Got claims: " + claims);

            String userId = claims.getSubject();
            String username = claims.get("name", String.class);

            Map<String, Object> userInfo = new HashMap<>();
            userInfo.put("userId", userId);
            userInfo.put("username", username);

            return ResponseEntity.ok(userInfo);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token");
        }
    }

}
