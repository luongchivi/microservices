package com.luongchivi.user_profile_service.serivce.Impl;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.entity.UserProfile;
import com.luongchivi.user_profile_service.mapper.UserProfileMapper;
import com.luongchivi.user_profile_service.repository.UserProfileRepository;
import com.luongchivi.user_profile_service.serivce.UserProfileService;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserProfileServiceImpl implements UserProfileService {
    UserProfileRepository userProfileRepository;

    UserProfileMapper userProfileMapper;

    public UserProfileResponse createUserProfile(CreationUserProfileRequest request) {
        UserProfile userProfile = userProfileMapper.toUserProfile(request);
        UserProfile saveUserProfile = userProfileRepository.save(userProfile);
        return userProfileMapper.toUserProfileResponse(saveUserProfile);
    }

    public UserProfileResponse getUserProfile(String userProfileId) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId).orElseThrow(() -> new RuntimeException("UserProfile not found"));
        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileResponse(userProfile);
        return userProfileResponse;
    }
}
