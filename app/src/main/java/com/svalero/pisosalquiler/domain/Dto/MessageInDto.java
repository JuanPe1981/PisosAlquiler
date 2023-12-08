package com.svalero.pisosalquiler.domain.Dto;

import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MessageInDto implements Serializable {
    private String message;
    private String dateMessage;
    private String timeMessage;
    private long user;
    private long ad;
}
