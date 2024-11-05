package com.luongchivi.post_service.repository.httpclient;

import com.luongchivi.post_service.dto.response.userProfile.UserProfileResponse;
import com.luongchivi.post_service.share.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "profile-service", url = "${app.service.user-profile.url}")
public interface UserProfileClient {
    @GetMapping("/internal/users/{userId}")
    ApiResponse<UserProfileResponse> getUserProfile(@PathVariable String userId);
}
