create or replace PROCEDURE  insertdiak (dazon in NUMBER,szulev char,nev in char,neptunkod in char,sazon in NUMBER)is
begin
insert into diak(dazon,szulev,nev,neptunkod,sazon) values(dazon,szulev,nev,neptunkod,sazon);
end;