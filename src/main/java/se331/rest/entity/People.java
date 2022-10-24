package se331.rest.entity;

import lombok.*;
import se331.rest.security.entity.AuthorityName;
import se331.rest.security.entity.User;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Builder
@Entity
@NoArgsConstructor
@AllArgsConstructor
public class People {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Exclude
    Long id;
    String name;
    String surname;
    String image;
    Integer age;
    String gender;
    String hometown;
    String status;
    @OneToMany
    @Builder.Default
    List<Comment> commentList= new ArrayList<>();

    @OneToMany
    @Builder.Default
    List<Vaccine> vaccineList = new ArrayList<>();

    @OneToOne
    User user;


}
