package com.thanksgivingex.battlegaming.Controller.RestEntity;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RestItemEntity {

    private Long id;

    private String name;
}
