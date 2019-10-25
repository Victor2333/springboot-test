package org.victor.test.POJO;


import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;

@JsonTypeInfo(use = JsonTypeInfo.Id.NAME, property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = TestStringPojo.class, name = "string"),
        @JsonSubTypes.Type(value = TestBooleanPojo.class, name = "boolean")
})
public interface ITest<T> {
    T getData();
    void setData(T t);
}
