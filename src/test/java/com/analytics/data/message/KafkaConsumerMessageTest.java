package com.analytics.data.message;

import com.analytics.data.dto.CarPostDTO;
import com.analytics.data.service.PostAnalyticsService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class KafkaConsumerMessageTest {

    @Mock
    private PostAnalyticsService postAnalyticsService;

    private KafkaConsumerMessage kafkaConsumerMessage;

    @BeforeEach
    void setUp() {
        kafkaConsumerMessage = new KafkaConsumerMessage(postAnalyticsService);
    }

    @Test
    void listening_ShouldDelegateToService() {
        CarPostDTO carPostDTO = CarPostDTO.builder()
                .model("Golf")
                .brand("VW")
                .price(90000.0)
                .build();

        kafkaConsumerMessage.listening(carPostDTO);

        verify(postAnalyticsService).saveDataAnalytics(carPostDTO);
    }

    @Test
    void listening_ShouldWorkWithNullPayload() {
        kafkaConsumerMessage.listening(null);

        verify(postAnalyticsService).saveDataAnalytics(null);
    }
}