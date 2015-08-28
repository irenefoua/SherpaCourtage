package com.j3a.assurance.managedBean.adminCompagnie;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardColumn;
import org.primefaces.model.DashboardModel;
import org.primefaces.model.DefaultDashboardColumn;
import org.primefaces.model.DefaultDashboardModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import com.j3a.assurance.model.CompagnieAssurance;

import com.j3a.assurance.objetService.ObjectService;

@Component
@Scope("session")
public class ManagedIndexCompagnieSpace implements Serializable {

	/**
	 * 
	 */

	private static final long serialVersionUID = 1L;

	
	
	private DashboardModel model;
	private CompagnieAssurance compagnieAssurance;
	
	@Autowired
    private ObjectService objectService;
    public ObjectService getObjectService() {
		return objectService;
	}

	public void setObjectService(ObjectService objectService) {
		this.objectService = objectService;
	}

	@PostConstruct
    public void init() {
        model = new DefaultDashboardModel();
        DashboardColumn column1 = new DefaultDashboardColumn();
        DashboardColumn column2 = new DefaultDashboardColumn();
        //DashboardColumn column3 = new DefaultDashboardColumn();
         
        column1.addWidget("ctras");
        column1.addWidget("compte");
         
        column2.addWidget("docs");
        column2.addWidget("alerts");
         
        //column3.addWidget("politics");
 
        model.addColumn(column1);
        model.addColumn(column2);
        //model.addColumn(column3);
        setCompagnieAssurance(getObjectService().RecupererCompagnieCourrant());
    }
     
    public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
         
        addMessage(message);
    }
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public DashboardModel getModel() {
        return model;
    }

	
	public CompagnieAssurance getCompagnieAssurance() {
		return compagnieAssurance;
	}

	public void setCompagnieAssurance(CompagnieAssurance compagnieAssurance) {
		this.compagnieAssurance = compagnieAssurance;
	}


	
}
