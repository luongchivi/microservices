package com.luongchivi.api_gateway.repository;

import com.luongchivi.api_gateway.dto.request.introspect.IntrospectRequest;
import com.luongchivi.api_gateway.dto.response.introspect.IntrospectResponse;
import com.luongchivi.api_gateway.share.response.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.PostExchange;
import reactor.core.publisher.Mono;

@Component
public interface IdentityClient {
    @PostExchange(url = "/auth/introspect", contentType = MediaType.APPLICATION_JSON_VALUE)
    Mono<ApiResponse<IntrospectResponse>> introspect(@RequestBody IntrospectRequest request);
}
