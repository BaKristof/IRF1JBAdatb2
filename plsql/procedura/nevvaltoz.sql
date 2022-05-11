create or replace PROCEDURE nevvaltoz ( eazon2 in number, ujnev in CHAR) is

begin
update edzo set nev=ujnev where eazon= eazon2;
end;