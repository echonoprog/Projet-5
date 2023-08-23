package com.ocs.safetynet.dao;

import com.ocs.safetynet.data.Data;
import com.ocs.safetynet.model.Medicalrecord;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
public class MedicalrecordDAO {


    public List<Medicalrecord> getAllMedicalrecords() {

        return Data.getMedicalrecords();
    }

    public void addMedicalrecord(Medicalrecord medicalrecord) {
        List<Medicalrecord> medicalrecordList=getAllMedicalrecords();
        medicalrecordList.add(medicalrecord);
    }

    public void updateMedicalrecord(int index, Medicalrecord medicalRecord) {
        List<Medicalrecord> medicalrecordList = getAllMedicalrecords();

        medicalrecordList.set(index,medicalRecord);
    }

    public void deleteMedicalrecord(int index) {
        List<Medicalrecord> medicalrecordList = getAllMedicalrecords();
        medicalrecordList.remove(index);
    }
}
