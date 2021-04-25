package nl.themagrid.helper.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.quarkus.runtime.annotations.RegisterForReflection;

import javax.enterprise.context.Dependent;
import javax.enterprise.inject.Produces;

@Dependent
@RegisterForReflection
public class MyObjectMapper {
    @Produces
    public ObjectMapper getObjectMapper() {
        ObjectMapper mapper = new ObjectMapper();
        // Don't throw an exception when json has extra fields, unknown to the object.
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);

        // Ignore null values when writing json.
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        // Write times as a String instead of a Long so its human readable.
        mapper.configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
        mapper.registerModule(new JavaTimeModule());

        return mapper;
    }
}
