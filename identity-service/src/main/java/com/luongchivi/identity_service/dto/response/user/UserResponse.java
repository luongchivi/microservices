package com.luongchivi.identity_service.dto.response.user;

import java.time.LocalDateTime;
import java.util.Set;

import com.luongchivi.identity_service.dto.response.role.RoleResponse;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@Builder
@ToString
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    String id;
    String username;
    Set<RoleResponse> roles;
    LocalDateTime createdAt;
    LocalDateTime updatedAt;
}
