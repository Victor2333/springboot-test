package org.victor.test.POJO;

import lombok.Data;

@Data
public class TestBooleanPojo implements ITest<Boolean> {

    public static TestBooleanPojo create() {
        TestBooleanPojo testBooleanPojo = new TestBooleanPojo();
        testBooleanPojo.setData(true);
        return testBooleanPojo;
    }

    Boolean data;
}
