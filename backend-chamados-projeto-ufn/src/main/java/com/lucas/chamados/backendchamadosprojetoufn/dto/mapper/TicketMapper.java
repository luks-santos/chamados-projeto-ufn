package com.lucas.chamados.backendchamadosprojetoufn.dto.mapper;

import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketDTO;
import com.lucas.chamados.backendchamadosprojetoufn.dto.TicketListDTO;
import com.lucas.chamados.backendchamadosprojetoufn.entities.*;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Priority;
import com.lucas.chamados.backendchamadosprojetoufn.enuns.Status;
import com.lucas.chamados.backendchamadosprojetoufn.service.CommentService;
import com.lucas.chamados.backendchamadosprojetoufn.service.TopicService;
import com.lucas.chamados.backendchamadosprojetoufn.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class TicketMapper {

    private CommentService commentService;
    private TopicService topicService;
    private UserService userService;

    public TicketListDTO toDTO(Ticket ticket) {
        return new TicketListDTO(
                ticket.getId(),
                ticket.getStatus().getValue(),
                ticket.getPriority().getValue(),
                ticket.getCreatedAt(),
                ticket.getClosedAt(),
                ticket.getUser().getPerson().getName(),
                ticket.getUser().getPerson().getCellphone(),
                ticket.getComment(),
                ticket.getTopic(),
                ticket.getDescription()
        );
    }

    public Ticket toEntity(TicketDTO ticketDTO) {
        Comment comment = commentService.findById(ticketDTO.getCommentId());
        Topic topic = topicService.findById(ticketDTO.getTopicId());
        User user = userService.findByLogin(ticketDTO.getUsername());

        return new Ticket(
                ticketDTO.getId(),
                ticketDTO.getDescription(),
                convertStatusValue(ticketDTO.getStatus()),
                convertPriorityValue(ticketDTO.getPriority()),
                ticketDTO.getCreatedAt(),
                ticketDTO.getClosedAt(),
                comment,
                topic,
                user
        );
    }

    public Status convertStatusValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Pendente" -> Status.PENDENTE;
            case "Em andamento" -> Status.EM_ANDAMENTO;
            case "Concluído" -> Status.CONCLUIDO;
            case "Cancelado" -> Status.CANCELADO;
            default -> throw new IllegalArgumentException("Status inválido: " + value);
        };
    }

    public Priority convertPriorityValue(String value) {
        if (value == null) {
            return null;
        }
        return switch (value) {
            case "Baixa" -> Priority.BAIXA;
            case "Média" -> Priority.MEDIA;
            case "Alta" -> Priority.ALTA;
            default -> throw new IllegalArgumentException("Prioridade inválida: " + value);
        };
    }
}
