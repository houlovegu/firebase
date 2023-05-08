package com.admin.common.entity;

import lombok.Data;

import java.io.Serializable;

@Data
public class ObjectItem implements Serializable {

    private String objectName;

    private Long size;
}
