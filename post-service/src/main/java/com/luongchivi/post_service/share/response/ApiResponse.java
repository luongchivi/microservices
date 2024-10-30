package com.luongchivi.post_service.share.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@JsonInclude(JsonInclude.Include.NON_NULL)
@ToString
@Builder
public class ApiResponse<T> {
    @Builder.Default
    int code = 1000;

    String message;
    T results;
}
