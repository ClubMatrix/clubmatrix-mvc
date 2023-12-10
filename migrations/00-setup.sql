CREATE TABLE "members" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(255) NOT NULL,
  "address" VARCHAR(255) NOT NULL,
  "phone" VARCHAR(20) NOT NULL,
  "email" VARCHAR(255) UNIQUE NOT NULL,
  "user_id" INTEGER NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "credit_card_payments" (
  "id" SERIAL PRIMARY KEY,
  "member_id" INTEGER NOT NULL,
  "card_number" VARCHAR(255) NOT NULL,
  "card_expiry" DATE NOT NULL,
  "card_cvv" VARCHAR(10),
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "bank_transfers" (
  "id" SERIAL PRIMARY KEY,
  "member_id" INTEGER NOT NULL,
  "bank_name" VARCHAR(255) NOT NULL,
  "account_number" VARCHAR(255) NOT NULL,
  "routing_number" VARCHAR(255),
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "employees" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(255) NOT NULL,
  "address" VARCHAR(255) NOT NULL,
  "phone" VARCHAR(20) NOT NULL,
  "email" VARCHAR(255) UNIQUE NOT NULL,
  "position" INTEGER NOT NULL,
  "salary" DECIMAL(10,2) NOT NULL,
  "user_id" INTEGER NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "users" (
  "id" SERIAL PRIMARY KEY,
  "username" VARCHAR(255) UNIQUE NOT NULL,
  "password" VARCHAR(255) NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "groups" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(255) UNIQUE NOT NULL
);

CREATE TABLE "user_group_memberships" (
  "user_id" INTEGER,
  "group_id" INTEGER,
  PRIMARY KEY ("user_id", "group_id")
);

CREATE TABLE "activities" (
  "id" SERIAL PRIMARY KEY,
  "name" VARCHAR(255) NOT NULL,
  "description" TEXT NOT NULL,
  "age_group" INTEGER NOT NULL,
  "skill_level" INTEGER NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "classes" (
  "id" SERIAL PRIMARY KEY,
  "activity_id" INTEGER,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "class_schedules" (
  "id" SERIAL PRIMARY KEY,
  "start_date" DATE NOT NULL,
  "end_date" DATE NOT NULL,
  "class_id" INTEGER NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "enrollments" (
  "id" SERIAL PRIMARY KEY,
  "member_id" INTEGER,
  "class_id" INTEGER,
  "attendance_count" INTEGER DEFAULT 0,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "employment_history" (
  "id" SERIAL PRIMARY KEY,
  "employee_id" INTEGER,
  "company_name" VARCHAR(255),
  "position" VARCHAR(255),
  "start_date" DATE,
  "end_date" DATE,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "trainings" (
  "id" SERIAL PRIMARY KEY,
  "employee_id" INTEGER,
  "name" VARCHAR(255),
  "completion_date" DATE,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "feedbacks" (
  "id" SERIAL PRIMARY KEY,
  "feedback" TEXT,
  "given_to_id" INTEGER,
  "given_by_id" INTEGER,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "dependents" (
  "id" SERIAL PRIMARY KEY,
  "member_id" INTEGER NOT NULL,
  "name" VARCHAR(255) NOT NULL,
  "relationship" VARCHAR(100),
  "date_of_birth" DATE,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE TABLE "notifications" (
  "id" SERIAL PRIMARY KEY,
  "user_id" INTEGER,
  "title" VARCHAR(255) NOT NULL,
  "message" TEXT NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP),
  "read" BOOLEAN DEFAULT false
);

CREATE TABLE "orders" (
  "id" SERIAL PRIMARY KEY,
  "member_id" INTEGER NOT NULL,
  "employee_id" INTEGER NOT NULL,
  "amount" INTEGER NOT NULL,
  "created_at" TIMESTAMP DEFAULT (CURRENT_TIMESTAMP)
);

CREATE INDEX "idx_members_email" ON "members" ("email");

CREATE INDEX "idx_member_users_id" ON "members" ("user_id");

CREATE INDEX "credit_card_payments" ON "credit_card_payments" ("member_id");

CREATE INDEX "bank_transfers" ON "bank_transfers" ("member_id");

CREATE INDEX "idx_employees_email" ON "employees" ("email");

CREATE INDEX "idx_employee_users_id" ON "employees" ("user_id");

CREATE INDEX "idx_users_username" ON "users" ("username");

CREATE INDEX "idx_class_schedules_class_id" ON "class_schedules" ("class_id");

CREATE INDEX "idx_enrollments_member_id" ON "enrollments" ("member_id");

CREATE INDEX "idx_enrollments_class_id" ON "enrollments" ("class_id");

CREATE INDEX "idx_orders_member_id" ON "orders" ("member_id");

CREATE INDEX "idx_orders_employee_id" ON "orders" ("employee_id");

ALTER TABLE "members" ADD CONSTRAINT "members_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE;

ALTER TABLE "employees" ADD CONSTRAINT "employees_user_id_fkey" FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE;

ALTER TABLE "class_schedules" ADD CONSTRAINT "class_schedules_class_id_fkey" FOREIGN KEY ("class_id") REFERENCES "classes" ("id") ON DELETE CASCADE;

ALTER TABLE "credit_card_payments" ADD FOREIGN KEY ("member_id") REFERENCES "members" ("id") ON DELETE CASCADE;

ALTER TABLE "bank_transfers" ADD FOREIGN KEY ("member_id") REFERENCES "members" ("id") ON DELETE CASCADE;

ALTER TABLE "dependents" ADD FOREIGN KEY ("member_id") REFERENCES "members" ("id") ON DELETE CASCADE;

ALTER TABLE "notifications" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE;

ALTER TABLE "user_group_memberships" ADD FOREIGN KEY ("user_id") REFERENCES "users" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "user_group_memberships" ADD FOREIGN KEY ("group_id") REFERENCES "groups" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "classes" ADD FOREIGN KEY ("activity_id") REFERENCES "activities" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "enrollments" ADD FOREIGN KEY ("member_id") REFERENCES "members" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "enrollments" ADD FOREIGN KEY ("class_id") REFERENCES "classes" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "employment_history" ADD FOREIGN KEY ("employee_id") REFERENCES "employees" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "trainings" ADD FOREIGN KEY ("employee_id") REFERENCES "employees" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "feedbacks" ADD FOREIGN KEY ("given_to_id") REFERENCES "employees" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "feedbacks" ADD FOREIGN KEY ("given_by_id") REFERENCES "employees" ("id") ON DELETE SET NULL ON UPDATE CASCADE;

ALTER TABLE "orders" ADD FOREIGN KEY ("member_id") REFERENCES "members" ("id") ON DELETE CASCADE ON UPDATE CASCADE;

ALTER TABLE "orders" ADD FOREIGN KEY ("employee_id") REFERENCES "employees" ("id") ON DELETE CASCADE ON UPDATE CASCADE;
