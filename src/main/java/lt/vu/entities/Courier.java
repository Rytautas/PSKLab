package lt.vu.entities;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lt.vu.usecases.mybatis.model.Item;
import org.apache.johnzon.mapper.JohnzonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "COURIER")
@NamedQueries({
        @NamedQuery(name = "Courier.findAll", query = "SELECT c FROM Courier c"),
        @NamedQuery(name = "Courier.findById", query = "SELECT c FROM Courier c WHERE c.id = :id"),
        @NamedQuery(name = "Courier.findByName", query = "SELECT c FROM Courier c WHERE c.name = :name")
})
@Getter
@Setter
@EqualsAndHashCode(of = "name")
@ToString(of = {"id", "name", "speed"})
public class Courier implements Serializable{

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(min = 3, max = 30)
    @Column(name = "NAME")
    private String name;

    @Column(name = "SPEED")
    private Integer speed;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @JoinColumn(name = "SHOP_ID", referencedColumnName = "ID")
    @ManyToOne
    @JohnzonIgnore
    private Shop shop;
}
