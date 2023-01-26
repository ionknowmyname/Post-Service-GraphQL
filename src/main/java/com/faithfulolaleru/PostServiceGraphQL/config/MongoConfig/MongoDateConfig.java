package com.faithfulolaleru.PostServiceGraphQL.config.MongoConfig;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.convert.MongoCustomConversions;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.List;

@Configuration
public class MongoDateConfig {

    @Bean
    public MongoCustomConversions mongoCustomConversions() {
        return new MongoCustomConversions(List.of(
                new OffsetDateTimeReadConverter(),
                new OffsetDateTimeWriteConverter(),
                new OffsetDateTimeReadConverter2()
        ));
    }

    static class OffsetDateTimeWriteConverter implements Converter<OffsetDateTime, String> {
        @Override
        public String convert(OffsetDateTime source) {
            // return source.toInstant().atZone(ZoneOffset.UTC).toString();
            return source.toInstant().atZone(ZoneOffset.UTC).toOffsetDateTime().toString();
        }
    }

    static class OffsetDateTimeReadConverter implements Converter<String, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(String source) {
            return OffsetDateTime.parse(source);
        }
    }
    static class OffsetDateTimeReadConverter2 implements Converter<Date, OffsetDateTime> {
        @Override
        public OffsetDateTime convert(Date source) {
            return source.toInstant().atOffset(ZoneOffset.UTC);
        }
    }
}
