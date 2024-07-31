-- Create the database if it does not exist
CREATE DATABASE IF NOT EXISTS ddcd;

-- Grant all privileges to the user for the database
GRANT ALL PRIVILEGES ON ddcd.* TO 'myuser'@'%';

-- Flush privileges to apply changes
FLUSH PRIVILEGES;
