package cz.cvut.fit.sp.botbuilder.api;


import cz.cvut.fit.base.bot_builder.base.exceptions.ItemNotFoundException;
import cz.cvut.fit.sp.botbuilder.api.dto.BotDTO;
import cz.cvut.fit.sp.botbuilder.api.dto.ConditionalRouteNewDTO;
import cz.cvut.fit.sp.botbuilder.business.exception.*;
import cz.cvut.fit.sp.botbuilder.business.service.*;
import cz.cvut.fit.sp.botbuilder.model.BotEntity;
import cz.cvut.fit.sp.botbuilder.model.MessageEntity;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping(path = "/bots")
public class BotController {

    private BotService botService;

    private SecurityService securityService;

    private MessageService messageService;


    @PutMapping("/{id}")
    public ResponseEntity setToken(@RequestBody BotEntity botConfig, @PathVariable String id) {
        try {
            botService.setToken(id, botConfig.getToken());
            return ResponseEntity.ok("SUCCESSFULLY ADDED");
        } catch (ItemNotFoundException e) {
            return ResponseEntity.badRequest().body("BOT NOT FOUND");
        } catch (RequiredBotHasDifferentOwnerException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PutMapping("/messages/{id}")
    public ResponseEntity addMessage(@RequestBody MessageEntity message, @PathVariable String id) throws RequiredBotHasDifferentOwnerException {
        try {
            MessageEntity messageEntity = messageService.create(message);
            botService.addMessage(messageEntity, id);
            return ResponseEntity.ok().body("SUCCESSFULLY ADDED");
        } catch (RequiredBotHasDifferentOwnerException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/routes/cond/new")
    public ResponseEntity createNewRouteFromNewMessages(@RequestBody ConditionalRouteNewDTO route, @PathVariable String id) {
        try {
            MessageEntity sourceResponseMessage = messageService.create(new MessageEntity(route.getSourceResponseMessage(), true));
            MessageEntity requestMessage = messageService.create(new MessageEntity(route.getRequestMessage(), false));
            MessageEntity destResponseMessage = messageService.create(new MessageEntity(route.getDestResponseMessage(), true));
            botService.addConditionalRoute(id, sourceResponseMessage.getId(), requestMessage.getId(), destResponseMessage.getId());
            return ResponseEntity.ok().body("SUCCESSFULLY ADDED");
        } catch (UnreachableMessageBranchException | RouteAlreadyExistsException | BadMessageTypeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RequiredBotHasDifferentOwnerException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("/{id}/routes/cond/existing")
    public ResponseEntity createNewRouteFromExistingMessages(@RequestBody ConditionalRouteNewDTO route, @PathVariable String id) {
        try {
            botService.addConditionalRoute(id, route.getSourceResponseMessage(), route.getRequestMessage(), route.getDestResponseMessage());
            return ResponseEntity.ok().body("SUCCESSFULLY ADDED");
        } catch (UnreachableMessageBranchException | RouteAlreadyExistsException | BadMessageTypeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        } catch (RequiredBotHasDifferentOwnerException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity deleteBot(@PathVariable String id) {
        try {
            botService.deleteBot(id);
            return ResponseEntity.ok().body("SUCCESSFULLY DELETED");
        } catch (RequiredBotHasDifferentOwnerException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        }
    }

    @PostMapping("generate/{id}")
    public ResponseEntity generateBot(@PathVariable String id) {
        try {
            botService.generateBot(id);
            return ResponseEntity.ok().body("SUCCESSFULLY GENERATED");
        } catch (RequiredBotHasDifferentOwnerException e) {
            return ResponseEntity.status(403).body(e.getMessage());
        } catch (NotEnoughParametersException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/")
    public BotDTO createBot(@RequestBody BotEntity initialBotInfo) {
        BotEntity bot = botService.createBot(initialBotInfo.getToken(), initialBotInfo.getName());
        return BotDTO.fromEntityToDTO(bot);
    }

    @GetMapping("/{id}")
    public ResponseEntity getBotById(@PathVariable String id) {
        return ResponseEntity.ok().body(botService.findById(id));
    }

    @GetMapping("/")
    public ResponseEntity getAll() {
        return ResponseEntity.ok().body(botService.findAll());
    }


}
