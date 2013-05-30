package com.soebes.casestudy.bo;

import java.util.ArrayList;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = TabellenNamen.CATEGORY)
public class CategoryBO extends AbstractBaseBO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryid", nullable = false, unique = true)
    private Long Id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne( cascade = { CascadeType.ALL } )
    @JoinColumn(name = "parentid")
    private CategoryBO parent;

    @OneToMany(mappedBy = "parent")
    private ArrayList<CategoryBO> subCategories = new ArrayList<CategoryBO>();

    public Long getId() {
	return Id;
    }

    @Override
    public void setId(Long id) {
	Id = id;
    }

    public String getCategoryName() {
	return categoryName;
    }

    public void setCategoryName(String categoryName) {
	this.categoryName = categoryName;
    }

    public CategoryBO getParent() {
	return parent;
    }

    public void setParent(CategoryBO parent) {
	this.parent = parent;
    }

    public ArrayList<CategoryBO> getSubCategories() {
	return subCategories;
    }

    public void setSubCategories(ArrayList<CategoryBO> subCategories) {
	this.subCategories = subCategories;
    }

}
