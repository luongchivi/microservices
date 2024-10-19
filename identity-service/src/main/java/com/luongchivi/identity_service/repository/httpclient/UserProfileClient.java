package com.luongchivi.identity_service.repository.httpclient;

import com.luongchivi.identity_service.dto.request.userProfile.UserProfileCreationRequest;
import com.luongchivi.identity_service.dto.response.userProfile.UserProfileResponse;
import com.luongchivi.identity_service.share.response.ApiResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


@FeignClient(name = "user-profile-service", url = "${app.services.user-profile}")
public interface UserProfileClient {
    @PostMapping(value = "/internal/users", produces = MediaType.APPLICATION_JSON_VALUE)
    ApiResponse<UserProfileResponse> createUserProfile(@RequestBody UserProfileCreationRequest request);
}
