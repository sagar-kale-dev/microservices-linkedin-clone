package co.in.sagarkale.linkedin.notification_service.consumer;

import co.in.sagarkale.linkedin.notification_service.advice.ApiResponse;
import co.in.sagarkale.linkedin.notification_service.client.ConnectionsClient;
import co.in.sagarkale.linkedin.notification_service.dto.PersonDto;
import co.in.sagarkale.linkedin.notification_service.service.NotificationService;
import co.in.sagarkale.linkedin.posts_service.event.PostCreatedEvent;
import co.in.sagarkale.linkedin.posts_service.event.PostLikedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostsServiceConsumer {

    private final ConnectionsClient connectionsClient;
    private final NotificationService notificationService;

    @KafkaListener(topics = "post-created-topic")
    public void handlePostCreated(PostCreatedEvent postCreatedEvent){
        ApiResponse<List<PersonDto>> apiResponse = connectionsClient.getAllFirstDegreeConn(postCreatedEvent.getCreatorId());
        List<PersonDto> personDtoList = apiResponse.getData();
        if(personDtoList != null){
            for (PersonDto personDto: personDtoList){
                notificationService.sendNotification(personDto.getUserId(), "Your connection "+postCreatedEvent.getCreatorId()+" has created" +
                        " a post, Check it out");
            }
        }
    }

    @KafkaListener(topics = "post-liked-topic")
    public void handlePostLiked(PostLikedEvent postLikedEvent){
        notificationService.sendNotification(postLikedEvent.getCreatorId(), postLikedEvent.getLikedByUserId()+" has liked your post, Check it out");
    }

}
