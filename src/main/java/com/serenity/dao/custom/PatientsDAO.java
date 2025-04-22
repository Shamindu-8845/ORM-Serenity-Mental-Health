package com.serenity.dao.custom;

/*import org.example.dao.CrudDAO;
import org.example.entity.Patients;*/

import com.serenity.dao.CrudDAO;
import com.serenity.entity.Patients;

import java.io.IOException;
import java.util.List;

public interface PatientsDAO extends CrudDAO<Patients> {
    List<Patients> search(String text) throws IOException;
}
