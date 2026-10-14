package com.analytics.data.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarModelAnalyticsEntityTest {

    @Test
    void noArgsConstructor_ShouldCreateEmptyInstance() {
        CarModelAnalyticsEntity entity = new CarModelAnalyticsEntity();

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getModel());
        assertNull(entity.getPosts());
    }

    @Test
    void settersAndGetters_ShouldWork() {
        CarModelAnalyticsEntity entity = new CarModelAnalyticsEntity();
        entity.setId(1L);
        entity.setModel("Golf");
        entity.setPosts(20L);

        assertEquals(1L, entity.getId());
        assertEquals("Golf", entity.getModel());
        assertEquals(20L, entity.getPosts());
    }

    @Test
    void equalsAndHashCode_ShouldBeConsistent() {
        CarModelAnalyticsEntity e1 = new CarModelAnalyticsEntity();
        e1.setId(1L);
        e1.setModel("Golf");
        e1.setPosts(20L);

        CarModelAnalyticsEntity e2 = new CarModelAnalyticsEntity();
        e2.setId(1L);
        e2.setModel("Golf");
        e2.setPosts(20L);

        CarModelAnalyticsEntity e3 = new CarModelAnalyticsEntity();
        e3.setId(2L);
        e3.setModel("Civic");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1, e3);
    }

    @Test
    void toString_ShouldContainFields() {
        CarModelAnalyticsEntity entity = new CarModelAnalyticsEntity();
        entity.setModel("Golf");

        assertTrue(entity.toString().contains("Golf"));
    }
}