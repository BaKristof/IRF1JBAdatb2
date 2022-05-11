create or replace trigger insertindiak after INSERT on diak
begin
 insert into naplo(muvelet,idopont,felhasznalo) values('Rekord felvéve.', sysdate, user);
end;