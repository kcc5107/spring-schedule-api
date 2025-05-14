CREATE TABLE schedule
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    toDo      TEXT        NOT NULL COMMENT '할 일',
    userName  VARCHAR(20) NOT NULL COMMENT '작성자명',
    password  VARCHAR(20) NOT NULL COMMENT '비밀번호',
    createdAt DATETIME COMMENT '작성일',
    updatedAt DATETIME COMMENT '수정일'
);

CREATE TABLE schedule2
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '일정 식별자',
    toDo      TEXT        NOT NULL COMMENT '할 일',
    password  VARCHAR(20) NOT NULL COMMENT '비밀번호',
    createdAt DATETIME COMMENT '작성일',
    updatedAt DATETIME COMMENT '수정일',
    authorId  BIGINT      NOT NULL,
    FOREIGN KEY (authorId) REFERENCES author (id) ON DELETE CASCADE
);

CREATE TABLE author
(
    id        BIGINT AUTO_INCREMENT PRIMARY KEY COMMENT '작성자 식별자',
    name      VARCHAR(20) NOT NULL COMMENT '이름',
    email     VARCHAR(30) NOT NULL UNIQUE COMMENT '이메일',
    createdAt DATETIME COMMENT '등록일',
    updatedAt DATETIME COMMENT '수정일'
);