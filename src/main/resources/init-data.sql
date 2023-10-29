INSERT INTO public.permission_groups(group_name)
VALUES ('admin');
INSERT INTO public.permissions(user_email, permission_level, group_id)
VALUES ('admin_view@gmail.com', 'VIEW', 1);
INSERT INTO public.permissions(user_email, permission_level, group_id)
VALUES ('admin_edit@gmail.com', 'EDIT', 1);
