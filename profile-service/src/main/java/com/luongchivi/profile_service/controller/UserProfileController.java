package com.luongchivi.profile_service.controller;

import com.luongchivi.profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.profile_service.serivce.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileController {
    UserProfileService userProfileService;

    @PostMapping
    UserProfileResponse createUserProfile(@RequestBody CreationUserProfileRequest request) {
        UserProfileResponse userProfileResponse = userProfileService.createUserProfile(request);
        return userProfileResponse;
    }

    @GetMapping("/{userProfileId}")
    UserProfileResponse getUserProfile(@PathVariable("userProfileId") String userProfileId) {
        UserProfileResponse userProfileResponse = userProfileService.getUserProfile(userProfileId);
        return userProfileResponse;
    }
}
