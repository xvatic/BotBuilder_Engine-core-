package cz.cvut.fit.sp.botbuilder.api.dto;

import cz.cvut.fit.sp.botbuilder.model.BotEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class BotDTO {
    private String id;

    private String token;

    private String owner;

    private String name;

    public static BotDTO fromEntityToDTO(BotEntity botEntity) {
        BotDTO dto = new BotDTO();
        dto.setId(botEntity.getId());
        dto.setToken(botEntity.getToken());
        dto.setOwner(botEntity.getOwner());
        dto.setName(botEntity.getName());
        return dto;
    }

}
