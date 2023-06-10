-- insert admin account
INSERT INTO users (id, username, password, display_name, role) VALUES ( 1, 'admin', 'admin', 'Admin 1', 0 );

-- insert normal user accounts
INSERT INTO users (id, username, password, display_name, role) VALUES ( 2, 'user1', 'password', 'User 1', 1 );
INSERT INTO users (id, username, password, display_name, role) VALUES ( 3, 'user2', 'password', 'User 2', 1 );

-- insert posts
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 4, 'Post Content 1', '2023-06-04 09:40:48.021', 2 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 5, 'Post Content 2', '2023-06-04 09:42:48.021', 2 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 6, 'Post Content 3', '2023-06-04 09:43:48.021', 2 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 7, 'Post Content 4', '2023-06-04 09:34:48.021', 2 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 8, 'Post Content 5', '2023-06-04 09:45:48.021', 2 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 9, 'Post Content 6', '2023-06-04 09:36:48.021', 2 );

INSERT INTO posts (id, content, published_on, published_by) VALUES ( 10, 'Post Content 7', '2023-06-04 09:40:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 11, 'Post Content 8', '2023-06-04 09:42:48.021', 1 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 12, 'Post Content 9', '2023-06-04 09:43:48.021', 1 );

INSERT INTO posts (id, content, published_on, published_by) VALUES ( 13, 'Post Content 10', '2023-06-04 09:34:48.021', 3 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 14, 'Post Content 11', '2023-06-04 09:45:48.021', 3 );
INSERT INTO posts (id, content, published_on, published_by) VALUES ( 15, 'Post Content 12', '2023-06-04 09:36:48.021', 3 );

-- restart id_sequence
alter sequence id_sequence restart with 16;
