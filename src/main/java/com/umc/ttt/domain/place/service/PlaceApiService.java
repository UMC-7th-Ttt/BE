package com.umc.ttt.domain.place.service;

public interface PlaceApiService {
    void fetchAndSaveOpenApiData() throws Exception;

    void updateImagesForAllPlaces();
}
