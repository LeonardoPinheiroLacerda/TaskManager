CREATE TABLE "Priorities" (
  "id" SERIAL PRIMARY KEY,
  "priority" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "Status" (
  "id" SERIAL PRIMARY KEY,
  "status" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "Classifications" (
  "id" SERIAL PRIMARY KEY,
  "classification" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "Clients" (
  "id" SERIAL PRIMARY KEY,
  "client" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "Projects" (
  "id" SERIAL PRIMARY KEY,
  "client_id" BIGINT,
  "team_id" BIGINT,
  "project" VARCHAR(50) UNIQUE NOT NULL
);

CREATE TABLE "Users" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(50) NOT NULL,
  "last_name" VARCHAR(50),
  "email" VARCHAR(100) UNIQUE NOT NULL,
  "telephone_number" VARCHAR(50) UNIQUE,
  "username" VARCHAR(50) UNIQUE NOT NULL,
  "password" VARCHAR(255) NOT NULL
);

CREATE TABLE "Teams" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(50) UNIQUE NOT NULL,
  "leader_id" BIGINT NOT NULL
);

CREATE TABLE "TeamPeople" (
  "team_id" INTEGER,
  "user_id" INTEGER,
  PRIMARY KEY ("team_id", "user_id")
);

CREATE TABLE "Tasks" (
  "id" SERIAL PRIMARY KEY,
  "status_id" BIGINT NOT NULL,
  "classification_id" BIGINT NOT NULL,
  "priority_id" BIGINT NOT NULL,
  "project_id" BIGINT NOT NULL,
  "started_at" TIMESTAMP,
  "expected_duration" TIME,
  "concluded_at" TIMESTAMP,
  "title" VARCHAR(100) NOT NULL,
  "description" VARCHAR NOT NULL,
  "requester" VARCHAR(50)
);

CREATE TABLE "Backlogs" (
  "id" SERIAL PRIMARY KEY,
  "note" VARCHAR,
  "created_at" TIMESTAMP,
  "task_id" BIGINT,
  "user_id" BIGINT
);

CREATE TABLE "BacklogNotifications" (
  "id" SERIAL PRIMARY KEY,
  "notified" BOOLEAN,
  "backlog_id" BIGINT,
  "notified_user" BIGINT
);

CREATE TABLE "DetailedHours" (
  "id" SERIAL PRIMARY KEY,
  "description" VARCHAR(100),
  "time" TIME,
  "created_at" TIMESTAMP,
  "task_id" BIGINT,
  "user_id" BIGINT
);

CREATE TABLE "Attachment" (
  "id" SERIAL PRIMARY KEY,
  "task_id" BIGINT,
  "name" VARCHAR(100),
  "path" VARCHAR(255),
  "size" VARCHAR(50),
  "created_at" TIMESTAMP
);