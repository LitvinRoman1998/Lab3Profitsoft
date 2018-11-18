package lab.profitsoft.people;

import org.junit.Assert;
import org.junit.Test;

import java.time.LocalDate;

public class InsuredPersonTest {
    @Test
    public void printInsuredPersonTest(){
        InsuredPerson insuredPerson1=new InsuredPerson("Litvin", "Roman", "Mikhailovich", LocalDate.of(1998,6,10),100000,123456789);

        Assert.assertEquals("Litvin R. M.",insuredPerson1.printInsuredPerson());
    }
}
