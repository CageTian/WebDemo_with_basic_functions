package com.relation.scholar.domain;

/**
 * Created by T.Cage on 2017/1/21.
 */
public class Scholar {
    private String advisee;
    private String advisor;
    private String bid;

    public String getBid() {
        return bid;
    }

    public void setBid(String bid) {
        this.bid = bid;
    }

    public String getAdvisee() {
        return advisee;
    }

    public void setAdvisee(String advisee) {
        this.advisee = advisee;
    }

    public String getAdvisor() {
        return advisor;
    }

    public void setAdvisor(String advisor) {
        this.advisor = advisor;
    }

    public int getBeginYear() {
        return beginYear;
    }

    public void setBeginYear(int beginYear) {
        this.beginYear = beginYear;
    }

    private int beginYear;

    @Override
    public String toString() {
        return "Scholar{" +
                "advisee='" + advisee + '\'' +
                ", advisor='" + advisor + '\'' +
                ", beginYear=" + beginYear +
                '}';
    }

}
