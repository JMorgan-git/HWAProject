DROP TABLE IF EXISTS "toDoEntry" CASCADE;


CREATE TABLE IF NOT EXISTS "toDoEntry"(
	"id" INT AUTO_INCREMENT,
	"name" VARCHAR(255) NOT NULL,
	"entry" VARCHAR(255) NOT NULL,
	"startTime" long NOT NULL,
	"expectedDuration" long NOT NULL,
	PRIMARY KEY("id")
)