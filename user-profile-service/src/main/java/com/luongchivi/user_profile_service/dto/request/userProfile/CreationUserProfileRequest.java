package com.luongchivi.user_profile_service.dto.request.userProfile;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class CreationUserProfileRequest {
    String userId;
    String firstName;
    String lastName;
    LocalDate dateOfBirth;
    String city;
}
