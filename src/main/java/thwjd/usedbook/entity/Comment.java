package thwjd.usedbook.entity;

import lombok.Data;

@Data
public class Comment {
    private Long id;
    private Long bookPostId;
    private Integer retype=0; //댓글의 깊이 (모댓글이면 0, 답글이면 1)

    private String writer;
    private String content;
    private String createTime;

}
