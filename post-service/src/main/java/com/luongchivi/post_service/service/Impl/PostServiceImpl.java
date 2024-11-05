package com.luongchivi.post_service.service.Impl;

import com.luongchivi.post_service.dto.request.PostCreationRequest;
import com.luongchivi.post_service.dto.response.post.PostResponse;
import com.luongchivi.post_service.dto.response.userProfile.UserProfileResponse;
import com.luongchivi.post_service.entity.Post;
import com.luongchivi.post_service.mapper.PostMapper;
import com.luongchivi.post_service.repository.PostRepository;
import com.luongchivi.post_service.repository.httpclient.UserProfileClient;
import com.luongchivi.post_service.service.PostService;
import com.luongchivi.post_service.share.response.PageResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.time.Instant;
import java.util.List;


@Service
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostServiceImpl implements PostService {
    PostRepository postRepository;
    PostMapper postMapper;
    UserProfileClient userProfileClient;

    public PostResponse createPost(PostCreationRequest request) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();
        UserProfileResponse userProfileResponse = null;

        try {
            userProfileResponse = userProfileClient.getUserProfile(userId).getResults();
        } catch (Exception e) {
            log.error("Error while getting userProfile", e);
        }

        String username = userProfileResponse != null ?
                userProfileResponse.getFirstName() + userProfileResponse.getLastName() : null;

        Post post = Post.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .userId(authentication.getName())
                .createdAt(Instant.now())
                .updatedAt(Instant.now())
                .build();

        post = postRepository.save(post);
        PostResponse postResponse = postMapper.toPostResponse(post);
        postResponse.setUsername(username);
        return postResponse;
    }

    public PageResponse<PostResponse> getMyPosts(int page, int pageSize, String sortDirection, String sortBy) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userId = authentication.getName();

        UserProfileResponse userProfileResponse = null;

        try {
            userProfileResponse = userProfileClient.getUserProfile(userId).getResults();
        } catch (Exception e) {
            log.error("Error while getting userProfile", e);
        }

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
        Page<Post> postsPage = postRepository.findAllByUserId(userId, pageable);

        String username = userProfileResponse != null ?
                userProfileResponse.getFirstName() + userProfileResponse.getLastName() : null;
        List<PostResponse> postsResponse = postsPage.getContent().stream()
                .map(post -> {
                    PostResponse postResponse = postMapper.toPostResponse(post);
                    postResponse.setUsername(username);
                    return postResponse;
                })
                .toList();

        return PageResponse.<PostResponse>builder()
                .currentPage(postsPage.getNumber() + 1)
                .totalPages(postsPage.getTotalPages())
                .pageSize(postsPage.getSize())
                .totalElements(postsPage.getTotalElements())
                .data(postsResponse)
                .build();
    }
}
