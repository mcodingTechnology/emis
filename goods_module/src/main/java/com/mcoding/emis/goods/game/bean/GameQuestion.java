package com.mcoding.emis.goods.game.bean;

import java.io.Serializable;
import java.util.Date;

public class GameQuestion implements Serializable{
	private static final long serialVersionUID = 1L;
    private Integer id;

    private String questiontype;

    private String questiontitle;

    private String option1;

    private String option2;

    private String option3;

    private String option4;

    private String option5;

    private String answer;

    private String ext;

    private String brandcode;

    private Date createtime;

    private Date updatetime;

    private String gamename;

    private Integer gameid;

    private String rightreply;

    private String wrongreply;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getQuestiontype() {
        return questiontype;
    }

    public void setQuestiontype(String questiontype) {
        this.questiontype = questiontype == null ? null : questiontype.trim();
    }

    public String getQuestiontitle() {
        return questiontitle;
    }

    public void setQuestiontitle(String questiontitle) {
        this.questiontitle = questiontitle == null ? null : questiontitle.trim();
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1 == null ? null : option1.trim();
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2 == null ? null : option2.trim();
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3 == null ? null : option3.trim();
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4 == null ? null : option4.trim();
    }

    public String getOption5() {
        return option5;
    }

    public void setOption5(String option5) {
        this.option5 = option5 == null ? null : option5.trim();
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer == null ? null : answer.trim();
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext == null ? null : ext.trim();
    }

    public String getBrandcode() {
        return brandcode;
    }

    public void setBrandcode(String brandcode) {
        this.brandcode = brandcode == null ? null : brandcode.trim();
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public Date getUpdatetime() {
        return updatetime;
    }

    public void setUpdatetime(Date updatetime) {
        this.updatetime = updatetime;
    }

    public String getGamename() {
        return gamename;
    }

    public void setGamename(String gamename) {
        this.gamename = gamename == null ? null : gamename.trim();
    }

    public Integer getGameid() {
        return gameid;
    }

    public void setGameid(Integer gameid) {
        this.gameid = gameid;
    }

    public String getRightreply() {
        return rightreply;
    }

    public void setRightreply(String rightreply) {
        this.rightreply = rightreply == null ? null : rightreply.trim();
    }

    public String getWrongreply() {
        return wrongreply;
    }

    public void setWrongreply(String wrongreply) {
        this.wrongreply = wrongreply == null ? null : wrongreply.trim();
    }
}