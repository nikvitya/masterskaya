package ru.yandex.exception.handler;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ErrorResponse {
    @Schema(description = "Общая причина ошибки")
    private final String error;
    @Schema(description = "Описание ошибки")
    private final String description;
}
