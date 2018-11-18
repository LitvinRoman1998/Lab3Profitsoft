package lab.profitsoft.insurance;

import lab.profitsoft.people.Client;
import lab.profitsoft.people.InsuredPerson;
import lab.profitsoft.people.Type;
import org.junit.Assert;
import org.junit.Test;

import java.io.File;
import java.time.LocalDate;
import java.util.ArrayList;

public class ContractTest {
    @Test
    public void totalInsuranceCostTest(){
        Client client=new Client(Type.ENTITY,"abc","Pushkin, 6");
        InsuredPerson insuredPerson1=new InsuredPerson("Litvin", "Roman", "Mikhailovich", LocalDate.of(1998,6,10),100000,123456789);
        InsuredPerson insuredPerson2=new InsuredPerson("Milo", "Gaines",null,LocalDate.of(1968,3,27),20000,345678901);

        Contract contract=new Contract(LocalDate.of(2010,3,30),LocalDate.of(2010,3,31),LocalDate.of(2020,3,31),client,insuredPerson1,insuredPerson2);

        Assert.assertEquals(120000,contract.totalInsuranceCost());
    }
    @Test
    public void findByTaxIdentificationNumberTest(){
        Client client=new Client(Type.ENTITY,"abc","Pushkin, 6");

        InsuredPerson insuredPerson1=new InsuredPerson("Litvin", "Roman", "Mikhailovich", LocalDate.of(1998,6,10),100000,123456789);
        InsuredPerson insuredPerson2=new InsuredPerson("Milo", "Gaines",null,LocalDate.of(1968,3,27),20000,345678901);

        Contract contract=new Contract(LocalDate.of(2010,3,30),LocalDate.of(2010,3,31),LocalDate.of(2020,3,31),client,insuredPerson1,insuredPerson2);

        Assert.assertEquals(insuredPerson1,contract.findByTaxIdentificationNumber(123456789));
    }

    @Test
    public void downloadContractsFromFileTest(){
        Client clientE=new Client(Type.ENTITY,"abc","Pushkin, 6");
        Client clientI=new Client(Type.INDIVIDUAL,"Litvin", "Roman", "Mikhailovich","Nauki 14");


        InsuredPerson insuredPerson1=new InsuredPerson("Litvin", "Roman", "Mikhailovich", LocalDate.of(1998,6,10),100000,123456789);
        InsuredPerson insuredPerson2=new InsuredPerson("Milo", "Gaines","James",LocalDate.of(1968,3,27),20000,345678901);
        InsuredPerson insuredPerson3=new InsuredPerson("Dorothy", "Green", "Alexandrovna",LocalDate.of(1979,6,1),22000,456789012);

        Contract contract1=new Contract(LocalDate.of(2010,3,30),LocalDate.of(2010,3,31),LocalDate.of(2020,3,31),clientE,insuredPerson2,insuredPerson3);
        Contract contract2=new Contract(LocalDate.of(2018,6,21),LocalDate.of(2018,6,22),LocalDate.of(2028,6,22),clientI,insuredPerson1);

        String filePath="contracts.txt";

        contract1.uploadToFile(filePath);
        contract2.uploadToFile(filePath);

        ArrayList<Contract> contracts=new ArrayList<>();
        contracts.add(contract1);
        contracts.add(contract2);

        Assert.assertEquals(contracts,Contract.downloadContractsFromFile(filePath));
    }
}
