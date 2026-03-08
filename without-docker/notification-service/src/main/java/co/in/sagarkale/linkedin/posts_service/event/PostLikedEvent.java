package co.in.sagarkale.linkedin.posts_service.event;

import lombok.Data;

@Data
public class PostLikedEvent {
    Long postId;
    Long creatorId;
    Long likedByUserId;
}
