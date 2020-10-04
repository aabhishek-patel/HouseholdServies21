package com.formate.householdservies21;

public class DeveloperList {

    private String m_name;
    private String m_mobile;
    private String m_fee;

    public String getM_name() {
        return m_name;
    }

    public String getM_mobile() {
        return m_mobile;
    }

    public String getM_fee() {
        return m_fee;
    }

    public DeveloperList(String m_name, String m_mobile, String m_fee) {
        this.m_name = m_name;
        this.m_mobile = m_mobile;
        this.m_fee = m_fee;
    }

}
