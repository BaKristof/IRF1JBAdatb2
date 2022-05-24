create or replace package utasPackage as 
procedur insertutas (utas in NUMBER,jarat in NUMBER,nev in char,jtipus in char,jegyD in Date );
PROCEDURE utasmod ( utasaz in number, ujnev in CHAR);
PROCEDURE  Deletutas (nev2 in CHAR);
FUNCTION itervalumszam(kis in Date, nagy in Date);
end;

create or replace PACKAGE BODY utasPackage
AS
PROCEDURE  insertutas (utas in NUMBER,jarat in NUMBER,nev in char,jtipus in char,jegyD in Date )is
begin
insert into utasok(utas,jarat,nev,jegytipus,jegydatum) values(utas,jarat,nev,jtipus,jegyD);
  END insertutas;

PROCEDURE utasmod ( utasaz in number, ujnev in CHAR) is

begin
update utasok set nev=ujnev where utas =utasaz;
end utasmod;

PROCEDURE  Deletutas (nev2 in CHAR)is
begin
delete from utasok where nev=nev2;
exception 
when NO_data_Found then
dbms_output.put_line('Nincs ilyen nevü utas');
end Deletutas;

FUNCTION itervalumszam
(kis in Date, nagy in Date)
   RETURN number IS
    szamlalas NUMBER;
BEGIN
   select count(utas) into szamlalas from utasok where jegydatum BETWEEN kis and nagy;

    return szamlalas;

END itervalumszam;
END utasPackage;