package com.example.medos.study_1;

/**
 * Created by medos on 9/30/2016.
 */
public class Doctors {
    private String name;
    private String address;
    private String Specialist;
    private String CertificatesPIC;

    public String getSpecialist() {
        return Specialist;
    }

    public void setSpecialist(String specialist) {
        Specialist = specialist;
    }

    public String getCertificatesPIC() {
        return CertificatesPIC;
    }

    public void setCertificatesPIC(String certificatesPIC) {
        CertificatesPIC = certificatesPIC;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Doctors( ) {


    }
    public Doctors(String name, String address,String Specialist,String CertificatesPIC ) {
        this.name = name;
        this.address = address;
        this.Specialist=Specialist;
        this.CertificatesPIC=CertificatesPIC;
    }
}
