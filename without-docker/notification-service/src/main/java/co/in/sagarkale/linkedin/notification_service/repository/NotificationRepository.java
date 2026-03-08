package co.in.sagarkale.linkedin.notification_service.repository;

import co.in.sagarkale.linkedin.notification_service.entity.Notification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NotificationRepository extends JpaRepository<Notification, Long> {
}