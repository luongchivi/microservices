package com.luongchivi.identity_service.mapper;

import com.luongchivi.identity_service.dto.request.user.UserCreationRequest;
import com.luongchivi.identity_service.dto.request.userProfile.UserProfileCreationRequest;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileCreationRequest toUserProfileCreationRequest(UserCreationRequest request);
}
