package lab.profitsoft.people;

import java.util.Objects;

public class Client extends Person  {
    Type type;
    String companyName;
    String adress;

    public Client(){}

    public Client(Type type, String name, String adress) {
        this.type = type;
        this.companyName=name;
        this.adress=adress;
    }
    public Client(Type type,String lastName,String firstName,String patronimic,String adress){
        super(lastName,firstName,patronimic);
        this.type=type;
        this.adress=adress;
    }

    @Override
    public String toString() {
        return "Client{" +
                "type:" + type+(type==Type.INDIVIDUAL?", Full name:"+lastName+" "+firstName+" "+patronimic:", Company name:"+companyName) +
                ", adress:'" + adress + '\'' +
                '}';
    }
    public String getClient(){
        return this.type+";"+(type==Type.INDIVIDUAL? getPerson()+";":companyName+";")+this.adress;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public Type getType() {
        return type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return (type==Type.INDIVIDUAL?(Objects.equals(lastName,client.lastName) && Objects.equals(firstName,client.firstName)&&
                Objects.equals(patronimic,client.patronimic) &&
                Objects.equals(adress, client.adress)):(type == client.type &&
                Objects.equals(companyName, client.companyName) &&
                Objects.equals(adress, client.adress)));
    }

    @Override
    public int hashCode() {
        return (type==Type.INDIVIDUAL?Objects.hash(type,lastName,firstName,patronimic,adress):Objects.hash(type, companyName, adress));
    }
}
