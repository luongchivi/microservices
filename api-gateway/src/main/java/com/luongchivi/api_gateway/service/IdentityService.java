package com.luongchivi.api_gateway.service;

import com.luongchivi.api_gateway.dto.request.introspect.IntrospectRequest;
import com.luongchivi.api_gateway.dto.response.introspect.IntrospectResponse;
import com.luongchivi.api_gateway.repository.IdentityClient;
import com.luongchivi.api_gateway.share.response.ApiResponse;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class IdentityService {
    IdentityClient identityClient;
    public Mono<ApiResponse<IntrospectResponse>> introspect(String token) {
        return identityClient.introspect(IntrospectRequest.builder().token(token).build());
    }
}
