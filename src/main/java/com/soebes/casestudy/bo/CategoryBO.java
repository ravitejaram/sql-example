package com.soebes.casestudy.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = TabellenNamen.CATEGORY)
public class CategoryBO extends BaseBO {

    @Column(name="category_name")
    private String categoryName;
    
}
