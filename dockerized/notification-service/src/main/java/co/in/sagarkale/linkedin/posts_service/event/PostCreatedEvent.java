package co.in.sagarkale.linkedin.posts_service.event;

import lombok.Data;

@Data
public class PostCreatedEvent {
    Long creatorId;
    Long postId;
    String content;
}
