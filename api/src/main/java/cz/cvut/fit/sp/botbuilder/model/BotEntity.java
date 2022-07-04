package cz.cvut.fit.sp.botbuilder.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.DocumentReference;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
@Document(collection = "bots")
public class BotEntity {

    @org.springframework.data.annotation.Id
    private String id;

    private String token;

    private String owner;

    private String name;

    private MessageEntity initialMessage;

    @DocumentReference
    private List<MessageEntity> stages;

    private HashMap<String, HashMap<String, String>> routes;

    public BotEntity() {
        stages = new ArrayList<MessageEntity>();
        routes = new HashMap<String, HashMap<String, String>>();
    }

    public void addRouteList(String id) {
        routes.put(id, new HashMap<String, String>());
    }

    public void addConditionalRoute(String idSource, String idRequest, String idDest) {
        routes.get(idSource).put(idRequest, idDest);
    }

    public void addUnconditionalRoute(String idSource, String idDest) {
        routes.get(idSource).put("epsilon", idDest);
    }

    public void addMessage(MessageEntity message) { this.stages.add(message); }
}
