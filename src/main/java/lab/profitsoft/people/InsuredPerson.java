package lab.profitsoft.people;


import java.time.LocalDate;



public class InsuredPerson extends Person {
    LocalDate birthDay;
    int insurancePrice;
    int taxIdentificationNumber;

    public InsuredPerson(){}

    public InsuredPerson(String lastName, String firstName, String patronimic, LocalDate birthDay, int insurancePrice, int taxIdentificationNumber)  {
        super(lastName,firstName,patronimic);
        this.birthDay = birthDay;
        this.insurancePrice = insurancePrice;
        this.taxIdentificationNumber=taxIdentificationNumber ;
    }


    @Override
    public String toString() {
        return "InsuredPerson{" +"fullName:'" + printInsuredPerson() +
                ", birthDay:" + birthDay +
                ", insurancePrice:" + insurancePrice +
                ", tax identification Number: "+taxIdentificationNumber+
                 '\'' +
                '}';
    }

    public String printInsuredPerson(){
        return lastName+" "+firstName.charAt(0)+". "+(((patronimic!=null) &&  !(this.patronimic.trim().isEmpty()))?patronimic.charAt(0)+".":"");
    }

    @Override
    public int hashCode(){
        return taxIdentificationNumber;
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof InsuredPerson)){
            return false;
        }
        if(((InsuredPerson) obj).taxIdentificationNumber==this.taxIdentificationNumber){
            return true;
        }
        return false;
    }

    public String getInsuredPerson(){
        return getPerson()+";"+this.birthDay+";"+this.insurancePrice+";"+this.taxIdentificationNumber;
    }

    public long getTaxIdentificationNumber() {
        return taxIdentificationNumber;
    }

    public int getInsurancePrice() {
        return insurancePrice;
    }

    public LocalDate getBirthDay() {
        return birthDay;
    }

    public void setBirthDay(LocalDate birthDay) {
        this.birthDay = birthDay;
    }

    public void setInsurancePrice(int insurancePrice) {
        this.insurancePrice = insurancePrice;
    }

    public void setTaxIdentificationNumber(int taxIdentificationNumber) {
        this.taxIdentificationNumber = taxIdentificationNumber;
    }


}
