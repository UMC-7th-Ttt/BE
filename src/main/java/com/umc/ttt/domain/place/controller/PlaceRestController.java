package com.umc.ttt.domain.place.controller;

import com.umc.ttt.domain.place.service.impl.PlaceCommandService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/places")
public class PlaceRestController {

    private final PlaceCommandService placeCommandService;

    @PostMapping
    @Operation(summary = "독립서점, 북카페 Open API 데이터 저장", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> fetchBooks() throws Exception {
        placeCommandService.fetchAndSaveOpenApiData();
        return ApiResponse.onSuccess("독립서점, 북카페 Open API 데이터가 저장되었습니다.");
    }

}
