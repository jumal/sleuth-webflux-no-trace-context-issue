package com.example.webfluxnotracecontext;

import brave.propagation.ExtraFieldPropagation;

public class TestEntity {

    private String field1;

    private String field2;

    public String getField1() {
        return field1;
    }

    public String getField2() {
        return field2;
    }

    public TestEntity() {
        super();
    }

    TestEntity(String field1) {
        this.field1 = field1;
    }

    TestEntity setContext() {
        field2 = ExtraFieldPropagation.get("X-Field2");
        return this;
    }
}
