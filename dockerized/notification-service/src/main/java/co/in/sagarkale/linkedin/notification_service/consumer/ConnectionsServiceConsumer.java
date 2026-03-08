package co.in.sagarkale.linkedin.notification_service.consumer;

import co.in.sagarkale.linkedin.connections_service.event.AcceptConnectionRequestEvent;
import co.in.sagarkale.linkedin.connections_service.event.SendConnectionRequestEvent;
import co.in.sagarkale.linkedin.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ConnectionsServiceConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "send-connection-request-topic")
    public void handleSendConnectionRequest(SendConnectionRequestEvent sendConnectionRequestEvent) {
        log.info("handle connections: handleSendConnectionRequest: {}", sendConnectionRequestEvent);
        String message =
                "You have receiver a connection request from user with id: %d"+sendConnectionRequestEvent.getSenderId();
        notificationService.sendNotification(sendConnectionRequestEvent.getReceiverId(), message);
    }

    @KafkaListener(topics = "accept-connection-request-topic")
    public void handleAcceptConnectionRequest(AcceptConnectionRequestEvent acceptConnectionRequestEvent) {
        log.info("handle connections: handleAcceptConnectionRequest: {}", acceptConnectionRequestEvent);
        String message =
                "Your connection request has been accepted by the user with id: %d"+acceptConnectionRequestEvent.getReceiverId();
        notificationService.sendNotification(acceptConnectionRequestEvent.getSenderId(), message);
    }

}

