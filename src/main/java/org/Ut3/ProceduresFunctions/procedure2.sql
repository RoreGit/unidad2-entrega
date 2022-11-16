create or replace procedure public.deleteGamesById(n int)
language plpgsql    
as $$
    begin
        delete from videojuego where id=n;
    end;$$;