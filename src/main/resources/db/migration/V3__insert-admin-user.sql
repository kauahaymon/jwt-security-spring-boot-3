insert into tb_user
(
  account_locked,
  created_date,
  email,
  enabled,
  first_name,
  last_modified_date,
  last_name,
  password
)
values
(
  false,
  CURRENT_TIMESTAMP,
  'admin@gmail.com',
  true,
  'admin',
  CURRENT_TIMESTAMP,
  '1',
  '$2a$10$1H4OXJCbC5ubygRLoAnfuuS0yc/gV4kx9fx33hdn5K49tITadyPaa'
);

insert into tb_user_roles (users_id, roles_id)
values (1, 2);