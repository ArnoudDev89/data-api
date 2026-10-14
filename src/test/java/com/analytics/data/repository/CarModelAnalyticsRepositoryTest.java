package com.analytics.data.repository;

import com.analytics.data.entity.CarModelAnalyticsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarModelAnalyticsRepositoryTest {

    @Mock
    private CarModelAnalyticsRepository repository;

    @Test
    void findByModel_ShouldReturnEntityWhenExists() {
        CarModelAnalyticsEntity entity = new CarModelAnalyticsEntity();
        entity.setModel("Golf");
        entity.setPosts(10L);

        when(repository.findByModel("Golf")).thenReturn(Optional.of(entity));

        Optional<CarModelAnalyticsEntity> result = repository.findByModel("Golf");

        assertTrue(result.isPresent());
        assertEquals("Golf", result.get().getModel());
        verify(repository).findByModel("Golf");
    }

    @Test
    void findByModel_ShouldReturnEmptyWhenNotExists() {
        when(repository.findByModel("Ferrari")).thenReturn(Optional.empty());

        Optional<CarModelAnalyticsEntity> result = repository.findByModel("Ferrari");

        assertTrue(result.isEmpty());
        verify(repository).findByModel("Ferrari");
    }
}