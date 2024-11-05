package com.luongchivi.user_profile_service.serivce;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.share.response.PageResponse;

public interface UserProfileService {
    UserProfileResponse createUserProfile(CreationUserProfileRequest request);
    UserProfileResponse getUserProfile(String userProfileId);
    UserProfileResponse getUserProfileByUserId(String userId);
    PageResponse<UserProfileResponse> getUsersProfile(int page, int pageSize, String sortDirection, String sortBy);
}
