package com.mulmeong.member.read.common.config;

import com.mulmeong.event.member.MemberCreateDto;
import com.mulmeong.event.member.MemberNicknameUpdateDto;
import com.mulmeong.event.member.MemberProfileImgUpdateDto;
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

    /**
     * {@link CommonJsonDeserializer} 에서 JSON 데이터를 역직렬화하는 설정을 가져옵니다.
     *
     * @return Kafka 컨슈머 설정을 포함하는 Map
     */
    @Bean
    public Map<String, Object> readConsumerConfigs() {
        return CommonJsonDeserializer.getStringObjectMap(bootstrapServers, groupId);
    }

    /**
     * 아래 내용부터는 특정 이벤트의 메시지를 처리하기 위한 ConsumerFactory와 ListenerContainerFactory를 생성합니다.
     *
     * @return 특정 이벤트 타입을 수신하는 컨슈머 설정 Map
     */

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
    public ConsumerFactory<String, MemberNicknameUpdateDto> nicknameUpdateDtoConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberNicknameUpdateDto> nicknameUpdateDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, MemberNicknameUpdateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(nicknameUpdateDtoConsumerFactory());
        return factory;
    }

    /* 프로필사진 수정 후 이벤트 리스너 */
    @Bean
    public ConsumerFactory<String, MemberProfileImgUpdateDto> profileUpdateDtoConsumerFactory() {

        return new DefaultKafkaConsumerFactory<>(readConsumerConfigs());
    }
    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, MemberProfileImgUpdateDto> profileUpdateDtoListener() {
        ConcurrentKafkaListenerContainerFactory<String, MemberProfileImgUpdateDto> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(profileUpdateDtoConsumerFactory());
        return factory;
    }
}
