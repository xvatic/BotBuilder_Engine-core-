package cz.cvut.fit.sp.botbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document
public class MessageEntity {
    @org.springframework.data.annotation.Id
    private String id;

    private String message;

    private boolean isResponse;

    public MessageEntity(String message, Boolean isResponse) {
        this.message = message;
        this.isResponse = isResponse;
    }
}
