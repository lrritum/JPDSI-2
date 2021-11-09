package com.jsfcourse.calc;

import java.io.Serializable;
import java.util.ResourceBundle;

import javax.annotation.PostConstruct;
import javax.faces.annotation.ManagedProperty;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

@Named
@ViewScoped
public class CalcBB implements Serializable {
	private Double x;
	private Double y;
	private Double result;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalcErr}")
	private ResourceBundle txtCalcErr;

	// Resource injected
	@Inject
	@ManagedProperty("#{txtCalc}")
	private ResourceBundle txtCalc;

	// Resource loaded "manually"
	// (here after initialization in @PostConstruct method)
	// (locale explicitly given - here based on the view )
//	@PostConstruct
//	public void postConstruct() {
//		// loading resource (notice the full "file" name)
//		FacesContext context = FacesContext.getCurrentInstance();
//		txtCalc = ResourceBundle.getBundle("resources.textCalc", context.getViewRoot().getLocale());
//		txtCalcErr = ResourceBundle.getBundle("resources.textCalcErr", context.getViewRoot().getLocale());
//	}
	
	public Double getX() {
		return x;
	}

	public void setX(Double x) {
		this.x = x;
	}

	public Double getY() {
		return y;
	}

	public void setY(Double y) {
		this.y = y;
	}

	public Double getResult() {
		return result;
	}

	public String calc() {
		FacesContext ctx = FacesContext.getCurrentInstance();

		result = x + y;

		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_INFO, txtCalcErr.getString("calcComputationOkInfo"), null));
		ctx.addMessage(null,
				new FacesMessage(FacesMessage.SEVERITY_WARN, txtCalc.getString("result") + ": " + result, null));

		return null;

	}

}
