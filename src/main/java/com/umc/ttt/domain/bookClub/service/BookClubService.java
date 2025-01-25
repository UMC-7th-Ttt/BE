package com.umc.ttt.domain.bookClub.service;

import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;
import org.springframework.data.domain.Page;

public interface BookClubService {
    public BookClub addBookClub(BookClubRequestDTO.AddUpdateDTO request);
    public BookClub updateBookClub(Long bookClubId, BookClubRequestDTO.AddUpdateDTO request);
    public void deleteBookClub(Long bookClubId);
    public Page<BookClub> getBookClubPreViewList(Integer page);
    public BookClub getBookClub(Long bookClubId);
}
