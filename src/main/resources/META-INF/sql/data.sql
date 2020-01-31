ALTER TABLE image
ADD CONSTRAINT unique_filename
UNIQUE (filename);