package com.luongchivi.user_profile_service.controller;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.serivce.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/external/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ExternalUserProfileController {
    UserProfileService userProfileService;
    @GetMapping("/{userProfileId}")
    UserProfileResponse getUserProfile(@PathVariable("userProfileId") String userProfileId) {
        UserProfileResponse userProfileResponse = userProfileService.getUserProfile(userProfileId);
        return userProfileResponse;
    }
}
