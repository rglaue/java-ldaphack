/*
 * b19-Workflow Migration
 *
 * Sets the id_table record for scarab_transition.
 *
 */
delete from id_table where table_name = 'SCARAB_TRANSITION';
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (100, 'SCARAB_TRANSITION', 100, 10);
delete from ID_TABLE where table_name = 'SCARAB_CONDITION';
insert into ID_TABLE (id_table_id, table_name, next_id, quantity) VALUES (101, 'SCARAB_CONDITION', 100, 10);
insert into SCARAB_ATTRIBUTE_TYPE(attribute_type_id, attribute_class_id, attribute_type_name, java_class_name) VALUES (13, 1, 'date', 'org.tigris.scarab.attribute.DateAttribute');

