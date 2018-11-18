package lab.profitsoft.main;

import lab.profitsoft.insurance.Contract;
import lab.profitsoft.people.Client;
import lab.profitsoft.people.InsuredPerson;
import lab.profitsoft.people.Type;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;

public class MainClass {
    public static void main(String[] args) {
        Client clientE1 = new Client(Type.ENTITY, "Ivanushki", "Pushkin street, 6");
        Client clientE2 = new Client(Type.ENTITY, "Roshen", "Poltavska street, 34");
        Client clientE3 = new Client(Type.ENTITY, "AVK", "Nauki street, 44");
        Client clientI = new Client(Type.INDIVIDUAL, "Litvin", "Roman", "Mikhailovich", "Nauki 14");
        InsuredPerson insuredPerson1;
        InsuredPerson insuredPerson2;
        InsuredPerson insuredPerson3;
        InsuredPerson insuredPerson4;
        InsuredPerson insuredPerson5;

        insuredPerson1 = new InsuredPerson("Litvin", "Roman", "Mikhailovich", LocalDate.of(1998, 6, 10), 100000, 123456789);
        insuredPerson2 = new InsuredPerson("George", "Martin", null, LocalDate.of(1990, 10, 21), 10000, 234567890);
        insuredPerson3 = new InsuredPerson("Milo", "Gaines", null, LocalDate.of(1968, 3, 27), 20000, 345678901);
        insuredPerson4 = new InsuredPerson("Dorothy", "Green", "Alexandrovna", LocalDate.of(1979, 6, 1), 22000, 456789012);
        insuredPerson5 = new InsuredPerson("Doroth", "Gree", "Alexandro", LocalDate.of(1978, 7, 6), 24000, 456789012);


        LinkedHashSet<InsuredPerson> insuredPeople = new LinkedHashSet<>();// using LinkedHashSet makes us be sure that in each contract tax Identification number will be used only one time
        insuredPeople.add(insuredPerson2);
        insuredPeople.add(insuredPerson3);
        insuredPeople.add(insuredPerson4);
        insuredPeople.add(insuredPerson5);//insuredPerson5 is not added, because person with such identification number is already exist

        Contract contract1;
        Contract contract2;
        Contract contract3;
        Contract contract4;

        contract1 = new Contract(LocalDate.of(2018, 9, 12), LocalDate.of(2018, 9, 13), LocalDate.of(2021, 9, 12), clientE1, insuredPeople);
        contract2 = new Contract(LocalDate.of(2010, 3, 30), LocalDate.of(2010, 3, 31), LocalDate.of(2020, 3, 31), clientI, insuredPerson1);
        contract3 = new Contract(LocalDate.of(2017, 12, 25), LocalDate.of(2017, 12, 26), LocalDate.of(2018, 12, 26), clientE2, insuredPerson2, insuredPerson4);
        contract4 = new Contract(LocalDate.of(2018, 6, 21), LocalDate.of(2018, 6, 22), LocalDate.of(2028, 6, 22), clientE3, insuredPerson2, insuredPerson3, insuredPerson5);

        String filePath = "contracts.txt";

        contract1.uploadToFile(filePath);
        contract2.uploadToFile(filePath);
        contract3.uploadToFile(filePath);
        contract4.uploadToFile(filePath);
        ArrayList<Contract> contracts1 = new ArrayList<>();
        contracts1.add(contract1);
        contracts1.add(contract2);
        contracts1.add(contract3);
        contracts1.add(contract4);
        ArrayList<Contract> contracts = Contract.downloadContractsFromFile(filePath);

        for (Contract contract : contracts) {
            System.out.println(contract);
        }
    }
}
