package com.valuequo.buckswise.domain;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import org.hibernate.annotations.CacheConcurrencyStrategy;
import javax.persistence.NamedStoredProcedureQueries;
import javax.persistence.NamedStoredProcedureQuery;
import javax.persistence.ParameterMode;
import javax.persistence.StoredProcedureParameter;
import javax.persistence.Table;

import org.hibernate.annotations.Cache;

@Entity
@Table(name = "deleteaccount")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
@NamedStoredProcedureQueries({
	@NamedStoredProcedureQuery(
			name = "userDetails",
			parameters = {
                    @StoredProcedureParameter(mode = ParameterMode.IN, name = "deluid", type = Long.class)
                 },
			procedureName = "deleteUserDetails"
	)
})
public class DeleteAccount implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

}