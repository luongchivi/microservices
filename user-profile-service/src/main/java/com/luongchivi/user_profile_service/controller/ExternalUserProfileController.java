package com.luongchivi.user_profile_service.controller;

import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.serivce.UserProfileService;
import com.luongchivi.user_profile_service.share.response.ApiResponse;
import com.luongchivi.user_profile_service.share.response.PageResponse;
import jakarta.validation.constraints.Min;
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
    ApiResponse<UserProfileResponse> getUserProfile(@PathVariable("userProfileId") String userProfileId) {
        UserProfileResponse userProfileResponse = userProfileService.getUserProfile(userProfileId);
        return ApiResponse.<UserProfileResponse>builder().results(userProfileResponse).build();
    }

    @GetMapping()
    ApiResponse<PageResponse<UserProfileResponse>> getUsersProfile(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Min(value = 1, message = "PAGE_INVALID")
            int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            @Min(value = 1, message = "PAGE_SIZE_INVALID")
            int pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {
        PageResponse<UserProfileResponse> usersProfileResponse = userProfileService.getUsersProfile(page, pageSize, sort, sortBy);
        return ApiResponse.<PageResponse<UserProfileResponse>>builder().results(usersProfileResponse).build();
    }
}
