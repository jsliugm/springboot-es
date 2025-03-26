package com.universe.domain;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;
import org.springframework.data.elasticsearch.annotations.Setting;

@Data
//@Setting(settingPath = "elastic-index.properties")
@Document(indexName = "person")
public class Person {
    @Id
    private String id;

    @Field(type = FieldType.Text)
    private String name;

    @Field(type = FieldType.Text, analyzer = "ik_analyzer")
    private String address;

    @Field(type = FieldType.Integer)
    private Integer score;

    @Field(type = FieldType.Integer)
    private Integer age;
    @Field(type = FieldType.Integer)
    private Integer age01;
    @Field(type = FieldType.Integer)
    private Integer age02;
    @Field(type = FieldType.Integer)
    private Integer age03;
    @Field(type = FieldType.Integer)
    private Integer age04;
    @Field(type = FieldType.Integer)
    private Integer age05;
    @Field(type = FieldType.Integer)
    private Integer age06;
    @Field(type = FieldType.Integer)
    private Integer age07;
    @Field(type = FieldType.Integer)
    private Integer age08;
    @Field(type = FieldType.Integer)
    private Integer age09;
    @Field(type = FieldType.Integer)
    private Integer age10;
    @Field(type = FieldType.Integer)
    private Integer age11;
    @Field(type = FieldType.Integer)
    private Integer age12;
    @Field(type = FieldType.Integer)
    private Integer age13;
    @Field(type = FieldType.Integer)
    private Integer age14;
    @Field(type = FieldType.Integer)
    private Integer age15;
    @Field(type = FieldType.Integer)
    private Integer age16;
    @Field(type = FieldType.Integer)
    private Integer age17;
    @Field(type = FieldType.Integer)
    private Integer age18;
    @Field(type = FieldType.Integer)
    private Integer age19;
    @Field(type = FieldType.Integer)
    private Integer age20;
    @Field(type = FieldType.Integer)
    private Integer age21;
    @Field(type = FieldType.Integer)
    private Integer age22;
    @Field(type = FieldType.Integer)
    private Integer age23;
    @Field(type = FieldType.Integer)
    private Integer age24;
    @Field(type = FieldType.Integer)
    private Integer age25;
    @Field(type = FieldType.Integer)
    private Integer age26;
    @Field(type = FieldType.Integer)
    private Integer age27;
    @Field(type = FieldType.Integer)
    private Integer age28;
    @Field(type = FieldType.Integer)
    private Integer age29;
    @Field(type = FieldType.Integer)
    private Integer age30;
    @Field(type = FieldType.Integer)
    private Integer age31;
    @Field(type = FieldType.Integer)
    private Integer age32;
    @Field(type = FieldType.Integer)
    private Integer age33;
    @Field(type = FieldType.Integer)
    private Integer age34;
    @Field(type = FieldType.Integer)
    private Integer age35;
    @Field(type = FieldType.Integer)
    private Integer age36;
    @Field(type = FieldType.Integer)
    private Integer age37;
    @Field(type = FieldType.Integer)
    private Integer age38;
    @Field(type = FieldType.Integer)
    private Integer age39;
    @Field(type = FieldType.Integer)
    private Integer age40;
    @Field(type = FieldType.Integer)
    private Integer age41;
    @Field(type = FieldType.Integer)
    private Integer age42;
    @Field(type = FieldType.Integer)
    private Integer age43;
    @Field(type = FieldType.Integer)
    private Integer age44;
    @Field(type = FieldType.Integer)
    private Integer age45;
    @Field(type = FieldType.Integer)
    private Integer age46;
    @Field(type = FieldType.Integer)
    private Integer age47;
    @Field(type = FieldType.Integer)
    private Integer age48;
    @Field(type = FieldType.Integer)
    private Integer age49;
    @Field(type = FieldType.Integer)
    private Integer age50;
    @Field(type = FieldType.Integer)
    private Integer age51;
    @Field(type = FieldType.Integer)
    private Integer age52;
    @Field(type = FieldType.Integer)
    private Integer age53;
    @Field(type = FieldType.Integer)
    private Integer age54;
    @Field(type = FieldType.Integer)
    private Integer age55;
}