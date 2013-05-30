package com.soebes.casestudy.bo;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
@Table(name = TabellenNamen.CATEGORY)
public class CategoryBO extends AbstractBaseBO {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "categoryid", nullable = false, unique = true)
    private Long Id;

    @Column(name = "category_name")
    private String categoryName;

    @ManyToOne(cascade = { CascadeType.ALL })
    @JoinColumn(name = "parentid")
    @NotFound(action = NotFoundAction.IGNORE)
    private CategoryBO parent;

    @OneToMany(mappedBy = "parent", fetch = FetchType.EAGER)
    @NotFound(action = NotFoundAction.IGNORE)
    private List<CategoryBO> subCategories;

    @ManyToMany(cascade = { CascadeType.ALL }, targetEntity = com.soebes.casestudy.bo.EntriesBO.class)
    @ElementCollection
    @JoinTable(name = TabellenNamen.ENTRY_CATEGORY, joinColumns = { @JoinColumn(name = "categoryid") }, inverseJoinColumns = { @JoinColumn(name = "entryid") })
    private List<EntriesBO> entries;

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

    public List<CategoryBO> getSubCategories() {
	return subCategories;
    }

    public void setSubCategories(ArrayList<CategoryBO> subCategories) {
	this.subCategories = subCategories;
    }

    public boolean hasSubCategories() {
	if (getSubCategories() != null && (getSubCategories().size() > 0)) {
	    return true;
	} else {
	    return false;
	}
    }

    public boolean hasParent() {
	if (getParent() == null) {
	    return false;
	} else {
	    return true;
	}
    }

    public List<EntriesBO> getEntries() {
        return entries;
    }

    public void setEntries(List<EntriesBO> entries) {
        this.entries = entries;
    }

}
