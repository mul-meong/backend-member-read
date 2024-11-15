package com.mulmeong.member.read.member.kafka;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Map;

public class CommonJsonDeserializer {

    static Map<String, Object> getStringObjectMap(String bootstrapServer, String groupId) {

        return Map.of(
                ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServer,
                ConsumerConfig.GROUP_ID_CONFIG, groupId,
                ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
                ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, ErrorHandlingDeserializer.class,
                ErrorHandlingDeserializer.KEY_DESERIALIZER_CLASS, StringDeserializer.class.getName(),
                ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class.getName(),
                JsonDeserializer.TRUSTED_PACKAGES, "com.mulmeong.event.member"
        );
    }
}

//        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "latest");  //메시지 오프셋 설정
//        props.put(ConsumerConfig.RECONNECT_BACKOFF_MS_CONFIG, 50); // 재연결 시도 간격 5초
//        props.put(ConsumerConfig.RECONNECT_BACKOFF_MAX_MS_CONFIG, 60000); // 최대 재연결 시도 간격 1분
//        /*confluent kafka 연결을 위한 설정*/
//        props.put(CommonClientConfigs.SECURITY_PROTOCOL_CONFIG,"SASL_SSL");
//        props.put(SaslConfigs.SASL_MECHANISM,"PLAIN");
//        props.put(SaslConfigs.SASL_JAAS_CONFIG,String.format("org.apache.kafka.common.security.plain.PlainLoginModule
//        required username=\"%s\" password=\"%s\";", apiKey, apiSecret));

