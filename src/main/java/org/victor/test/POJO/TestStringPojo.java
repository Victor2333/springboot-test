package org.victor.test.POJO;


import lombok.Data;

@Data
class TestStringPojo implements ITest<String> {
    String data;
}
