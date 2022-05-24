create or replace trigger megfigy1 after INSERT on utasok
begin
 insert into naplo(muvelet,idopont,felhasznalo) 
 values('Rekord felvéve.', sysdate, user);
end;

create or replace trigger megfigy2 after UPDATE  on utasok
begin
 insert into naplo(muvelet,idopont,felhasznalo) 
 values('Rekord modositas.', sysdate, user);
end;

create or replace trigger megfigy3 after DELETE  on utasok
begin
 insert into naplo(muvelet,idopont,felhasznalo) 
 values('Rekord törlese.', sysdate, user);
end;

create or replace trigger utasinc BEFORE INSERT 
on utasok FOR EACH row
DECLARE
 idnumber number;
 CURSOR c1 is Select Max(utas) into idnumber from utasok;
begin
    open c1;
    FETCH c1 into idnuber;
    
    if c1%notfound then
    :new.utas := 1;
    end if;
    
    if :new.utas <= idnumber
    then :new.utas := idnumber+1
    end if;

end;

Nem tudom melyik lenne jó szoval itt van mind a 2 autoincriment

CREATE SEQUENCE utas_seq
 START WITH     1
 INCREMENT BY   1
 NOCACHE
 NOCYCLE;

CREATE OR REPLACE TRIGGER utasinc2
  BEFORE INSERT ON utasok
  FOR EACH ROW
BEGIN
  SELECT utas_seq.nextval
  INTO :new.utas
  FROM dual;
END;
