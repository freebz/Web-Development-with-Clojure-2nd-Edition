-- name: create-user!
-- creates a new user record
INSERT INTO users
(id, pass)
VALUES (:id, :pass)

-- name: get-user
-- retrieve a user given the id.
SELECT * FROM users
WHERE id = :id

-- name: delete-user!
-- delete a user given the id
DELETE FROM users
WHERE id = :id

--name: save-file!
-- saves a file to the database
INSERT INTO files
(owner, type, name, data)
VALUES (:owner, :type, :name, :data)

--name: list-thumbnails
-- selects thumbnail names for the given gallery owner
SELECT owner, name FROM files
 WHERE owner = :owner
  AND name LIKE 'thumb\_%'

--name: get-image
-- retrieve image data by name
SELECT type, data FROM files
WHERE name = :name

--name: select-gallery-previews
-- selects a thumbnail for each user gallery
WITH summary AS (
    SELECT f.owner,
           f.name,
           ROW_NUMBER() OVER(PARTITION BY f.owner
                                 ORDER BY f.name DESC) AS rk
      FROM files f WHERE name like 'thumb\_%')
SELECT s.*
  FROM summary s
 WHERE s.rk = 1

--name: delete-file!
-- deletes the file with the given name and owner
DELETE FROM files
WHERE name = :name
AND owner = :owner