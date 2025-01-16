package com.umc.ttt.domain.book.controller;

import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.service.BookCommandService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {

    private final BookCommandService bookCommandService;

    @PostMapping("/fetch")
    @Operation(summary = "알라딘 Open API 데이터 저장", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> fetchBooks() {
        bookCommandService.fetchBooks();
        return ApiResponse.onSuccess("알라딘 Open API 데이터가 저장되었습니다.");
    }
}
