package com.relation.pager;

/**
 * Created by T.Cage on 2017/1/29.
 */
public class sqlExpression {
    private String name;
    private String operator;
    private String value;

    public sqlExpression() {
    }

    @Override
    public String toString() {
        return "sqlExpression{" +
                "name='" + name + '\'' +
                ", operator='" + operator + '\'' +
                ", value='" + value + '\'' +
                '}';
    }

    public sqlExpression(String name, String operator, String value) {
        this.name = name;
        this.operator = operator;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOperator() {
        return operator;
    }

    public void setOperator(String operator) {
        this.operator = operator;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
