package com.soebes.casestudy.bo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = TabellenNamen.CATEGORY)
public class CategoryBO extends AbstractBaseBO {
    private Long Id;

    private String categoryName;
    
    private Long parentId;

    
    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column(name="categoryid", nullable=false, unique=true)
    @Override
    public Long getId() {
        return Id;
    }

    @Override
    public void setId(Long id) {
        Id = id;
    }

    @Column(name="category_name")
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Column(name="parentid", nullable=false)
    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    
    
}
