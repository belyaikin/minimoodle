package me.belyaikin.minimoodle.model;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
@Entity
@Table(name="users")
public class User {
    @Id
    private long telegramId;
    private String moodleKey;
}
