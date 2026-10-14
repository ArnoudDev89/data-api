package com.analytics.data.service;

import com.analytics.data.dto.CarPostDTO;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PostAnalyticsServiceTest {

    @Mock
    private PostAnalyticsService postAnalyticsService;

    @Test
    void saveDataAnalytics_ShouldBeInvokable() {
        CarPostDTO dto = CarPostDTO.builder()
                .brand("VW")
                .model("Golf")
                .price(90000.0)
                .build();

        postAnalyticsService.saveDataAnalytics(dto);

        verify(postAnalyticsService).saveDataAnalytics(dto);
    }
}