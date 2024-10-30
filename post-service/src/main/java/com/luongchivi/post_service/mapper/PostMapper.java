package com.luongchivi.post_service.mapper;

import com.luongchivi.post_service.dto.request.PostCreationRequest;
import com.luongchivi.post_service.dto.response.PostResponse;
import com.luongchivi.post_service.entity.Post;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PostMapper {
    Post toPost(PostCreationRequest request);
    PostResponse toPostResponse(Post post);
}
