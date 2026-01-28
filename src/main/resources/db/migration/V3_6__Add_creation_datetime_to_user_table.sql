ALTER TABLE "user" ADD COLUMN creation_datetime timestamp with time zone NOT NULL DEFAULT now();
