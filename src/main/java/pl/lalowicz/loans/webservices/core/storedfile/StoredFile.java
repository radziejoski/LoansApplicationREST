package pl.lalowicz.loans.webservices.core.storedfile;

import pl.lalowicz.loans.webservices.core.customer.Customer;

import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by radoslaw.lalowicz on 2017-05-11.
 */
@Entity
@Table(name = "file")
public class StoredFile implements Serializable {

    private static final long serialVersionUID = -7878410240043293921L;

    public static final String PROPERTY_CUSTOMER = "customer";

    @Id
    @SequenceGenerator(name = "file_id_seq_gen", sequenceName = "file_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "file_id_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "file_name", nullable = false)
    private String fileName;

    @Column(name = "path", nullable = false)
    private String path;

    @ManyToOne(optional = false)
    @JoinColumn(name = "customer_id", foreignKey = @ForeignKey(name = "file_customer_id_fk"))
    private Customer customer;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}
