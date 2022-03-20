ALTER TABLE "Projects" ADD FOREIGN KEY ("client_id") REFERENCES "Clients" ("id");

ALTER TABLE "Projects" ADD FOREIGN KEY ("team_id") REFERENCES "Teams" ("id");

ALTER TABLE "Teams" ADD FOREIGN KEY ("leader_id") REFERENCES "Users" ("id");

ALTER TABLE "TeamPeople" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("id");

ALTER TABLE "TeamPeople" ADD FOREIGN KEY ("team_id") REFERENCES "Teams" ("id");

ALTER TABLE "Tasks" ADD FOREIGN KEY ("status_id") REFERENCES "Status" ("id");

ALTER TABLE "Tasks" ADD FOREIGN KEY ("classification_id") REFERENCES "Classifications" ("id");

ALTER TABLE "Tasks" ADD FOREIGN KEY ("priority_id") REFERENCES "Priorities" ("id");

ALTER TABLE "Tasks" ADD FOREIGN KEY ("project_id") REFERENCES "Projects" ("id");

ALTER TABLE "Backlogs" ADD FOREIGN KEY ("task_id") REFERENCES "Tasks" ("id");

ALTER TABLE "Backlogs" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("id");

ALTER TABLE "BacklogNotifications" ADD FOREIGN KEY ("backlog_id") REFERENCES "Backlogs" ("id");

ALTER TABLE "BacklogNotifications" ADD FOREIGN KEY ("notified_user") REFERENCES "Users" ("id");

ALTER TABLE "DetailedHours" ADD FOREIGN KEY ("task_id") REFERENCES "Tasks" ("id");

ALTER TABLE "DetailedHours" ADD FOREIGN KEY ("user_id") REFERENCES "Users" ("id");

ALTER TABLE "Attachment" ADD FOREIGN KEY ("task_id") REFERENCES "Tasks" ("id");