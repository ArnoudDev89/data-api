package com.analytics.data.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CarModelPriceEntityTest {

    @Test
    void noArgsConstructor_ShouldCreateEmptyInstance() {
        CarModelPriceEntity entity = new CarModelPriceEntity();

        assertNotNull(entity);
        assertNull(entity.getId());
        assertNull(entity.getModel());
        assertNull(entity.getPrice());
    }

    @Test
    void settersAndGetters_ShouldWork() {
        CarModelPriceEntity entity = new CarModelPriceEntity();
        entity.setId(1L);
        entity.setModel("Golf GTI");
        entity.setPrice(145000.0);

        assertEquals(1L, entity.getId());
        assertEquals("Golf GTI", entity.getModel());
        assertEquals(145000.0, entity.getPrice());
    }

    @Test
    void equalsAndHashCode_ShouldBeConsistent() {
        CarModelPriceEntity e1 = new CarModelPriceEntity();
        e1.setId(1L);
        e1.setModel("Golf");
        e1.setPrice(145000.0);

        CarModelPriceEntity e2 = new CarModelPriceEntity();
        e2.setId(1L);
        e2.setModel("Golf");
        e2.setPrice(145000.0);

        CarModelPriceEntity e3 = new CarModelPriceEntity();
        e3.setId(2L);
        e3.setModel("Civic");

        assertEquals(e1, e2);
        assertEquals(e1.hashCode(), e2.hashCode());
        assertNotEquals(e1, e3);
    }

    @Test
    void toString_ShouldContainFields() {
        CarModelPriceEntity entity = new CarModelPriceEntity();
        entity.setModel("Golf");

        assertTrue(entity.toString().contains("Golf"));
    }
}