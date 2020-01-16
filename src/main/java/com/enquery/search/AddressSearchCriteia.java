package com.enquery.search;

import com.enquery.model.Address;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AddressSearchCriteia implements Serializable {
    private static final long serialVersionUID = -4698569074197249664L;

    private String city;

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Specification<Address> search(){
        return new Specification<Address>() {
            @Override
            public Predicate toPredicate(Root<Address> root, CriteriaQuery<?> query, CriteriaBuilder cb) {

                List<Predicate> predicates = new ArrayList<>();
                if(!StringUtils.isEmpty(city)){
                    predicates.add(cb.equal(root.get("addressId").get("city"),city));
                }
               return cb.and(predicates.toArray(new Predicate[predicates.size()]));
            }
        };
    }
}