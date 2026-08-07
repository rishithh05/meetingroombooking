CREATE TABLE meeting_rooms (

    id BIGINT AUTO_INCREMENT PRIMARY KEY,

    subject VARCHAR(255),

    organizer VARCHAR(255),

    start_time TIMESTAMP,

    end_time TIMESTAMP

);