package mx.com.backend.asesores.service;

import java.util.List;

import mx.com.backend.asesores.dto.ApplicationEntry;

public interface ApplicationService {
	
	public void createApplicationItem(ApplicationEntry request);

    public List<ApplicationEntry> getApplicationItems();

}
