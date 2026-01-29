ALTER TABLE "user" ADD COLUMN IF NOT EXISTS creation_datetime timestamp with time zone NOT NULL DEFAULT now();
