package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "SHOP")
@NamedQueries({
        @NamedQuery(name = "Shop.findAll", query = "SELECT s FROM Shop s"),
        @NamedQuery(name = "Shop.findById", query = "SELECT s FROM Shop s WHERE s.id = :id"),
        @NamedQuery(name = "Shop.findByName", query = "SELECT s FROM Shop s WHERE s.name = :name")
})
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(of = {"id", "name"})
public class Shop implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(max = 50)
    @Column(name = "NAME")
    private String name;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @JoinTable(name = "SHOPITEM", joinColumns = {
            @JoinColumn(name = "SHOP_ID", referencedColumnName = "ID")}, inverseJoinColumns = {
            @JoinColumn(name = "ITEM_ID", referencedColumnName = "ID")})
    @ManyToMany
    @JohnzonIgnore
    private List<Item> itemList = new ArrayList<>();

    @OneToMany(mappedBy = "shop")
    private List<Courier> courierList = new ArrayList<>();
}

