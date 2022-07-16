CREATE TABLE IF NOT EXISTS meeting_room.usersInMeeting
  (userId smallint NOT NULL,
   meetingId smallint NOT NULL);
  ALTER TABLE usersInMeeting ADD CONSTRAINT fk_usersInMeeting_on_meeting FOREIGN KEY (meetingId) REFERENCES meeting (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;;
  ALTER TABLE usersInMeeting ADD CONSTRAINT fk_usersInMeeting_on_user FOREIGN KEY (userId) REFERENCES users (id)
  MATCH SIMPLE ON UPDATE NO ACTION ON DELETE NO ACTION;;

