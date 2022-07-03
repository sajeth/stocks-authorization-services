package com.saji.stocks.authorization.entity;

import javax.persistence.*;
import java.math.BigInteger;

/**
 * @author saji
 */
@MappedSuperclass
public abstract class BaseEntity {
    @Id
    @Basic(optional = false)
    @Column(name = "ID", nullable = false)
    private BigInteger id;
    @Column(name = "NAME", nullable = false)
    private String name;
    @Column(name = "LOGICAL_DEL_IN")
    private String logicalDeleteIn;

    public String getLogicalDelIn() {
        return logicalDeleteIn;
    }

    public void setLogicalDelIn(final String value) {
        this.logicalDeleteIn = value;
    }


    public BigInteger getId() {
        return id;
    }

    public void setId(final BigInteger id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }
}
