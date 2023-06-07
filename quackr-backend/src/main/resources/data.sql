INSERT INTO users (id, username, password, display_name, role) VALUES ( 1, 'nguyennh', 'nguyennh', 'Nguyen Hang Nguyen', 1 );

INSERT INTO posts (id, content, published_on, published_by) VALUES ( 1, 'Post Content 1', '2023-06-04 09:40:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 2, 'Post Content 2', '2023-06-04 09:42:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 3, 'Post Content 3', '2023-06-04 09:43:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 4, 'Post Content 4', '2023-06-04 09:34:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 5, 'Post Content 5', '2023-06-04 09:45:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 6, 'Post Content 6', '2023-06-04 09:36:48.021', 1 );

alter sequence users_seq restart with 2;
alter sequence posts_seq restart with 7;
