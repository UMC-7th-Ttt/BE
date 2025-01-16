package com.umc.ttt.domain.place.service.impl;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.entity.enums.Role;
import com.umc.ttt.domain.place.converter.PlaceConverter;
import com.umc.ttt.domain.place.dto.PlaceResponseDTO;
import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.place.repository.PlaceRepository;
import com.umc.ttt.domain.place.service.PlaceQueryService;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.handler.PlaceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PlaceQueryServiceImpl implements PlaceQueryService {

    private final PlaceRepository placeRepository;

    @Override
    public PlaceResponseDTO.PlaceDTO getPlace(Long placeId, Member member) {
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));
        boolean isAdmin = member.getRole() == Role.ADMIN;
        return PlaceConverter.toPlaceDTO(place, member, isAdmin);
    }
}
