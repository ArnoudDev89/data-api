package com.analytics.data.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BrandAnalyticsEntityTest {

    @Test
    void noArgsConstructor_ShouldCreateEmptyInstance() {
        BrandAnalyticsEntity entity = new BrandAnalyticsEntity();

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getBrand());
        assertNull(entity.getPosts());
    }

    @Test
    void settersAndGetters_ShouldWork() {
        BrandAnalyticsEntity entity = new BrandAnalyticsEntity();
        entity.setId(1L);
        entity.setBrand("VW");
        entity.setPosts(10L);

        assertEquals(1L, entity.getId());
        assertEquals("VW", entity.getBrand());
        assertEquals(10L, entity.getPosts());
    }

    @Test
    void equalsAndHashCode_ShouldBeConsistent() {
        BrandAnalyticsEntity entity1 = new BrandAnalyticsEntity();
        entity1.setId(1L);
        entity1.setBrand("VW");
        entity1.setPosts(10L);

        BrandAnalyticsEntity entity2 = new BrandAnalyticsEntity();
        entity2.setId(1L);
        entity2.setBrand("VW");
        entity2.setPosts(10L);

        BrandAnalyticsEntity entity3 = new BrandAnalyticsEntity();
        entity3.setId(2L);
        entity3.setBrand("Honda");

        assertEquals(entity1, entity2);
        assertEquals(entity1.hashCode(), entity2.hashCode());
        assertNotEquals(entity1, entity3);
    }

    @Test
    void toString_ShouldContainFields() {
        BrandAnalyticsEntity entity = new BrandAnalyticsEntity();
        entity.setBrand("VW");

        assertTrue(entity.toString().contains("VW"));
    }
}