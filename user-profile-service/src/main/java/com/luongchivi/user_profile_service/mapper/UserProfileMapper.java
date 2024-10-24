package com.luongchivi.user_profile_service.mapper;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.entity.UserProfile;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfile toUserProfile(CreationUserProfileRequest request);

    UserProfileResponse toUserProfileResponse(UserProfile userProfile);
}
