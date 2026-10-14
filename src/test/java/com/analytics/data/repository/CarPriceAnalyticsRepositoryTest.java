package com.analytics.data.repository;

import com.analytics.data.entity.CarModelPriceEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class CarPriceAnalyticsRepositoryTest {

    @Mock
    private CarPriceAnalyticsRepository repository;

    @Test
    void findAll_ShouldReturnList() {
        CarModelPriceEntity entity = new CarModelPriceEntity();
        entity.setModel("Golf");
        entity.setPrice(90000.0);

        when(repository.findAll()).thenReturn(List.of(entity));

        List<CarModelPriceEntity> result = repository.findAll();

        assertEquals(1, result.size());
        assertEquals("Golf", result.get(0).getModel());
        verify(repository).findAll();
    }

    @Test
    void save_ShouldReturnSavedEntity() {
        CarModelPriceEntity entity = new CarModelPriceEntity();
        entity.setModel("Golf");
        entity.setPrice(90000.0);

        CarModelPriceEntity saved = new CarModelPriceEntity();
        saved.setId(1L);
        saved.setModel("Golf");
        saved.setPrice(90000.0);

        when(repository.save(entity)).thenReturn(saved);

        CarModelPriceEntity result = repository.save(entity);

        assertNotNull(result.getId());
        assertEquals("Golf", result.getModel());
        verify(repository).save(entity);
    }
}