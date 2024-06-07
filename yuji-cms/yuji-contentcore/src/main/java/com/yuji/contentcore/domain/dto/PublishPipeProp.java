package com.yuji.contentcore.domain.dto;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class PublishPipeProp {

	private String pipeCode;
	
	private String pipeName;
	
	private Map<String, Object> props;
	
	public static PublishPipeProp newInstance(String pipeCode, String pipeName, Map<String, Object> props) {
		PublishPipeProp ppp = new PublishPipeProp();
		ppp.setPipeCode(pipeCode);
		ppp.setPipeName(pipeName);
		ppp.setProps(Objects.requireNonNullElse(props, new HashMap<>()));
		return ppp;
	}

	public String getPipeCode() {
		return pipeCode;
	}

	public void setPipeCode(String pipeCode) {
		this.pipeCode = pipeCode;
	}

	public String getPipeName() {
		return pipeName;
	}

	public void setPipeName(String pipeName) {
		this.pipeName = pipeName;
	}

	public Map<String, Object> getProps() {
		return props;
	}

	public void setProps(Map<String, Object> prop) {
		this.props = prop;
	}
}
