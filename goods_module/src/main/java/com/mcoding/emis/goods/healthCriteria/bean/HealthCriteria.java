package com.mcoding.emis.goods.healthCriteria.bean;

import java.io.Serializable;

public class HealthCriteria implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
    private Integer id;

    private String testitem;

    private Integer weight;

    private String result;

    private String score;

    private String ext1;

    private String ext2;

    private String analysis;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTestitem() {
        return testitem;
    }

    public void setTestitem(String testitem) {
        this.testitem = testitem == null ? null : testitem.trim();
    }

    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result == null ? null : result.trim();
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
    	this.score = score == null ? null : score.trim();
    }

    public String getExt1() {
        return ext1;
    }

    public void setExt1(String ext1) {
        this.ext1 = ext1 == null ? null : ext1.trim();
    }

    public String getExt2() {
        return ext2;
    }

    public void setExt2(String ext2) {
        this.ext2 = ext2 == null ? null : ext2.trim();
    }

    public String getAnalysis() {
        return analysis;
    }

    public void setAnalysis(String analysis) {
        this.analysis = analysis == null ? null : analysis.trim();
    }
}