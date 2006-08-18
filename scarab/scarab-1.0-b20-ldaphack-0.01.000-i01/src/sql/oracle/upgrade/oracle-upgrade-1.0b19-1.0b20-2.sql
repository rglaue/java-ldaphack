/*
 * b19->b20 Migration:
 *
 * Create the new table that defines the transitions between diferent values of a
 * dropdown list attribute.
 * Create the table that holds Conditions based on attribute_options.
 *
 */
CREATE TABLE SCARAB_TRANSITION
(
    TRANSITION_ID NUMBER NOT NULL,
    ROLE_ID NUMBER,
    -- REFERENCES SCARAB_ATTRIBUTE (ATTRIBUTE_ID)
    ATTRIBUTE_ID NUMBER NOT NULL,
    -- REFERENCES SCARAB_ATTRIBUTE_OPTION (OPTION_ID)
    FROM_OPTION_ID NUMBER,
    -- REFERENCES SCARAB_ATTRIBUTE_OPTION (OPTION_ID)
    TO_OPTION_ID NUMBER,
    DISABLED_IF_BLOCKED NUMBER (1) default 0,
    PRIMARY KEY (TRANSITION_ID)
);

ALTER TABLE SCARAB_TRANSITION
    ADD CONSTRAINT SCARAB_TRANSITION_FK_1 FOREIGN KEY (ATTRIBUTE_ID)
    REFERENCES SCARAB_ATTRIBUTE (ATTRIBUTE_ID)
;
ALTER TABLE SCARAB_TRANSITION
    ADD CONSTRAINT SCARAB_TRANSITION_FK_2 FOREIGN KEY (FROM_OPTION_ID)
    REFERENCES SCARAB_ATTRIBUTE_OPTION (OPTION_ID)
;
ALTER TABLE SCARAB_TRANSITION
    ADD CONSTRAINT SCARAB_TRANSITION_FK_3 FOREIGN KEY (TO_OPTION_ID)
    REFERENCES SCARAB_ATTRIBUTE_OPTION (OPTION_ID)
;


CREATE TABLE SCARAB_CONDITION
(
    CONDITION_ID NUMBER (20, 0) NOT NULL,
    --REFERENCES SCARAB_TRANSITION (TRANSITION_ID)
    TRANSITION_ID NUMBER,
    --REFERENCES SCARAB_R_MODULE_ATTRIBUTE(MODULE_ID,ATTRIBUTE_ID,ISSUE_TYPE_ID)
    MODULE_ID NUMBER,
    ISSUE_TYPE_ID NUMBER,
    ATTRIBUTE_ID NUMBER,
    OPTION_ID NUMBER NOT NULL,
    PRIMARY KEY (CONDITION_ID)
);

ALTER TABLE SCARAB_CONDITION
    ADD CONSTRAINT SCARAB_CONDITION_FK_1 FOREIGN KEY (MODULE_ID, ATTRIBUTE_ID, ISSUE_TYPE_ID)
    REFERENCES SCARAB_R_MODULE_ATTRIBUTE (MODULE_ID, ATTRIBUTE_ID, ISSUE_TYPE_ID)
;
ALTER TABLE SCARAB_CONDITION
    ADD CONSTRAINT SCARAB_CONDITION_FK_2 FOREIGN KEY (TRANSITION_ID)
    REFERENCES SCARAB_TRANSITION (TRANSITION_ID)
;
ALTER TABLE SCARAB_CONDITION
    ADD CONSTRAINT SCARAB_CONDITION_FK_3 FOREIGN KEY (ATTRIBUTE_ID)
    REFERENCES SCARAB_ATTRIBUTE (ATTRIBUTE_ID)
;
ALTER TABLE SCARAB_CONDITION
    ADD CONSTRAINT SCARAB_CONDITION_FK_4 FOREIGN KEY (OPTION_ID)
    REFERENCES SCARAB_ATTRIBUTE_OPTION (OPTION_ID)
;
ALTER TABLE SCARAB_CONDITION
    ADD CONSTRAINT SCARAB_CONDITION_FK_5 FOREIGN KEY (MODULE_ID, ISSUE_TYPE_ID)
    REFERENCES SCARAB_R_MODULE_ISSUE_TYPE (MODULE_ID, ISSUE_TYPE_ID)
;