package com.luongchivi.profile_service.serivce;

import com.luongchivi.profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.profile_service.dto.response.userProfileResponse.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createUserProfile(CreationUserProfileRequest request);
    UserProfileResponse getUserProfile(String userProfileId);
}
