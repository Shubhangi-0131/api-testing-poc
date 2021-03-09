package petclinic.api.onlineshop.data;
import com.google.gson.annotations.Expose;
import lombok.Builder;
import lombok.Getter;
@Getter
@Builder

public class Response{
    @Expose
    private String name;
    @Expose
    private String currency;
    @Expose
    private String id;
    @Expose
    private String amount;


    @Expose
    private int quantity;
    /*@Expose
        private String lastName;
        @Expose
        private List<Pet> pets;
        @Expose
        private String telephone
        private int amount;
        private String name;
        private String currency;
        private int id;
        public void setAmount(int amount){
           this.amount = amount;
        }
        public int getAmount(){
           return amount;
        }
        public void setName(String name){
           this.name = name;
        }
        public String getName(){
           return name;
        }
        public void setCurrency(String currency){
           this.currency = currency;
        }
        public String getCurrency(){
           return currency;
        }
        public void setId(int id){
           this.id = id;
        }
        public int getId(){
           return id;
        }*/
    @Override
    public String toString() {
        return "Response{" +
                "name='" + name + '\'' +
                ", currency='" + currency + '\'' +
                ", id='" + id + '\'' +
                ", quantity='" + quantity + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}