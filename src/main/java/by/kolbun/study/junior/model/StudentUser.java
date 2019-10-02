package by.kolbun.study.junior.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "users")
@Data
public class StudentUser {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(unique = true, nullable = false)
	private String username;

	@Column(nullable = false)
	@Setter(value = AccessLevel.PACKAGE)
	private String password;

	@Column(unique = true, nullable = false)
	private String email;

	@Column(name = "last_login")
	private LocalDateTime lastLogin;

	@OneToOne(optional = false, cascade = CascadeType.ALL, orphanRemoval = true)
	@Setter(value = AccessLevel.NONE)
	private StudentProfile profile;
}
