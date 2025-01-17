package com.umc.ttt.domain.book.controller;

import com.umc.ttt.domain.book.dto.BookResponseDTO;
import com.umc.ttt.domain.book.service.BookCommandService;
import com.umc.ttt.domain.book.service.BookQueryService;
import com.umc.ttt.global.apiPayload.ApiResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookRestController {

    private final BookCommandService bookCommandService;
    private final BookQueryService bookQueryService;

    @PostMapping("/fetch")
    @Operation(summary = "알라딘 Open API 데이터 저장", description = "서버 테스트용 api입니다. 연동x")
    public ApiResponse<String> fetchBooks() {
        bookCommandService.fetchBooks();
        return ApiResponse.onSuccess("알라딘 Open API 데이터가 저장되었습니다.");
    }

    @GetMapping("/search")
    @Operation(summary = "책 검색", description = "책 검색 API이며, 검색 결과는 커서를 기반으로 페이징 처리됩니다.\n\n" +
            "첫 페이지 조회 시 각 cursor 값으로 0을 전달해주세요.\n\n" +
            "첫 페이지가 아닌 경우 이전 응답의 hasNext가 true일 때, nextCursor 값을 cursor로 전달해주세요.")
    @Parameters({
            @Parameter(name = "keyword", description = "검색 키워드"),
            @Parameter(name = "cursor", description = "페이지 커서, defualt 값은 0입니다."),
            @Parameter(name = "limit", description = "가져올 데이터 개수, defualt 값은 10입니다.")
    })
    public ApiResponse<BookResponseDTO.SearchBookResultDTO> searchBooks(@RequestParam(value = "keyword", required = true) String keyword,
                                                                        @RequestParam(value = "cursor", required = false, defaultValue = "0") long cursor,
                                                                        @RequestParam(value = "limit", required = false, defaultValue = "10") int limit) {
        BookResponseDTO.SearchBookResultDTO books = bookQueryService.searchBooks(keyword, cursor, limit);
        return ApiResponse.onSuccess(books);
    }
}
