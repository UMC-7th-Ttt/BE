package com.umc.ttt.domain.place.service.impl;

import com.umc.ttt.domain.member.entity.Member;
import com.umc.ttt.domain.member.entity.enums.Role;
import com.umc.ttt.domain.place.converter.PlaceConverter;
import com.umc.ttt.domain.place.dto.PlaceRequestDTO;
import com.umc.ttt.domain.place.dto.PlaceResponseDTO;
import com.umc.ttt.domain.place.entity.Place;
import com.umc.ttt.domain.place.repository.PlaceRepository;
import com.umc.ttt.domain.place.service.PlaceCommandService;
import com.umc.ttt.global.apiPayload.code.status.ErrorStatus;
import com.umc.ttt.global.apiPayload.exception.handler.MemberHandler;
import com.umc.ttt.global.apiPayload.exception.handler.PlaceHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class PlaceCommandServiceImpl implements PlaceCommandService {

    private final PlaceRepository placeRepository;

    // 관리자 여부 확인
    private void isAdmin(Member member) {
        if (member.getRole() != Role.ADMIN) {
            throw new MemberHandler(ErrorStatus.PERMISSION_DENIED);
        }
    }

    @Override
    @Transactional
    public PlaceResponseDTO.CurationDTO updateCuration(Long placeId, PlaceRequestDTO.CurationDTO curationDTO, Member member) {
        isAdmin(member);
        Place place = placeRepository.findById(placeId)
                .orElseThrow(() -> new PlaceHandler(ErrorStatus.PLACE_NOT_FOUND));
        place.updateCuration(curationDTO.getCurationTitle(), curationDTO.getCurationContent());
        return PlaceConverter.toCurationPreviewDTO(place, member);
    }

}
