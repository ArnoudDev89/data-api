package com.analytics.data.message;

import com.analytics.data.dto.CarPostDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.test.util.ReflectionTestUtils;

import static org.junit.jupiter.api.Assertions.assertNotNull;

class KafkaConsumerConfigsTest {

    private KafkaConsumerConfigs configs;

    @BeforeEach
    void setUp() {
        configs = new KafkaConsumerConfigs();
        ReflectionTestUtils.setField(configs, "bootstrapServers", "localhost:9092");
    }

    @Test
    void consumerFactory_ShouldCreateFactory() {
        ConsumerFactory<String, CarPostDTO> factory = configs.consumerFactory();

        assertNotNull(factory);
        assertNotNull(factory.getConfigurationProperties().get("bootstrap.servers"));
    }

    @Test
    void kafkaListenerContainerFactory_ShouldCreateFactory() {
        ConcurrentKafkaListenerContainerFactory<String, CarPostDTO> factory = configs.kafkaListenerContainerFactory();

        assertNotNull(factory);
        assertNotNull(factory.getConsumerFactory());
    }
}