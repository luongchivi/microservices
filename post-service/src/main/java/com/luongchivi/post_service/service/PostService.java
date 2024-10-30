package com.luongchivi.post_service.service;

import com.luongchivi.post_service.dto.request.PostCreationRequest;
import com.luongchivi.post_service.dto.response.PostResponse;
import com.luongchivi.post_service.share.response.PageResponse;

public interface PostService {
    PostResponse createPost(PostCreationRequest request);

    PageResponse<PostResponse> getMyPosts(int page, int pageSize, String sortDirection, String sortBy);
}
