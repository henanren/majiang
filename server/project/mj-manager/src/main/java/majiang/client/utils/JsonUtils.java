package majiang.client.utils;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.*;

import java.io.IOException;
import java.lang.reflect.Type;
import java.text.SimpleDateFormat;
import java.util.List;

public class JsonUtils {
    public static final ObjectMapper mapper = new ObjectMapper();
    public static final String FORMAT_PATTERN = "yyyy-MM-dd HH:mm:ss";

    //private static final SimpleFilterProvider prov = new SimpleFilterProvider();
    static {


        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);

        //mapper.configure(SerializationFeature.INDENT_OUTPUT , true);
//		mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS , true);
//		mapper.configure(SerializationFeature.WRITE_NULL_MAP_VALUES , true);
//		mapper.configure(SerializationFeature.EAGER_SERIALIZER_FETCH , true);
//        mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_PATTERN);
        dateFormat.setLenient(false);
        mapper.setDateFormat(dateFormat);


        //mapper.constructType(t)
//		SerializerFactory.
//		mapper.setSerializerFactory(f);
//		mapper.getSerializerProvider().setNullValueSerializer(new JsonSerializer<Object>(){
//			@Override
//			public void serialize(Object value, JsonGenerator jgen,
//					SerializerProvider provider) throws IOException,
//					JsonProcessingException {
//				jgen.writeString("");
//				System.out.println(value);
//			}
//			
//		});
        //mapper.getSerializerProvider().
//		mapper.getSerializerProvider().findTypedValueSerializer(valueType, cache, property)(type)setDefaultKeySerializer(new JsonSerializer<Object>(){
//			@Override
//			public void serialize(Object value, JsonGenerator jgen,
//					SerializerProvider provider) throws IOException,
//					JsonProcessingException {
//				System.out.println("sb:"+value);
//			}
//		});
        //包装root元素
        //mapper.configure(SerializationFeature.WRAP_ROOT_VALUE , true);
//		mapper.enableDefaultTyping();
//		mapper.configure(DeserializationFeature. , false);
//		mapper.configure(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS , false);

    }

    public static ObjectMapper getObjectMapper() {
        return mapper;
    }

    public static final JsonFactory jsonFactory = new JsonFactory();

    /**
     * 不能处理复杂情况,和继承情况
     *
     * @param o
     * @return
     */
    public final static String serialize(Object o) {
        try {
            if (o instanceof List) {
                return mapper.writeValueAsString(((List<?>) o).toArray());
            }
            return mapper.writeValueAsString(o);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 不能处理复杂情况,和继承情况
     *
     * @param json
     * @param valueType
     * @return
     */
    public static <T> T deserialize(String json, Class<T> valueType) {
        try {
            return mapper.readValue(json, valueType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T deserialize(String json, final Type type) {
        try {
            return mapper.readValue(json, new TypeReference<Object>(){
                public Type getType() {
                    return type;
                }
            });
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static <T> T deserialize(String json, Class<?> parentClass, Class<?>... elementClasses) {
        try {
            JavaType javaType = mapper.getTypeFactory().constructParametricType(
                    parentClass, elementClasses
            );
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    /**
     * 不能处理复杂情况,和继承情况
     * 对付一般的List&lt;MyClass&gt; 方式足够了
     */
    public static <T> T deserialize(String json, TypeReference<T> valueTypeRef) {
        try {
            return mapper.readValue(json, valueTypeRef);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static JsonNode deserialize(String json) {
        try {
            return mapper.readTree(json);
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    public static ObjectMapper create(boolean isNoDefault) {
        ObjectMapper mapper = new ObjectMapper();

        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, true);

        if (isNoDefault) {
            mapper.setSerializationInclusion(JsonInclude.Include.NON_DEFAULT);
        }

        mapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);

        SimpleDateFormat dateFormat = new SimpleDateFormat(FORMAT_PATTERN);
        dateFormat.setLenient(false);
        mapper.setDateFormat(dateFormat);
        return mapper;
    }

}
