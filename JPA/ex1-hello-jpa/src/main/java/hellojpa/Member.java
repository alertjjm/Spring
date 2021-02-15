package hellojpa;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Member {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false)
    private String name;
    private int age;
    @Enumerated(EnumType.STRING)
    private RoleType roleType;
    private LocalDateTime dateTime;
    @Lob
    private String description;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
