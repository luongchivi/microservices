package com.luongchivi.user_profile_service.controller;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.serivce.UserProfileService;
import com.luongchivi.user_profile_service.share.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/internal/users")
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InternalUserProfileController {
    UserProfileService userProfileService;

    @PostMapping
    ApiResponse<UserProfileResponse> createUserProfile(@RequestBody CreationUserProfileRequest request) {
        UserProfileResponse userProfileResponse = userProfileService.createUserProfile(request);
        return ApiResponse.<UserProfileResponse>builder().results(userProfileResponse).build();
    }

    @GetMapping("/{userId}")
    ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String userId) {
        UserProfileResponse userProfileResponse = userProfileService.getUserProfileByUserId(userId);
        return ApiResponse.<UserProfileResponse>builder().results(userProfileResponse).build();
    }
}
