package com.jw.boardservice.file;

import com.jw.boardservice.board.Board;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class FileDto
{
    @Getter
    @AllArgsConstructor
    public static class FileWriteRequestDto
    {
        private String name;
        private String path;
        private Long size;

        public FileEntity toEntity(Board board)
        {
            return FileEntity.builder()
                    .name(name)
                    .board(board)
                    .path(path)
                    .size(size)
                    .build();
        }
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    public static class FileReadResponseDto
    {
        private String name;
        private String path;
        private Long size;
    }
}
