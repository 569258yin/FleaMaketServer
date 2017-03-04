package com.aygxy.fmaket.net.procatal;


import java.io.Serializable;

/**
 * body明文中的内容
 *
 * @author yh
 */
public class Body implements Serializable {

    private Oelement oelement;
    private String elements;
    private String token;

    public Oelement getOelement() {
        return oelement;
    }

    public void setOelement(Oelement oelement) {
        this.oelement = oelement;
    }

    public String getElements() {
        return elements;
    }

    public void setElements(String elements) {
        this.elements = elements;
    }
    public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}

	@Override
    public String toString() {
        return "Body{" +
                "oelement=" + oelement +
                ", elements='" + elements + '\'' +
                '}';
    }
}
