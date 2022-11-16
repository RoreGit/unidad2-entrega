CREATE OR REPLACE FUNCTION public.countGames()
RETURNS int
	LANGUAGE plpgsql
AS $$
	declare
	   contador integer;
	BEGIN
		select count(*) into contador from videojuego;
	   return contador;
	END;
$$;