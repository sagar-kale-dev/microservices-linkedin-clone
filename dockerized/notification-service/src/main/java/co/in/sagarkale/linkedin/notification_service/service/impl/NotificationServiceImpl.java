package co.in.sagarkale.linkedin.notification_service.service.impl;

import co.in.sagarkale.linkedin.notification_service.entity.Notification;
import co.in.sagarkale.linkedin.notification_service.repository.NotificationRepository;
import co.in.sagarkale.linkedin.notification_service.service.NotificationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class NotificationServiceImpl implements NotificationService {

    private final NotificationRepository notificationRepository;

    @Override
    public void sendNotification(Long userId, String message) {
        Notification notification = new Notification();
        notification.setMessage(message);
        notification.setUserId(userId);

        notificationRepository.save(notification);
        log.info("Notification sent successfully to user id: {} with message: {}",userId, message);
    }
}
