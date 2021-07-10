package by.petropavlovskaja.springpharmacy.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "account")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private int id;

    @OneToOne(mappedBy = "account", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Login login;

    @Column
    private String name;

    @Column
    private String surname;

    @Column
    private String patronymic;

    @Column
    private String phone;

    @Column
    private boolean status;

    @Column
    private int balance;

    @Column
    private int credit;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fk_role")
    private AccountRole role;

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", login=" + login +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", patronymic='" + patronymic + '\'' +
                ", phone='" + phone + '\'' +
                ", status=" + status +
                ", balance=" + balance +
                ", credit=" + credit +
                ", role=" + role +
                '}'+ '\n';
    }
}
