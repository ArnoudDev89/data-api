package com.analytics.data.repository;

import com.analytics.data.entity.BrandAnalyticsEntity;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class BrandAnalyticsRepositoryTest {

    @Mock
    private BrandAnalyticsRepository repository;

    @Test
    void findByBrand_ShouldBeInvokable() {
        BrandAnalyticsEntity entity = new BrandAnalyticsEntity();
        entity.setBrand("VW");
        entity.setPosts(5L);

        when(repository.findByBrand("VW")).thenReturn(Optional.of(entity));

        Optional<BrandAnalyticsEntity> result = repository.findByBrand("VW");

        assertTrue(result.isPresent());
        assertEquals("VW", result.get().getBrand());
        verify(repository).findByBrand("VW");
    }
}