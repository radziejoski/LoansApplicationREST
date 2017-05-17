package pl.lalowicz.loans.webservices.core.customer;

import com.fasterxml.jackson.annotation.JsonIgnore;
import pl.lalowicz.loans.webservices.core.storedfile.StoredFile;

import javax.persistence.*;
import java.io.Serializable;
import java.sql.Date;
import java.util.List;

/**
 * Created by radoslaw.lalowicz on 2017-05-11.
 */
@Entity
@Table(name = "customer")
public class Customer implements Serializable {

    private static final long serialVersionUID = -803622195803016198L;

    public static final String PROPERTY_ID = "id";
    public static final String PROPERTY_LOAN_TYPE = "loanType";
    public static final String PROPERTY_FISCAL_CODE = "fiscalCode";

    @Id
    @SequenceGenerator(name = "customer_id_seq_gen", sequenceName = "customer_id_seq", allocationSize = 1)
    @GeneratedValue(generator = "customer_id_seq_gen", strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false, length = 100)
    private String name;

    @Column(name = "surname", nullable = false, length = 100)
    private String surname;

    @Column(name = "fiscal_code", nullable = false, length = 20)
    private String fiscalCode;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "total_amount", nullable = false)
    private String totalAmount;

    @Column(name = "phone", nullable = false)
    private int phone;

    @Column(name = "rates", nullable = false)
    private int rates;

    @Column(name = "date_of_birth", nullable = false)
    private Date birthDate;

    @Column(name = "loan_type", nullable = false, length = 50)
    @Enumerated(EnumType.STRING)
    private LoanType loanType;

    @JsonIgnore
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL)
    private List<StoredFile> filesList;

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

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getFiscalCode() {
        return fiscalCode;
    }

    public void setFiscalCode(String fiscalCode) {
        this.fiscalCode = fiscalCode;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public int getRates() {
        return rates;
    }

    public void setRates(int rates) {
        this.rates = rates;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public LoanType getLoanType() {
        return loanType;
    }

    public void setLoanType(LoanType loanType) {
        this.loanType = loanType;
    }

    public List<StoredFile> getFilesList() {
        return filesList;
    }

    public void setFilesList(List<StoredFile> filesList) {
        this.filesList = filesList;
    }

    @Override
    public int hashCode() {
        return super.hashCode();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }
}
