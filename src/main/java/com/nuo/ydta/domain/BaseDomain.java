package com.nuo.ydta.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
@Data
public class BaseDomain implements Serializable {
    @CreatedDate
    private Date createdTime;
    @LastModifiedDate
    private Date updatedTime;

    @Column(nullable = false,length = 5,name = "version")
    private String version = "v1";


}
