package cz.cvut.fit.sp.botbuilder.api.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ConditionalRouteNewDTO {
    String sourceResponseMessage;

    String requestMessage;

    String destResponseMessage;
}
