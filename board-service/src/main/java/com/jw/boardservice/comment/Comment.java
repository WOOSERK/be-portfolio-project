package com.jw.boardservice.comment;

import com.jw.boardservice.BaseTimeEntity;
import com.jw.boardservice.board.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment extends BaseTimeEntity
{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parent_id")
    private Comment parent;

    @OneToMany(mappedBy = "parent")
    private List<Comment> children = new ArrayList<>();

    @Builder
    public Comment(String email, String content, Board board, Comment parent)
    {
        this.email = email;
        this.content = content;
        this.board = board;
        this.parent = parent;
    }

    public void update(Comment comment)
    {
        content = comment.content;
    }

    public void mapBoard(Board board)
    {
        this.board = board;
    }
}
