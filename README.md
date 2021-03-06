# 백엔드 프로젝트입니다.

## 시스템 구조도
![마이크로서비스 설계-3](https://user-images.githubusercontent.com/55542546/172000908-9964f5c6-ef2e-4baf-a41e-5dd1c8154bca.jpg)

## rest api
### user 마이크로서비스(/user-service)

| method  | uri  | description  | return | success | fail |
|:----------|:----------|:----------|:-------|:-----------|:-----------|
| post    | /register    | 회원가입   | String | CREATED |  |
| post    | /login    | 로그인    | | | |
| post    | /logout    | 로그아웃    | | | |
| put    | /update    | 회원정보수정    | Boolean | CREATED | NOT_FOUND  |
| delete | /withdraw    | 회원탈퇴    | Boolean | OK | NOT_FOUND |

### board 마이크로서비스(/board-service)

| method  | uri  | description  | return | success | fail |
|:----------|:-----------------------------|:----------|:--------|:------------|:-----------|
| post    | /write    | 글 작성   | Long | CREATED | |
| put    | /{id}    | 글 수정    | Boolean | CREATED | NOT_FOUND |
| delete | /{id}    | 글 삭제    | Boolean | OK | NOT_FOUND |
| get | /{id} | 글 조회 | Board | OK | NOT_FOUND |
| get | / | 글 목록 조회 | List<Board> | OK | NOT_FOUND |  
| get | /{board_id}/{is_like} | 글 추천/비추천 | String | OK | NOT_MODIFIED |

| method  | uri  | description  | return | success | fail |
|:-----------|:--------------------------------------|:----------------|:--------------|:-------------------|:---------------------|
| post | /{board_id}/comment | 댓글 작성 | String | CREATED | NOT_FOUND |
| post | /{board_id}/{comment_id}/comment | 대댓글 작성 | String | CREATED | NOT_FOUND |  
| put | /{id}/comment | 댓글 수정 | String | CREATED | NOT_MODIFIED |
| delete | /{id}/comment | 댓글 삭제 | String | OK | NOT_FOUND |  
| get | /{board_id}/{comment_id}/{is_like} | 댓글 추천/비추천 | String | OK | NOT_MODIFIED |
  
| method  | uri  | description  | return | success | fail |
|:-----------|:--------------------------------------|:----------------|:--------------|:-------------------|:---------------------|
| post | /{id}/report | 신고하기 | Long | CREATED | NOT_FOUND

