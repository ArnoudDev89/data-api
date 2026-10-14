package com.analytics.data.service;

import com.analytics.data.dto.CarPostDTO;
import com.analytics.data.entity.BrandAnalyticsEntity;
import com.analytics.data.entity.CarModelAnalyticsEntity;
import com.analytics.data.entity.CarModelPriceEntity;
import com.analytics.data.repository.BrandAnalyticsRepository;
import com.analytics.data.repository.CarModelAnalyticsRepository;
import com.analytics.data.repository.CarPriceAnalyticsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PostAnalyticsServiceImplTest {

    @Mock
    private BrandAnalyticsRepository brandAnalyticsRepository;
    @Mock
    private CarModelAnalyticsRepository carModelAnalyticsRepository;
    @Mock
    private CarPriceAnalyticsRepository carPriceAnalyticsRepository;

    private PostAnalyticsServiceImpl service;

    @BeforeEach
    void setUp() {
        service = new PostAnalyticsServiceImpl();
        // inject mocks via reflection because @Autowired fields
        org.springframework.test.util.ReflectionTestUtils.setField(service, "brandAnalyticsRepository", brandAnalyticsRepository);
        org.springframework.test.util.ReflectionTestUtils.setField(service, "carModelAnalyticsRepository", carModelAnalyticsRepository);
        org.springframework.test.util.ReflectionTestUtils.setField(service, "carPriceAnalyticsRepository", carPriceAnalyticsRepository);
    }

    private CarPostDTO buildDTO() {
        return CarPostDTO.builder()
                .brand("VW")
                .model("Golf")
                .price(90000.0)
                .build();
    }

    @Test
    void saveDataAnalytics_NewBrandAndModel_ShouldCreate() {
        when(brandAnalyticsRepository.findByBrand("VW")).thenReturn(Optional.empty());
        when(carModelAnalyticsRepository.findByModel("Golf")).thenReturn(Optional.empty());

        service.saveDataAnalytics(buildDTO());

        ArgumentCaptor<BrandAnalyticsEntity> brandCaptor = ArgumentCaptor.forClass(BrandAnalyticsEntity.class);
        verify(brandAnalyticsRepository).save(brandCaptor.capture());
        assertEquals("VW", brandCaptor.getValue().getBrand());
        assertEquals(1L, brandCaptor.getValue().getPosts());

        ArgumentCaptor<CarModelAnalyticsEntity> modelCaptor = ArgumentCaptor.forClass(CarModelAnalyticsEntity.class);
        verify(carModelAnalyticsRepository).save(modelCaptor.capture());
        assertEquals("Golf", modelCaptor.getValue().getModel());
        assertEquals(1L, modelCaptor.getValue().getPosts());

        ArgumentCaptor<CarModelPriceEntity> priceCaptor = ArgumentCaptor.forClass(CarModelPriceEntity.class);
        verify(carPriceAnalyticsRepository).save(priceCaptor.capture());
        assertEquals("Golf", priceCaptor.getValue().getModel());
        assertEquals(90000.0, priceCaptor.getValue().getPrice());
    }

    @Test
    void saveDataAnalytics_ExistingBrandAndModel_ShouldIncrement() {
        BrandAnalyticsEntity existingBrand = new BrandAnalyticsEntity();
        existingBrand.setId(1L);
        existingBrand.setBrand("VW");
        existingBrand.setPosts(5L);

        CarModelAnalyticsEntity existingModel = new CarModelAnalyticsEntity();
        existingModel.setId(1L);
        existingModel.setModel("Golf");
        existingModel.setPosts(3L);

        when(brandAnalyticsRepository.findByBrand("VW")).thenReturn(Optional.of(existingBrand));
        when(carModelAnalyticsRepository.findByModel("Golf")).thenReturn(Optional.of(existingModel));

        service.saveDataAnalytics(buildDTO());

        verify(brandAnalyticsRepository).save(argThat(b -> b.getPosts() == 6L));
        verify(carModelAnalyticsRepository).save(argThat(m -> m.getPosts() == 4L));
        verify(carPriceAnalyticsRepository).save(any(CarModelPriceEntity.class));
    }
}