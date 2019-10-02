package by.kolbun.study.junior.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "profiles")
@Data
public class StudentProfile {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(nullable = false)
	private String name;

	private LocalDate birth;

	private String education;

	@Column(name = "github_account")
	private String githubAccount;

	@ManyToOne(targetEntity = Group.class)
	private Group group;

	@OneToOne(mappedBy = "profile")
	@Setter(AccessLevel.NONE)
	private StudentUser user;

	@Enumerated(EnumType.STRING)
	@Setter(AccessLevel.PACKAGE)
	private StudentStatus status;
}
