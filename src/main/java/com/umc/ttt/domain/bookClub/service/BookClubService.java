package com.umc.ttt.domain.bookClub.service;

import com.umc.ttt.domain.bookClub.dto.BookClubRequestDTO;
import com.umc.ttt.domain.bookClub.entity.BookClub;

public interface BookClubService {
    public BookClub addBookClub(BookClubRequestDTO.AddUpdateDTO request);
    public BookClub updateBookClub(Long bookClubId, BookClubRequestDTO.AddUpdateDTO request);
}
