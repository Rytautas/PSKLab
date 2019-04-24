/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
@Table(name = "ITEM")
@NamedQueries({
        @NamedQuery(name = "Item.findAll", query = "SELECT i FROM Item i"),
        @NamedQuery(name = "Item.findById", query = "SELECT i FROM Item i WHERE i.id = :id"),
        @NamedQuery(name = "Item.findByName", query = "SELECT i from Item i WHERE i.name = :name")
})
@Getter
@Setter
@EqualsAndHashCode(of = "ID")
@ToString(of = {"id", "name", "price"})
public class Item implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Integer id;

    @Size(min = 3, max = 30)
    @Column(name = "NAME")
    private String name;

    @Column(name = "PRICE")
    private Float price;

    @Version
    @Column(name = "OPT_LOCK_VERSION")
    private Integer optLockVersion;

    @ManyToMany(mappedBy = "itemList")
    private List<Shop> shopList = new ArrayList<>();
}
