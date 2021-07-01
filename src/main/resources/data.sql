insert into roles (id, created_at, deleted_at, updated_at, name) values (1, '2019-04-06 20:11:24', null, '2019-04-06 20:11:36', 'ROLE_TEACHER');
insert into roles (id, created_at, deleted_at, updated_at, name) values (2, '2019-04-06 20:11:42', null, '2019-04-06 20:11:44', 'ROLE_STUDENT');

INSERT INTO group_type (id, created_at, deleted_at, updated_at, name) VALUES (1, '2019-10-13 09:19:30', null, '2019-10-13 09:18:35', 'Filial');
INSERT INTO group_type (id, created_at, deleted_at, updated_at, name) VALUES (2, '2019-10-13 09:19:30', null, '2019-10-13 09:20:25', 'Department');
INSERT INTO group_type (id, created_at, deleted_at, updated_at, name) VALUES (3, '2019-10-13 09:19:44', null, '2019-10-13 09:19:56', 'Division');

INSERT INTO ume.umembers (membertype, id, created_at, deleted_at, updated_at, parent_id, firstname, lastname, middlename, phone, photopath, group_name, grouptype_id) VALUES ('Profile', 1, '2019-04-18 19:59:59', null, '2019-04-18 19:59:59', null, 'Admin', 'Adminov', 'Adminovic', '87777000000', null, null, null);
INSERT INTO ume.umembers (membertype, id, created_at, deleted_at, updated_at, parent_id, firstname, lastname, middlename, phone, photopath, group_name, grouptype_id) VALUES ('Group', 2, '2019-10-13 09:21:43', null, '2019-10-13 09:21:48', null, null, null, null, null, null, 'Education', 2);
INSERT INTO ume.umembers (membertype, id, created_at, deleted_at, updated_at, parent_id, firstname, lastname, middlename, phone, photopath, group_name, grouptype_id) VALUES ('Group', 3, '2019-10-13 09:24:03', null, '2019-10-13 09:24:07', null, null, null, null, null, null, 'Finance', 2);
INSERT INTO ume.umembers (membertype, id, created_at, deleted_at, updated_at, parent_id, firstname, lastname, middlename, phone, photopath, group_name, grouptype_id) VALUES ('Group', 4, '2019-10-13 09:23:37', null, '2019-10-13 09:23:42', 2, null, null, null, null, null, 'Digital technology', 3);

insert into users (id, created_at, deleted_at, updated_at, email, login, password, profile_id) values (1, '2019-04-18 19:59:59', null, '2019-04-18 19:59:59', 'admin@u.me', 'admin', '$2a$10$H2PqH5KUkeooBkWeLkKVzOwCcX.6F6Z/HBy4f//rg9/GHiBR8493u', 1);
insert into users_roless (user_id, role_id) values (1, 1);
insert into users_roless (user_id, role_id) values (1, 2);