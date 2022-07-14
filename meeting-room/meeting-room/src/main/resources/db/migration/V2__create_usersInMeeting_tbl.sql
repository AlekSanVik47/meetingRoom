CREATE TABLE IF NOT EXISTS meeting_room.usersInMeeting
  (userId smallint NOT NULL,
   meetingId smallint NOT NULL);
  ALTER TABLE usersInMeeting ADD CONSTRAINT fk_user_meeting_on_meeting FOREIGN KEY (meetingId) REFERENCES meeting (id);
  ALTER TABLE usersInMeeting ADD CONSTRAINT fk_user_meeting_on_user FOREIGN KEY (userId) REFERENCES "user" (id);