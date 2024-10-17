package com.luongchivi.user_profile_service.serivce;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;

public interface UserProfileService {
    UserProfileResponse createUserProfile(CreationUserProfileRequest request);
    UserProfileResponse getUserProfile(String userProfileId);
}
