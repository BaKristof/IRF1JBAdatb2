
create or replace trigger megfigy4 after DELETE  on autobuszjaratok
begin
 insert into naplo(muvelet,idopont,felhasznalo) 
 values('Rekord törlese.', sysdate, user);
end;

create or replace trigger megfigy5 after UPDATE  on autobuszjaratok
begin
 insert into naplo(muvelet,idopont,felhasznalo) 
 values('Rekord modositva.', sysdate, user);
end;

create or replace trigger megfigy6 after INSERT  on autobuszjaratok
begin
 insert into naplo(muvelet,idopont,felhasznalo) 
 values('Rekord feltöltve.', sysdate, user);
end;

create or replace trigger autoinc BEFORE INSERT 
on autobuszjaratok FOR EACH row
DECLARE
 idnumber number;
 CURSOR c1 is Select Max(jarat) into idnumber from autobuszjaratok;
begin
    open c1;
    FETCH c1 into idnuber;
    
    if c1%notfound then
    :new.jarat := 1;
    end if;
    
    if :new.jarat <= idnumber
    then :new.jarat := idnumber+1;
    end if;

end;


Nem tudom melyik lenne jó szoval itt van mind a 2 "autoincriment"

CREATE SEQUENCE autob_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE OR REPLACE TRIGGER autobinc2
  BEFORE INSERT ON autobuszjaratok
  FOR EACH ROW
BEGIN
  SELECT autob_seq.nextval
  INTO :new.jarat
  FROM dual;
END;