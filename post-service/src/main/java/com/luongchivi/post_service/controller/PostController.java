package com.luongchivi.post_service.controller;

import com.luongchivi.post_service.dto.request.PostCreationRequest;
import com.luongchivi.post_service.dto.response.post.PostResponse;
import com.luongchivi.post_service.service.PostService;
import com.luongchivi.post_service.share.response.ApiResponse;
import com.luongchivi.post_service.share.response.PageResponse;
import jakarta.validation.constraints.Min;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class PostController {
    PostService postService;

    @PostMapping("/create-post")
    ApiResponse<PostResponse> createPost(@RequestBody PostCreationRequest request) {
        PostResponse post = postService.createPost(request);
        return ApiResponse.<PostResponse>builder().results(post).build();
    }

    @GetMapping("/my-posts")
    ApiResponse<PageResponse<PostResponse>> myPosts(
            @RequestParam(value = "page", required = false, defaultValue = "1")
            @Min(value = 1, message = "PAGE_INVALID")
            int page,
            @RequestParam(value = "pageSize", required = false, defaultValue = "10")
            @Min(value = 1, message = "PAGE_SIZE_INVALID")
            int pageSize,
            @RequestParam(value = "sort", required = false, defaultValue = "ASC") String sort,
            @RequestParam(value = "sortBy", required = false, defaultValue = "id") String sortBy) {

        PageResponse<PostResponse> posts = postService.getMyPosts(page, pageSize, sort, sortBy);
        return ApiResponse.<PageResponse<PostResponse>>builder().results(posts).build();
    }
}
