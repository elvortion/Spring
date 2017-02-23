insert into user (user_id, password, name, email) values ('jsb9412', '9412', '정이프', 'sbjung@smilegate.com')
insert into user (user_id, password, name, email) values ('test', '1234', '테스트', 'test@test.com')
insert into user (user_id, password, name, email) values ('test2', '1234', '사용자', 'very@simple.com')

insert into question (id, writer_id, title, contents, time) values (1, 1, '질문합니다', 'Spring이란?', CURRENT_TIMESTAMP())
insert into question (id, writer_id, title, contents, time) values (2, 2, '저도 궁금', 'ORM이 뭔가요?', CURRENT_TIMESTAMP())
insert into question (id, writer_id, title, contents, time) values (3, 3, '질문있는데요', '여기 뭐하는 곳임', CURRENT_TIMESTAMP())