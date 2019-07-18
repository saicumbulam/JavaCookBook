package com.example.demo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.Response;

import com.example.demo.model.Patient;

import org.springframework.stereotype.Service;

@Service
public class PatientServiceImpl implements PatientService {
	private Map<Long,Patient> patients = new HashMap();
	private long currentId = 123;
	
	public PatientServiceImpl() {
		Init();
	}
	
	public void Init() {
		Patient patient = new Patient();
		patient.setId(currentId);
		patient.setName("John");
		this.patients.put(patient.getId(), patient);
	}

	@Override
	public List<Patient> getPatients() {
		Collection<Patient> results = patients.values();
		List<Patient> response = new ArrayList(results);
		return response;
	}

	@Override
	public Patient getPatient(long id) {
		return patients.get(id);
	}

	@Override
	public Response createPatient(Patient patient) {
		patient.setId(++currentId);
		patients.put(patient.getId(), patient);
		return Response.ok(patient).build();
	}
	
}
