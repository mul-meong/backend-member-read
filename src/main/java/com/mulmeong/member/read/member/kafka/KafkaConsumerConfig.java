package com.mulmeong.member.read.member.kafka;

import com.mulmeong.event.member.MemberCreateDto;
import com.mulmeong.event.member.NicknameUpdateDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.util.Map;

@EnableKafka
@Configuration
public class KafkaConsumerConfig {

    @Value("${spring.kafka.bootstrap-servers}")
    private String bootstrapServers;

    @Value("${spring.kafka.consumer.group-id}")
    private String groupId;

    @Bean
    public Map<String, Object> readConsumerConfigs() {
        return CommonJsonDeserializer.getStringObjectMap(bootstrapServers, groupId);
    }

    /* 회원가입 후 이벤트 리스너 */
    @Bean
    public ConsumerFactory<String, MemberCreateDto> memberCreateDtoConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberCreateDto> memberCreateDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, MemberCreateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(memberCreateDtoConsumerFactory());
        return factory;
    }

    /* 닉네임 수정 후 이벤트 리스너 */
    @Bean
    public ConsumerFactory<String, NicknameUpdateDto> nicknameUpdateDtoConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, NicknameUpdateDto> nicknameUpdateDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, NicknameUpdateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(nicknameUpdateDtoConsumerFactory());
        return factory;
    }
}
