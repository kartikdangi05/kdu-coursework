package com.assessment.two.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.aspectj.weaver.ast.Or;

import java.util.List;

@Entity
@Table(name = "users")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    private String password;
    private String email;

    @ElementCollection(targetClass = Address.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "address_users", joinColumns = @JoinColumn(name = "user_id"))
    private List<Address> address;

    @OneToOne
    @JoinColumn(name = "cart")
    private ShoppingCart cart;

    @ElementCollection(targetClass = Order.class, fetch = FetchType.EAGER)
    @CollectionTable(name = "order_users", joinColumns = @JoinColumn(name = "user_id"))
    private List<Order> orders;

}
