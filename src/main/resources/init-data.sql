INSERT INTO file_management.permission_groups(group_name)
VALUES ('admin');
INSERT INTO file_management.permissions(user_email, permission_level, group_id)
VALUES ('admin_view@gmail.com', 'VIEW', 1);
INSERT INTO file_management.permissions(user_email, permission_level, group_id)
VALUES ('admin_edit@gmail.com', 'EDIT', 1);
