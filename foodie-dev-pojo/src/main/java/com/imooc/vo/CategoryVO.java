package com.imooc.vo;

import java.util.List;

public class CategoryVO {

    private int id;

    private String name;

    private String type;

    private int fatherId;

    private List<SubCategoryVO> subCatList;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getFatherId() {
        return fatherId;
    }

    public void setFatherId(int fatherId) {
        this.fatherId = fatherId;
    }

    public List<SubCategoryVO> getSubCatList() {
        return subCatList;
    }

    public void setSubCatList(List<SubCategoryVO> subCatList) {
        this.subCatList = subCatList;
    }
}
