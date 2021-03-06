/*
 * This script swaps the url data so that the description is stored in NAME and
 * the url is stored in DATA.  Truncates any descriptions that were longer 
 * than 255 characters.
 *
 * Created By: John McNally 
 * $Id: mysql-upgrade-1.0b7-1.0b8-2.sql 9625 2005-04-17 21:57:05Z dabbous $
 */

alter table SCARAB_ATTACHMENT add TMP_NAME VARCHAR (255);

update SCARAB_ATTACHMENT set TMP_NAME=ATTACHMENT_NAME, ATTACHMENT_NAME=ATTACHMENT_DATA, ATTACHMENT_DATA=TMP_NAME where ATTACHMENT_TYPE_ID=3; 

alter table SCARAB_ATTACHMENT drop TMP_NAME;
