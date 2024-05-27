package ua.com.kisit2024.course_coffeemachine_2024_372.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor

@Entity
@Table(name = "users")
public class Users {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 2, max = 50, message = "Значення логіна має знаходитись в діапазоні від 2 до 50 символів")
    private String username;
    @Size(min = 4, message = "Пароль має бути більше 4 символів")
    private String password;

    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String payment;

    @ManyToMany(fetch = FetchType.EAGER)
    private Set<Roles> rolesset;

    @OneToMany(mappedBy = "user")
    private List<Order> orderList;

}
