package com.ocs.safetynet.service;

import com.ocs.safetynet.dao.MedicalrecordDAO;
import com.ocs.safetynet.model.Medicalrecord;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MedicalrecordService {

    private List<Medicalrecord> medicalrecords;

    private MedicalrecordDAO medicalrecordDAO;

    public MedicalrecordService(MedicalrecordDAO medicalrecordDAO) {
        this.medicalrecordDAO = medicalrecordDAO;
    }

    public List<Medicalrecord> getAllMedicalrecords(){
        return medicalrecordDAO.getAllMedicalrecords();
    }



    public Medicalrecord addMedicalrecord(Medicalrecord medicalrecord) {
            medicalrecordDAO.addMedicalrecord(medicalrecord);
            return medicalrecord;

    }

    public Medicalrecord updateMedicalrecord(String firstName, String lastName, Medicalrecord medicalRecord) {
        List <Medicalrecord> medicalrecordList=getAllMedicalrecords();
        Medicalrecord medicalrecordToUpdate = findMedicalrecord(firstName,lastName);
        int index = medicalrecordList.lastIndexOf(medicalrecordToUpdate);
        medicalrecordDAO.updateMedicalrecord(index,medicalRecord);
        return medicalRecord;
    }

    private Medicalrecord findMedicalrecord(String firstName, String lastName) {
        List<Medicalrecord> medicalrecordList = getAllMedicalrecords();
        for (Medicalrecord medicalrecord : medicalrecordList){
            if (medicalrecord.getFirstName().equals(firstName) && medicalrecord.getLastName().equals(lastName)){
                return medicalrecord;
            }
        }
        return null;
    }

    public void deleteMedicalRecord(String firstName, String lastName) {
        List<Medicalrecord> medicalrecordList=getAllMedicalrecords();
        Medicalrecord medicalrecordToUpdate = findMedicalrecord(firstName,lastName);
        int index = medicalrecordList.lastIndexOf(medicalrecordToUpdate);
        medicalrecordDAO.deleteMedicalrecord(index);
    }
}
