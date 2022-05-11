create or replace TRIGGER sazon
    BEFORE INSERT
    ON diak
    FOR EACH ROW
BEGIN
    IF :new.sazon > 5
    THEN
        :new.sazon := 1;
    END IF;
END;