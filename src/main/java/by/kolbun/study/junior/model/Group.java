package by.kolbun.study.junior.model;

import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "groups_table")
@Data
public class Group {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	@Column(name = "bh_name")
	private String bhName;

	private LocalDate startDate;

	private LocalDate finishDate;

	@OneToMany(fetch = FetchType.EAGER, mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<StudentProfile> students;

}
