package lab.profitsoft.insurance;

import lab.profitsoft.people.Client;
import lab.profitsoft.people.InsuredPerson;
import lab.profitsoft.people.Type;


import java.io.*;
import java.time.LocalDate;
import java.util.*;

public class Contract {
    int number;
    LocalDate conclusionDate;
    LocalDate startDate;
    LocalDate endDate;
    Client client;
    ArrayList<InsuredPerson> insuredPeople;

    static int numOfContract=0;

    public Contract(){}

    public Contract(LocalDate conclusionDate, LocalDate startDate, LocalDate endDate, Client client, InsuredPerson ... insuredPeople){
    this(conclusionDate, startDate, endDate, client,new LinkedHashSet<>(Set.of(insuredPeople)));
    }


    public Contract(LocalDate conclusionDate, LocalDate startDate, LocalDate endDate, Client client, LinkedHashSet<InsuredPerson> insuredPeople){
        this.number = numOfContract++;
        this.conclusionDate = conclusionDate;
        this.startDate = startDate;
        this.endDate = endDate;
        this.client = client;
        this.insuredPeople = new ArrayList<>(insuredPeople);;
    }

    public int totalInsuranceCost(){
        int contractCost=0;
        for (InsuredPerson insuredPerson:insuredPeople){
            contractCost+=insuredPerson.getInsurancePrice();
        }
        return contractCost;
    }

    public InsuredPerson findByTaxIdentificationNumber(int taxIdentificationNumber){
        for (InsuredPerson insuredperson:insuredPeople) {
            if(taxIdentificationNumber==insuredperson.getTaxIdentificationNumber()){
                return insuredperson;
            }
        }
        return null;
    }

    public void sortByAlphabet(){
        insuredPeople.sort((o1, o2) -> o1.getLastName().compareTo(o2.getLastName()));
        System.out.println(insuredPeople);
    }

    public void sortByBirthDate(){
        insuredPeople.sort((Comparator<InsuredPerson>) (o1, o2) ->o1.getBirthDay().compareTo(o2.getBirthDay()));
        System.out.println(insuredPeople);
    }

    public void uploadToFile(String filePath){
        File file=new File(filePath);
        try(BufferedWriter bufferedWriter=new BufferedWriter(new FileWriter(file,true))) {
            bufferedWriter.write(Integer.valueOf(this.number).toString());
            bufferedWriter.write(";");
            bufferedWriter.write(this.conclusionDate.toString());
            bufferedWriter.write(";");
            bufferedWriter.write(this.startDate.toString());
            bufferedWriter.write(";");
            bufferedWriter.write(this.endDate.toString());
            bufferedWriter.write(";");
            bufferedWriter.write(this.client.getClient());
            Iterator<InsuredPerson> iterator=insuredPeople.listIterator();
            while (iterator.hasNext())
            {
                bufferedWriter.write(";");
                bufferedWriter.write(iterator.next().getInsuredPerson());
            }
            bufferedWriter.write(System.lineSeparator());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public static ArrayList<Contract> downloadContractsFromFile(String filePath){
        ArrayList<Contract> contracts=new ArrayList<>();
        try {
            File file=new File(filePath);
            BufferedReader bufferedReader=new BufferedReader(new FileReader(file));
            String readLine=null;
            Scanner scanner=null;
            int index=0;
            while ((readLine=bufferedReader.readLine())!=null ){
                Contract contract=new Contract();
                Client client=new Client();
                ArrayList<InsuredPerson> insuredPeople=new ArrayList<>();
                scanner=new Scanner(readLine);
                scanner.useDelimiter(";");
                if(!(readLine.trim().isEmpty())){
                while(scanner.hasNext()) {
                    String data = scanner.next();
                    if (index == 0) {
                        contract.setNumber(Integer.parseInt(data));
                    } else if (index == 1) {
                        contract.setConclusionDate(LocalDate.parse(data));
                    } else if (index == 2) {
                        contract.setStartDate(LocalDate.parse(data));
                    } else if (index == 3) {
                        contract.setEndDate(LocalDate.parse(data));
                    } else if (index == 4) {
                        client.setType(Type.valueOf(data));
                    } else if (client.getType().equals(Type.INDIVIDUAL) && index == 5) {
                        client.setLastName(data);
                        client.setFirstName(scanner.next());
                        client.setPatronimic(scanner.next());
                        client.setAdress(scanner.next());
                        contract.setClient(client);
                    } else if (client.getType().equals(Type.ENTITY) && index == 5) {
                        client.setCompanyName(data);
                        client.setAdress(scanner.next());
                        contract.setClient(client);
                    } else {
                        InsuredPerson insuredPerson=new InsuredPerson();
                        insuredPerson.setLastName(data);
                        insuredPerson.setFirstName(scanner.next());
                        insuredPerson.setPatronimic(String.valueOf(scanner.next()));
                        insuredPerson.setBirthDay(LocalDate.parse(scanner.next()));
                        insuredPerson.setInsurancePrice(Integer.parseInt(scanner.next()));
                        insuredPerson.setTaxIdentificationNumber(Integer.parseInt(scanner.next()));
                        insuredPeople.add(insuredPerson);
                    }
                    index++;
                    }
                    index=0;
                    contract.setInsuredPeople(insuredPeople);
                    contracts.add(contract);
                }

            }
            bufferedReader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return contracts;
    }

    @Override
    public String toString() {
        return "Contract{" +
                "number: " + number +
                ", conclusionDate: " + conclusionDate +
                ", startDate: " + startDate +
                ", endDate: " + endDate +
                ", client: " + client +
                ", insuredPeople: " + insuredPeople +
                ", total Insurance sum: "+totalInsuranceCost()+
                '}';
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public void setConclusionDate(LocalDate conclusionDate) {
        this.conclusionDate = conclusionDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public void setInsuredPeople(ArrayList<InsuredPerson> insuredPeople) {
        this.insuredPeople = insuredPeople;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Contract contract = (Contract) o;
                return number == contract.number &&
                Objects.equals(conclusionDate, contract.conclusionDate) &&
                Objects.equals(startDate, contract.startDate) &&
                Objects.equals(endDate, contract.endDate) &&
                Objects.equals(client, contract.client) &&
                Objects.equals(insuredPeople, contract.insuredPeople);

    }

    @Override
    public int hashCode() {
        return Objects.hash(number, conclusionDate, startDate, endDate, client, insuredPeople);
    }
}
