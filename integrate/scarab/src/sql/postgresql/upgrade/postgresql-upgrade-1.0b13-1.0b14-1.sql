INSERT INTO TURBINE_PERMISSION (PERMISSION_ID, PERMISSION_NAME) 
    VALUES (19, 'Issue | Move');

insert into TURBINE_ROLE_PERMISSION (ROLE_ID, PERMISSION_ID)
       select  TURBINE_ROLE.ROLE_ID, TURBINE_PERMISSION.PERMISSION_ID
         from  TURBINE_ROLE, TURBINE_PERMISSION
         where TURBINE_ROLE.ROLE_NAME in ('Developer', 'QA', 'Project Owner', 'Root')
           and TURBINE_PERMISSION.PERMISSION_NAME in (
                  'Issue | Move');
