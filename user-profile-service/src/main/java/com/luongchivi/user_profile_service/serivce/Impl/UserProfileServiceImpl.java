package com.luongchivi.user_profile_service.serivce.Impl;

import com.luongchivi.user_profile_service.dto.request.userProfile.CreationUserProfileRequest;
import com.luongchivi.user_profile_service.dto.response.userProfileResponse.UserProfileResponse;
import com.luongchivi.user_profile_service.entity.UserProfile;
import com.luongchivi.user_profile_service.exception.AppException;
import com.luongchivi.user_profile_service.exception.ErrorCode;
import com.luongchivi.user_profile_service.mapper.UserProfileMapper;
import com.luongchivi.user_profile_service.repository.UserProfileRepository;
import com.luongchivi.user_profile_service.serivce.UserProfileService;
import com.luongchivi.user_profile_service.share.response.PageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public UserProfileResponse getUserProfileByUserId(String userId) {
        UserProfile userProfile = userProfileRepository.findByUserId(userId).orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_FOUND));
        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileResponse(userProfile);
        return userProfileResponse;
    }

    public UserProfileResponse getUserProfile(String userProfileId) {
        UserProfile userProfile = userProfileRepository.findById(userProfileId).orElseThrow(() -> new AppException(ErrorCode.USER_PROFILE_NOT_FOUND));
        UserProfileResponse userProfileResponse = userProfileMapper.toUserProfileResponse(userProfile);
        return userProfileResponse;
    }

    @PreAuthorize("hasRole('Admin')")
    public PageResponse<UserProfileResponse> getUsersProfile(int page, int pageSize, String sortDirection, String sortBy) {
        if (!sortDirection.equalsIgnoreCase("ASC") && !sortDirection.equalsIgnoreCase("DESC")) {
            throw new IllegalArgumentException("Invalid sort direction. Must be 'ASC' or 'DESC'.");
        }

        Sort sort;
        if (sortDirection.equalsIgnoreCase("DESC")) {
            sort = Sort.by(sortBy).descending();
        } else {
            sort = Sort.by(sortBy).ascending();
        }

        Pageable pageable = PageRequest.of(page - 1, pageSize, sort);
        Page<UserProfile> usersProfilePage = userProfileRepository.findAll(pageable);

        List<UserProfileResponse> usersProfileResponse = usersProfilePage.stream().map(item -> userProfileMapper.toUserProfileResponse(item)).collect(Collectors.toList());

        return PageResponse.<UserProfileResponse>builder()
                .currentPage(usersProfilePage.getNumber() + 1)
                .totalPages(usersProfilePage.getTotalPages())
                .pageSize(usersProfilePage.getSize())
                .totalElements(usersProfilePage.getTotalElements())
                .data(usersProfileResponse)
                .build();
    }
}
